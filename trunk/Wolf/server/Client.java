package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Client extends Thread {
	private ObjectInputStream is;
	private ObjectOutputStream os;
	private String name;
	private boolean connected;

	public Client(String name, ObjectInputStream is, ObjectOutputStream os) {
		this.name = name;
		this.is = is;
		this.os = os;
		connected = true;
		
	}

	@Override
	public void run() {
		while(true){
			try {
				MessageToServer read = (MessageToServer)is.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	public String getNickname() {
		return name;
	}

	public boolean isConnected() {
		return connected;
	}

	public void trySend(Object o) {
		try {
			os.writeObject(o);
		} catch (IOException e) {
			e.printStackTrace();
			disconnect();
		}
	}

	/*
	 * 
	 */

	private void disconnect() {
		try {
			is.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
			connected = false;
		}
	}

}
