package tuzhms.client;

import java.net.ServerSocket;
import java.net.Socket;

import java.io.IOException;

import tuzhms.constants.Ports;

class SubServerThread implements Runnable, Ports{
	
	private Socket socket = null;

	public void run() {
		try {
			ServerSocket server = new ServerSocket(Ports.PORT);
			System.out.println("Do pockl");
			socket = server.accept();
			System.out.println("---> " + socket.toString());
		} catch(IOException e) {
			System.out.println("---> Ошибка в сервере");
		}
	}

	public Socket getSocket() {
		return socket;
	}
}