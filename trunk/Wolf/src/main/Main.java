package main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {

	private static final Dimension SIZE = new Dimension(768,640);

	public static void main(String[] args) {
		new Main().start();
	}

	JFrame frame;

	public Main() {

		frame = new JFrame("VectorMash");
		frame.setSize(SIZE);
		frame.setLocationRelativeTo(null);

	}

	private void start() {
		frame.setVisible(true);
	}

}
