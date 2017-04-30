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

public class Base4Panel extends JPanel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1551518459395937415L;

	public static final int defaultBase = 10;
	public static final int maxBase = 16;
	public static final int minBase = 2;

	private Base4CalcState calc; // this object will
									// actually do the
									// calculating work
	private ArrayList<JButton> values;
	private ArrayList<JButton> operation;

	private CalculatorModel mode;
	private JLabel display;
	private JSlider baseSlider;

	private int currentBase;
	private boolean postOperAction = false;
	private int operCounter = 0;
	private boolean showValue = false;

	String displayText;

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
			operation.get(i).addActionListener(new buttonListener());
			buttonLayout.add(operation.get(i));
		}

		for (int i = 0; i < maxBase; i++) {
			JButton temp = new JButton(Integer.toHexString(i).toUpperCase());
			temp.addActionListener(new buttonListener());
			values.add(temp);
			buttonLayout.add(temp);
		}

		display.setVisible(true);
		display.setSize(250, 15);
		display.setBackground(Color.BLACK);
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
				updateButtons(currentBase);
				calc.setBase(currentBase);

			}
		});

		sliderPanel.add(baseSlider);

		this.add(disPanel);
		this.add(buttonLayout);
		this.add(sliderPanel);

		// do you need any other layout elements?

		// you may decide you want to improve the appearance of the layout,
		// which is fine. But defer that until you get the calculator working.
		// (You can spend HOURS messing with layout, which is not the point of
		// this exercise!)

	}

	public void updateButtons(int currentBase) {
		for (int i = 0; i < values.size(); i++) {
			if (Integer.parseInt(values.get(i).getText(), maxBase) < currentBase) {
				values.get(i).setEnabled(true);
			} else {
				values.get(i).setEnabled(false);
			}
		}
	}

	class buttonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			for (int i = 0; i < values.size(); i++) {

				if (event.getSource() == values.get(i)) {
					showValue = false;
					if (!postOperAction) {
						calc.receiveKeyValue(values.get(i).getText());
						System.out.println(values.get(i).getText());
					} else {
						calc.resetDisplay();
						calc.receiveKeyValue(values.get(i).getText());
						postOperAction = false;
						System.out.println(values.get(i).getText());

					}

				}

			}

			for (int i = 0; i < operation.size(); i++) {
				if (event.getSource() == operation.get(i)) {
					if (operation.get(i).getText() == "+" || operation.get(i).getText() == "-"
							|| operation.get(i).getText() == "*" || operation.get(i).getText() == "/") {
						showValue = true;
						System.out.println(display.getText());
						System.out.println(currentBase);
						mode.setParameterB(display.getText());
						mode.setCurrentoper(operation.get(i).getText());

						System.out.println("+");
						postOperAction = true;
						operCounter++;
						if (operCounter > 1) {
							System.out.println(mode.toString());
							calc.getInput(mode, currentBase);
							mode.setParameterA(calc.currentValue());
							mode.setOper1(mode.getCurrentoper());
						} else {
							mode.setParameterA(mode.getParameterB());
							mode.setOper1(mode.getCurrentoper());
						}
					}

					if (operation.get(i).getText() == "CL") {
						showValue = false;
						System.out.println("CL");
						calc.resetDisplay();
						operCounter = 0;
						calc.clear();
						mode.clear();
					}

					if (operation.get(i).getText() == "=") {
						System.out.println("=");
						showValue = true;
						operCounter++;
						mode.setParameterB(display.getText());
						calc.getInput(mode, currentBase);
						operCounter = 0;
						postOperAction = true;
					}
				}
			}

			// you need to deal with event handling. before you go too crazy
			// writing code,
			// think about when the calc object needs to be involved, and when
			// it doesn't

		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (!showValue) {
			display.setText(((Base4CalcState) o).getEquation());
		} else {
			display.setText(((Base4CalcState) o).getValue());
		}

	}
}
