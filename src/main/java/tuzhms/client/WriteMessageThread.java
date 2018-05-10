package tuzhms.client;

import java.io.DataOutputStream;
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
	private DataOutputStream out;
	private boolean stoped = false;

	WriteMessageThread(MainFrame frame, DataOutputStream out) {
		this.frame = frame;
		this.out = out;
	}

	//@Ovirride
	public void run() {
		while (!stoped) {
			try {
				String message = frame.getYourMessage();
				out.writeUTF(message);
			} catch (IOException e) {}
		}
	}
}