public class Base4CalcState {
	private int value; // value of current calculation
	private int currentBase;
	// calculatorModel mode;

	Base4CalcState() {
		value = 0;
	}

	// invoked when the "Clear" button is pressed--is there any other time it
	// should be invoked?
	void clear() {
		value = 0;
	}

	String currentValue() {
		return Integer.toString(value, currentBase);
	}

	void getInput(CalculatorModel mode, int base) {
		currentBase = base;
		value = Calculator.calculate(Integer.parseInt(mode.getParameterA(), currentBase),
				Integer.parseInt(mode.getParameterB(), currentBase), mode.getOper1());
	}

	int getBase() {
		return currentBase;
	}

	void setBase(int base) {
		currentBase = base;
	}

}
