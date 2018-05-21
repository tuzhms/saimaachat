package tuzhms.client;

import java.io.PrintWriter;
import java.io.IOException;

import tuzhms.gui.MainFrame;

/**
* Поток ввода и отправки сообщений
* Объявлять как демона
* @autor Tuzhilkin Mikhail
* @version 1.0.0
*/
class WriteMessageThread implements Runnable {

	private MainFrame frame;
	private PrintWriter out;
	private boolean stoped = false;

	WriteMessageThread(MainFrame frame, PrintWriter out) {
		this.frame = frame;
		this.out = out;
	}

	//@Ovirride
	public void run() {
		out.println("Собеседник готов!");
		while (!stoped) {
			String message = frame.getYourMessage();
			out.println(message);
			frame.addMessage(message + "\n");
			System.out.println("---> Отправил");
		}
	}
}