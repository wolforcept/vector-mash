package server;

public class VectorServer {

	private static final int PORT = 60550, FREQUENCY = 60;

	public VectorServer() {

		new VectorServerCommunicator(PORT).start();

		new Thread() {
			public void run() {
				try {

					while (true) {
						sleep(1000 / FREQUENCY);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}

	public static void main(String[] args) {
		new VectorServer();
	}
}
