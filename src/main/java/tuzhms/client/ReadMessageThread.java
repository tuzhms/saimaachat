package tuzhms.client;

import java.io.BufferedReader;
import java.io.IOException;

import tuzhms.gui.MainFrame;

/**
* Поток вывода сообщений от собеседника
* Объявлять как демона
* @autor Tuzhilkin Mikhail
* @version 1.0.0
*/
class ReadMessageThread implements Runnable {

	private MainFrame frame;
	private BufferedReader in;
	private boolean stoped = false;

	ReadMessageThread(MainFrame frame, BufferedReader in){
		this.frame = frame;
		this.in = in;
	}

	//@Override
	public void run() {
		System.out.println("---> Поток чтения работает");
		while (!stoped) {
			try {
				String message = in.readLine();
				frame.addMessage(message);
			} catch (IOException e) {}
		}
	}
}