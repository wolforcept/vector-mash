package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import common.IvorySnapshot;

public class Connector {

	private static final int PORT = 60550;

	private Socket socket;
	private Sender sender;
	private Receiver receiver;
	private String name;

	IvorySnapshot snapshot;

	public Connector(String name) {
		this.name = name;
	}

	public boolean connect() {
		try {
			socket = new Socket(InetAddress.getByName("localhost"), PORT);
			ObjectInputStream is = new ObjectInputStream(
					socket.getInputStream());
			receiver = new Receiver(is);
			ObjectOutputStream os = new ObjectOutputStream(
					socket.getOutputStream());
			sender = new Sender(os);

			os.writeObject(name);

			receiver.start();
			sender.start();
			return true;
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null,
					"Could Not Connect: UnknownHostException");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Could Not Connect: IOException");
			e.printStackTrace();
		}
		return false;
	}

	public void stopReceiving() {
		receiver.receiving = false;
	}

	private class Receiver extends Thread {
		private ObjectInputStream is;
		private boolean receiving;

		public Receiver(ObjectInputStream is) {
			this.is = is;
			receiving = true;
		}

		@Override
		public void run() {
			try {
				while (receiving) {
					try {
						Object latest = is.readObject();
						if (latest instanceof IvorySnapshot)
							snapshot = (IvorySnapshot) latest;
					} catch (ClassNotFoundException e) {
						System.err.println("Failed to read, class not found.");
						// e.printStackTrace();
					}
				}
			} catch (IOException e) {
				System.err.println("Input/Output Error, connection with "
						+ name + "terminated. ");
				// e.printStackTrace();
			}
		}
	}

	private class Sender extends Thread {

		public Sender(ObjectOutputStream os) {

		}

	}

}
