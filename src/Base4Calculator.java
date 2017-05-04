import javax.swing.JFrame;

/**
 * Base4Calculator is responsible for laying out the background of the program.
 * It starts up the Base4CalcState and Base4Panel, making Base4Panel to an
 * observer to Base4CalcState. It also creates a JFrame and adds the panel to
 * it.
 * 
 * @author Adebowale Ojetola
 * @version %I%, %G%
 * @since 1.0
 *
 */
public class Base4Calculator {

	/**
	 * Kicks off the program
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String[] args) {
		createAndShowGUI();
	}

	/**
	 * starts up Base4CalcState and Base4Panel adds Base4Panel as an observer to
	 * Base4CalcState. It also creates a JFrame and adds the Base4Panel variable
	 * to it.
	 */
	private static void createAndShowGUI() {
		Base4CalcState calc = new Base4CalcState();
		Base4Panel panel = new Base4Panel(calc);
		calc.addObserver(panel);
		calc.setup(); // update panel
		JFrame frame = new JFrame("Base 4 Calculator");

		frame.add(panel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
}
