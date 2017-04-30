import java.util.Observable;

public class Base4CalcState extends Observable {
	private int value; // value of current calculation
	private int currentBase;
	private String equation="0";
	private boolean firstTime=true;
	// calculatorModel mode;

	Base4CalcState() {
		value = 0;
	}

	// invoked when the "Clear" button is pressed--is there any other time it
	// should be invoked?
	void clear() {
		value = 0;
	}
	
	void setup(){
		setChanged();
	    notifyObservers();
	}
	
	void receiveKeyValue(String key){
		System.out.println(key + "===" + equation);
		if(!(key.equals("0") && equation.equals("0"))){
			if(firstTime){
				//resetDisplay();
				equation="";
				firstTime=false;
			}
			equation= equation+key;
			setChanged();
		    notifyObservers();
		}
	}
	
	void resetDisplay(){
	//if(firstTime){
	//	equation="";
	//}
	//else{
		equation="0"; 
	//}
	firstTime=true; 
	setChanged();
    notifyObservers();
    }
	
	String getEquation(){return equation;}
	
	String currentValue() {
		
		return Integer.toString(value, currentBase);
	}

	void getInput(CalculatorModel mode, int base) {
		currentBase = base;
		value = calculate(Integer.parseInt(mode.getParameterA(), currentBase),
				Integer.parseInt(mode.getParameterB(), currentBase), mode.getOper1());
		setChanged();
	    notifyObservers();
	}

	int getBase() {
		return currentBase;
	}

	void setBase(int base) {
		currentBase = base;
		setChanged();
	    notifyObservers();
	}
	
	String getValue(){return currentValue();}

	public static int calculate (int input1, int input2, String operation){
		if(operation == "+"){
			return input1+input2;
			}
		else if(operation =="-"){
			return input1-input2;
		}
		else if(operation =="*"){
			return input1*input2;
		}
		else
			return input1/input2;

			
		}
}
