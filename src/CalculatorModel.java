public class CalculatorModel{
	private String parameterB;
	private String currentoper;
	private String parameterA;
	private String oper1;
	
	
	
	
	public void clear(){ 
	this.parameterB=""; 
	this.currentoper=""; 
	this.parameterA=""; 
	this.oper1="";
	}
	
	/**
	 * @return the currentinput
	 */
	public String getParameterB() {
		return parameterB;
	}
	/**
	 * @param currentinput the currentinput to set
	 */
	public void setParameterB(String currentinput) {
		this.parameterB = currentinput;
	}
	/**
	 * @return the currentoper
	 */
	public String getCurrentoper() {
		return currentoper;
	}
	/**
	 * @param currentoper the currentoper to set
	 */
	public void setCurrentoper(String currentoper) {
		this.currentoper = currentoper;
	}
	/**
	 * @return the input1
	 */
	public String getParameterA() {
		return parameterA;
	}
	/**
	 * @param input1 the input1 to set
	 */
	public void setParameterA(String input1) {
		this.parameterA = input1;
	}
	/**
	 * @return the oper1
	 */
	public String getOper1() {
		return oper1;
	}
	/**
	 * @param oper1 the oper1 to set
	 */
	public void setOper1(String oper1) {
		this.oper1 = oper1;
	}
	
	public String toString(){
		return parameterB +": " + currentoper +": " + parameterA + ": " + oper1;
		}
	
	
}