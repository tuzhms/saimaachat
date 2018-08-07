package tuzhms.client;

import java.io.BufferedReader;
import java.io.IOException;

import tuzhms.gui.MainFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Поток чтения сообщений от собеседника
* Объявлять как демона
*
* @author Tuzhilkin Mikhail
* @since 1.0.0
* @version 1.1.0
*/
public class ReadMessageThread implements Runnable {

	static Logger log = LoggerFactory.getLogger(ReadMessageThread.class);

	private MainFrame frame;
	private BufferedReader in;
	private CalledClient yourFriend;
	private boolean stoped = false;

	/**
	* Создание эклемпляра потока чтения сообщений
	*
	* @param frame главное окно
	* @param in {@link BufferedReader}
	* @see MainFrame
	* @see BufferedReader
	*/
	public ReadMessageThread(MainFrame frame, BufferedReader in, CalledClient yourFriend){
		this.frame = frame;
		this.in = in;
		this.yourFriend = yourFriend;
	}

	/** Запуск потока чтения сообщений */
	public void run() {
		
		while (!stoped) {
			try {
				log.debug("Read thread is working");
				String message = in.readLine();
				log.debug("Read good");
				frame.addMessage(yourFriend.getName() + "@ " + message);
			} catch (IOException e) {
				log.error("Error ReadThread", e);
			}
		}
	}
}