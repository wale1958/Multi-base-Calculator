import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Base4Panel functions as the view of this project. It declares the buttons,
 * Label, Slider, calculator model and it's observable {@link Base4CalcState}.
 * It is responsible for the layout and the display of the calculator. Has an
 * inner class {@link ButtonListener}
 * 
 * 
 * @author Adebowale Ojetola
 * @version %I%, %G%
 * @since 1.0
 *
 */
public class Base4Panel extends JPanel implements Observer {
	private static final long serialVersionUID = -1551518459395937415L; // required
																		// for a
																		// subclass
																		// of
																		// JPanel
	public static final int defaultBase = 10;
	public static final int maxBase = 16;
	public static final int minBase = 2;

	private Base4CalcState calc; // does the calculating work
	private ArrayList<JButton> values; // holds integer values 0-9
	private ArrayList<JButton> operation; // holds operations

	private CalculatorModel mode; // observable
	private JLabel display; // displays the buttons clicked and calculation
	private JSlider baseSlider; // used to set the base

	private int currentBase;
	private boolean operClicked = false; // used to replace the input clicked on
											// the label after an operation is
											// clicked. To separate first input
											// from the second
	private int operCounter = 0; // used to determine when a calculation should
									// be made which is after any of the
									// operations except clear has been clicked
									// more than once.
	private boolean showValue = false; // used to determine if an input should
										// be shown or a calculation

	/**
	 * Sets up the layout, buttons and slider.
	 * 
	 * @param cal
	 *            observable reverence value created in {@link Base4Calculator}
	 */
	Base4Panel(Base4CalcState cal) {

		display = new JLabel();
		operation = new ArrayList<JButton>();
		values = new ArrayList<JButton>();
		calc = cal;
		mode = new CalculatorModel();

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel disPanel = new JPanel(new FlowLayout());
		JPanel sliderPanel = new JPanel(new FlowLayout());
		JPanel buttonLayout = new JPanel(new GridLayout(4, 6));

		operation.add(new JButton("+"));
		operation.add(new JButton("-"));
		operation.add(new JButton("*"));
		operation.add(new JButton("/"));
		operation.add(new JButton("CL"));
		operation.add(new JButton("="));

		for (int i = 0; i < operation.size(); i++) {
			operation.get(i).addActionListener(new ButtonListener());
			buttonLayout.add(operation.get(i));
		}

		for (int i = 0; i < maxBase; i++) {
			JButton temp = new JButton(Integer.toHexString(i).toUpperCase());
			temp.addActionListener(new ButtonListener());
			values.add(temp);
			buttonLayout.add(temp);
		}

		display.setVisible(true);
		display.setSize(250, 15);
		display.setForeground(Color.RED);
		display.setFont(new Font("Default", Font.BOLD, 30));
		disPanel.add(display);

		baseSlider = new JSlider(minBase, maxBase, defaultBase);
		updateButtons(defaultBase);
		baseSlider.setPaintTicks(true);
		baseSlider.setPaintLabels(true);
		baseSlider.setMajorTickSpacing(1);
		currentBase = baseSlider.getValue();
		baseSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				currentBase = ((JSlider) e.getSource()).getValue();
				updateButtons(currentBase); // disable/enable buttons as the
											// slider moves
				calc.setBase(currentBase); // update the model

			}
		});

		sliderPanel.add(baseSlider);

		this.add(disPanel);
		this.add(buttonLayout);
		this.add(sliderPanel);

	}

	/**
	 * Updates the buttons as the slider moves
	 * 
	 * @param currentBase
	 *            holds the current base the slider is on
	 */
	public void updateButtons(int currentBase) {
		for (int i = 0; i < values.size(); i++) {
			if (Integer.parseInt(values.get(i).getText(), maxBase) < currentBase) {
				values.get(i).setEnabled(true);
			} else {
				values.get(i).setEnabled(false);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 * Decides whether the calculation or input should be displayed
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (!showValue) {
			display.setText(((Base4CalcState) o).getEquation());
		} else {
			display.setText(((Base4CalcState) o).currentValue());
		}

	}

	/**
	 * buttonListener functions as the as the controller. It decides when to
	 * calculate, what value to be sent to the model and what to do when an
	 * operation is clicked.
	 * 
	 * @author Adebowale Ojetola
	 * @version %I%, %G%
	 * @since 1.0
	 *
	 */
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			for (int i = 0; i < values.size(); i++) {

				if (event.getSource() == values.get(i)) {
					showValue = false;
					if (!operClicked) { // if an operation hasn't been clicked
						calc.receiveKeyValue(values.get(i).getText()); // tell
																		// the
																		// model
																		// what
																		// button
																		// was
																		// clicked
					} else {
						calc.resetDisplay(); // otherwise clear the screen
						calc.receiveKeyValue(values.get(i).getText()); // then
																		// tell
																		// the
																		// model
																		// what
																		// button
																		// was
																		// clicked
						operClicked = false;
					}
				}
			}

			for (int i = 0; i < operation.size(); i++) {
				if (event.getSource() == operation.get(i)) {
					if (operation.get(i).getText() == "+" || operation.get(i).getText() == "-"
							|| operation.get(i).getText() == "*" || operation.get(i).getText() == "/") { // for
																											// these
																											// operations
						showValue = true;
						mode.setParameterB(calc.getEquation()); // store the
																// first input
						mode.setCurrentoper(operation.get(i).getText()); // and
																			// the
																			// first
																			// operation

						operCounter++;
						if (operCounter > 1) { // if an operation has already
												// been clicked before i.e. the
												// controller does not rely on
												// the '=' button to calculate.
												// Note that this allows for
												// continuation of calculations
												// on the operations rather than
												// the '=' button. For example
												// "2++" displays "4" as the
												// output in base 10. Another example is
												// "8-2--" and that displays "4" as
												// the output in base 10. This feature does not work on the '=' button.
							calc.getInput(mode, currentBase);// send both inputs
																// and the
																// currentBase
																// to the model
																// to calculate
							mode.setParameterA(calc.currentValue()); // set the
																		// first
																		// input
																		// to
																		// the
																		// calculated
																		// value
																		// in
																		// the
																		// case
																		// that
																		// the
																		// user
																		// wants
																		// to
																		// use
																		// that
																		// value
																		// in
																		// the
																		// next
																		// calculation
							mode.setOper1(mode.getCurrentoper());
						} else {
							mode.setParameterA(mode.getParameterB());// otherwise
																		// save
																		// the
																		// integer
																		// as
																		// the
																		// first
																		// input
							mode.setOper1(mode.getCurrentoper());
						}
						operClicked = true;
					}

					if (operation.get(i).getText() == "CL") {
						showValue = false;
						calc.resetDisplay();
						operCounter = 0;
						calc.clear();
						mode.clear();
					}

					if (operation.get(i).getText() == "=") {
						if (operCounter == 0) { // if there an operation other
												// than '=' and 'CL' has not
												// been clicked
							showValue = false; // don't calculate or show
												// anything other than the input
						} else {
							showValue = true;
							operCounter++;
							mode.setParameterB(calc.getEquation());// otherwise
																	// store the
																	// second
																	// input
							calc.getInput(mode, currentBase);// and calculate
							operClicked = true;
						}
					}
				}
			}

		}
	}

}
