package server;

import java.util.concurrent.locks.ReentrantLock;

public class VectorServer {

	private static final int PORT = 60550;// , FREQUENCY = 60;

	ReentrantLock notice;
	Ivory ivory;

	public VectorServer() {

		new VectorServerCommunicator(PORT).start();

		ivory = new Ivory();
		notice = ivory.commence();

		new Thread() {
			public void run() {
				try {

					while (true) {

						notice.wait();
						System.out.println("noticed!");

					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();

		ivory.add(2, 2, new Hole());

		ivory.add(2, 2, new Hole());

	}

	public static void main(String[] args) {
		new VectorServer();
	}
}
