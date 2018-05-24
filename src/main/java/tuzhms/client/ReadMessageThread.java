package tuzhms.client;

import java.io.BufferedReader;
import java.io.IOException;

import tuzhms.gui.MainFrame;

/**
* Поток чтения сообщений от собеседника
* Объявлять как демона
*
* @author Tuzhilkin Mikhail
* @since 1.0.0
*/
public class ReadMessageThread implements Runnable {

	private MainFrame frame;
	private BufferedReader in;
	private boolean stoped = false;

	/**
	* Создание эклемпляра потока чтения сообщений
	*
	* @param frame главное окно
	* @param in {@link BufferedReader}
	* @see MainFrame
	* @see BufferedReader
	*/
	ReadMessageThread(MainFrame frame, BufferedReader in){
		this.frame = frame;
		this.in = in;
	}

	/** Запуск потока чтения сообщений */
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