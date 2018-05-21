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
		
		while (!stoped) {
			try {
				System.out.println("---> Поток чтения работает");
				String message = in.readLine() + "\n";
				System.out.println("Прочёл");
				frame.addMessage(message);
			} catch (IOException e) {
				System.out.println("---> Error ReadThread");
			}
		}
	}
}