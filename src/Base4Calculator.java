import javax.swing.JFrame;

// this class probably won't need to change much: most of the 'action' is in Base4Panel and Base4Calc


public class Base4Calculator {

	public static void main(String[] args) {
		createAndShowGUI();
	}

	private static void createAndShowGUI() {
		Base4CalcState calc=new Base4CalcState();
		Base4Panel panel=new Base4Panel(calc);
		calc.addObserver(panel);
		calc.setup();
		JFrame frame = new JFrame("Base 4 Calculator");

		frame.add(panel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
}
