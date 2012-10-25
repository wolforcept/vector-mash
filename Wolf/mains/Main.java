package mains;

import javax.swing.JFrame;

import server.Ivory;

public class Main {

	public static void main(String[] args) {
		new Main().start();
	}

	JFrame frame;

	public Main() {

		frame = new JFrame("VectorMash");
		frame.setSize(Ivory.SIZE);
		frame.setLocationRelativeTo(null);

	}

	private void start() {
		frame.setVisible(true);
	}

}
