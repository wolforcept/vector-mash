package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class VectorServerCommunicator extends Thread {

	private ServerSocket server_socket;
	private boolean acceptClients;
	private int port;

	public VectorServerCommunicator(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		try {
			openConnection();
		} catch (IOException e) {
			System.out.println("Could not open connection at port " + port
					+ ". Port already in use?");
		}
		try {
			acceptClients = true;
			while (acceptClients) {
				acceptClient();
			}
		} catch (IOException e) {
			System.out.println("ClientDealer stopped. IO Exception");
			e.printStackTrace();
		}

	}

	private void acceptClient() throws IOException {
		System.out.println("Ready to accept new Client");
		Socket socket = server_socket.accept();
		ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

		String name;
		try {
			Object firstMessage = is.readObject();
			if (!(firstMessage instanceof String)) {
				throw new ClassNotFoundException();
			}
			name = (String) firstMessage;
			new Client(name, is, os).start();
			System.out.println("Accepted new Client: " + name);
		} catch (ClassNotFoundException e) {
			System.err.println("Could not accept new Client. Wrong Version?");
			e.printStackTrace();
		}

	}

	private void closeClientDealer() throws IOException {
		server_socket.close();
	}

	private void openConnection() throws IOException {
		server_socket = new ServerSocket(port);
	}

}
