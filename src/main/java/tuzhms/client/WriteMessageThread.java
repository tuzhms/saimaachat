package tuzhms.client;

import java.io.PrintWriter;
import java.io.IOException;

import tuzhms.gui.MainFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Поток ввода и отправки сообщений
* Объявлять как демона
*
* @author Tuzhilkin Mikhail
* @since 1.0.0
* @version 1.1.0
*/
public class WriteMessageThread implements Runnable {

	static Logger log = LoggerFactory.getLogger(WriteMessageThread.class);

	private MainFrame frame;
	private PrintWriter out;
	private Client you;
	private boolean stoped = false;

	/**
	* Создание эклемпляра потока отправки сообщений
	*
	* @param frame главное окно
	* @param in {@link PrintWriter}
	* @see MainFrame
	* @see PrintWriter
	*/
	public WriteMessageThread(MainFrame frame, PrintWriter out, Client you) {
		this.frame = frame;
		this.out = out;
		this.you = you;
	}

	/** Запуск потока отправки сообщений */
	public void run() {
		log.trace("WriteMessageThread is working");
		out.println("Собеседник готов!");
		while (!stoped) {
			String message = frame.getYourMessage();
			out.println(message);
			frame.addMessage(you.getName() + "@ " + message);
			log.trace("Write good");
		}
	}
}