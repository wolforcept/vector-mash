package client;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class VectorClient {

	private static final Dimension SIZE = new Dimension(768, 640);

	public static void main(String[] args) {
		new VectorClient().start();
	}

	/* SWING */
	JFrame frame;

	/* NET */
	Connector connector;

	public VectorClient() {

		String name = JOptionPane.showInputDialog("Enter your name:");

		connector = new Connector(name);

		if (!connector.connect()) {
			System.exit(0);
		}
		frame = new JFrame("VectorMash");
		frame.setSize(SIZE);
		frame.setLocationRelativeTo(null);

	}

	private void start() {
		frame.setVisible(true);
	}

}
