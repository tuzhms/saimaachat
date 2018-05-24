package tuzhms.client;

import java.net.Socket;
import java.net.InetAddress;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

import tuzhms.constants.Ports;
import tuzhms.gui.MainFrame;

/**
* <p>Отправитель сообщений</p>
* <p>В данном классе описан отправитель сообщений, в котором
	запускается серверный поток (в следующих версиях будет 
	перенесён на более ранний этап), ожидающий подключение,
	а так же и подключение к серверу второго пользователя.
	Для реализации пирингового чата пришлось создавать 2 
	однонаправленных соединения (в классическом клиент-серверном
	приложении создайтся 1 двунаправленное) от сокета пользователя
	к сокету сервера.</p>
* <p> Соединение от {@link Socket} предназначено для отправки 
	сообщений, то есть {@link Socket#getOutputStream()} 
	использовать в потоке вывода, а {@link Socket#getInputStream()}
	не использовать вообще</p>
* <p> Соединение от {@link java.net.ServerSocket} предназначено для приёма 
	сообщений. Соответственно наоборот: {@link Socket#getOutputStream()} 
	не использовать вообще, а {@link Socket#getInputStream()}
	использовать в потоке ввода</p>
*
* @author Tuzhilkin Mikhail
* @version 1.0.0
* @since 1.0.0
* @see Socket
* @see Socket#getInputStream()
* @see Socket#getOutputStream()
* @see java.net.ServerSocket
* @see Ports
* @see Client
*/
public class Sender implements Ports {

	private Client you;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private MainFrame chatFrame;

	//Пока не используется
	private boolean stoped = false;

	/** Тригер успешного подключения ко второму пользователю*/
	private boolean socketGood = false;

	/**
	* Конструктор отправителя.
	*
	* @param you объект {@link Client}, в котором хранятся данные о контакте
	* @see Client
	* @see ReadMessageThread
	* @see WriteMessageThread
	* @see SubServerThread
	* @since 1.0.0
	*/
	public Sender(Client you) {
		this.you = you;
		chatFrame = new MainFrame(you);

		//Создание и запуск серверного потока, ожидающего подключение
		SubServerThread subServerThread = new SubServerThread();
		Thread server = new Thread(subServerThread);
		server.start();

		//Долбёжка к подключаемому пользователю
		while (!socketGood) {
			try {
				socket = new Socket(
					InetAddress.getByName(you.getConnectIp()), Ports.PORT);
				System.out.println("---> Socket - good!");
				socketGood = true;
			} catch(IOException e) {
				System.out.println("---> Socket - error!");
			} finally {
				try {
					/* Задержка нужна, чтоб опаздывающий успел подключиться
					до инициализации потоков ввода и вывода пользователя,
					который подключился первым. Иначе возникает ошибка */
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.out.println("---> Socket - nedozhdalsya!");
				}
			} 
			
		}
		
		try {
			//Потоки ввода и вывода
			in = new BufferedReader(new InputStreamReader(
				subServerThread.getSocket().getInputStream()));
			out = new PrintWriter(
				socket.getOutputStream(), true);

			//Потоки на чтение и запись, объявлены как демоны
			Thread readThread = new Thread(new ReadMessageThread(chatFrame, in));
			Thread writeThered = new Thread(
				new WriteMessageThread(chatFrame, out));
			readThread.setDaemon(true);
			writeThered.setDaemon(true);

			//Запуск потоков
			readThread.start();
			writeThered.start();

		} catch (IOException e) {
			System.out.println("---> В сокете проблема!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
	
}