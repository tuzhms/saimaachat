package tuzhms.client;

import java.io.PrintWriter;
import java.io.IOException;

import tuzhms.gui.MainFrame;

/**
* Поток ввода и отправки сообщений
* Объявлять как демона
*
* @author Tuzhilkin Mikhail
* @since 1.0.0
*/
public class WriteMessageThread implements Runnable {

	private MainFrame frame;
	private PrintWriter out;
	private boolean stoped = false;

	/**
	* Создание эклемпляра потока отправки сообщений
	*
	* @param frame главное окно
	* @param in {@link PrintWriter}
	* @see MainFrame
	* @see PrintWriter
	*/
	WriteMessageThread(MainFrame frame, PrintWriter out) {
		this.frame = frame;
		this.out = out;
	}

	/** Запуск потока отправки сообщений */
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