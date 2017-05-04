/**
 * CalculatorModel also functions as the model. It stores the value of the
 * inputs and operations to be used in the calculations.
 * 
 * @author Adebowale Ojetola
 * @version %I%, %G%
 * @since 1.0
 *
 */
public class CalculatorModel {
	private String parameterB;
	private String currentoper;
	private String parameterA;
	private String oper1;

	/**
	 * Resets the input values
	 */
	public void clear() {
		this.parameterB = "0";
		this.parameterA = "0";
	}

	/**
	 * @return most recent input
	 */
	public String getParameterB() {
		return parameterB;
	}

	/**
	 * @param currentinput
	 *            the most recent input
	 */
	public void setParameterB(String currentinput) {
		this.parameterB = currentinput;
	}

	/**
	 * @return the most recent operation
	 */
	public String getCurrentoper() {
		return currentoper;
	}

	/**
	 * @param currentoper
	 *            the operator to be used in the next calculation
	 */
	public void setCurrentoper(String currentoper) {
		this.currentoper = currentoper;
	}

	/**
	 * @return the first input to be used in a calculation
	 */
	public String getParameterA() {
		return parameterA;
	}

	/**
	 * @param input1
	 *            the first argument to be used in a calculation
	 */
	public void setParameterA(String input1) {
		this.parameterA = input1;
	}

	/**
	 * @return the operator to be used in a calculation
	 */
	public String getOper1() {
		return oper1;
	}

	/**
	 * @param oper1
	 *            the operator to be used in a calculation
	 */
	public void setOper1(String oper1) {
		this.oper1 = oper1;
	}
}