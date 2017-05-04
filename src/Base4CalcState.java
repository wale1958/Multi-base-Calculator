import java.util.Observable;

/**
 * Base4CalcState class is a public class that functions has a model. It holds:
 * <ul>
 * <li>Value of the Integer buttons clicked on the calculator.
 * <li>The current base.
 * <li>The value of the most recent calculation made.
 * </ul>
 * <p>
 * Base4CalcState extends the Observable class and updates the
 * Observer {@link Base4Panel} whenever either of the value it holds changes.
 * 
 * 
 * 
 * @author Adebowale Ojetola
 * @version %I%, %G%
 * @since 1.0
 *
 */
public class Base4CalcState extends Observable {
	private int value; // value of current calculation
	private int currentBase;
	private String equation; // holds the value of the input
	private boolean firstTime; // used to prevent duplication of the integer
								// zero when the calculator is started up

	/**
	 * Initializes the value of the instance variable of the class
	 * 
	 */
	Base4CalcState() {
		value = 0;
		equation = "0";
		firstTime = true;
		currentBase = 10;
	}

	/**
	 * Resets the value of the current calculation.
	 */
	void clear() {
		value = 0;
	}

	/**
	 * used to alert the view that the observable's state has changed
	 */
	void setup() {
		setChanged();
		notifyObservers();
	}

	/**
	 * Stores the value of the Integer button clicked by the user, in the
	 * variable equation. Excluding whenever the Integer button clicked is zero
	 * and the value held in equation is also zero.
	 * 
	 * @param key
	 *            contains the value of the Integer button clicked.
	 */
	void receiveKeyValue(String key) {
		System.out.println(key + "===" + equation);
		if (!(key.equals("0") && equation.equals("0"))) { // if key and equation
															// both don't hold
															// the value zero
			if (firstTime) { // If this is the first integer button clicked
				equation = ""; // remove the 0
				firstTime = false;
			}
			equation = equation + key; // store the value of the button clicked
			setup(); // notify the view
		}
	}

	/**
	 * resets the value of the equation and enables the check for zero
	 * 
	 */
	void resetDisplay() {
		equation = "0";
		firstTime = true;
		setup();
	}

	/**
	 * @return value of the Integer value to be used in a calculation
	 */
	String getEquation() {
		return equation;
	}

	/**
	 * @return value of the most recent calculation in the most recent base
	 */
	String currentValue() {

		return Integer.toString(value, currentBase);
	}

	/**
	 * Calculates the input values held in parameter mode and stores it as an
	 * integer
	 * 
	 * @param mode
	 *            holds the value of two inputs and the operation to be
	 *            performed between them
	 * @param base
	 *            holds the value of the base the inputs should be calculated in
	 */
	void getInput(CalculatorModel mode, int base) {
		currentBase = base;
		value = calculate(Integer.parseInt(mode.getParameterA(), currentBase),
				Integer.parseInt(mode.getParameterB(), currentBase), mode.getOper1());
		setup();
	}

	/**
	 * @return base a calculation should be made in
	 */
	int getBase() {
		return currentBase;
	}

	/**
	 * updates the base and notifies the observer
	 * 
	 * @param base
	 *            the base a calculation should be made in
	 */
	void setBase(int base) {
		currentBase = base;
		setup();
	}

	/**
	 * Does the calculation in integer
	 * 
	 * @param input1
	 *            holds the first input the user clicked 
	 * @param input2
	 *            holds the second input the user clicked 
	 * @param operation
	 *            holds the operation to be performed between the two inputs
	 * @return the result of the calculation
	 */
	public static int calculate(int input1, int input2, String operation) {
		if (operation == "+") {
			return input1 + input2;
		} else if (operation == "-") {
			return input1 - input2;
		} else if (operation == "*") {
			return input1 * input2;
		} else {
			return input1 / input2;
		}

	}
}
