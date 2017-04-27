public class Calculator{
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
