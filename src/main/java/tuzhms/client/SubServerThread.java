package tuzhms.client;

import java.net.ServerSocket;

import java.io.IOException;

import tuzhms.constants.Ports;

class SubServerThread implements Runnable, Ports{
	
	public void run() {
		try {
			ServerSocket server = new ServerSocket(Ports.PORT);
			server.accept();
			System.out.println("---> "
				+ "Подключение произошло на сервере");
		} catch(IOException e) {
			System.out.println("---> Ошибка в сервере");
		}
	}
}