package tuzhms.client;

import java.net.ServerSocket;
import java.net.Socket;

import java.io.IOException;

import tuzhms.constants.Ports;

/**
* <p>Поток серверного подключения</p>
* <p>Поскольку чат пиринговый, необжодимо принимать подключение
	Внутри клиентской программы. Благодаря этому классу создаётся
	2 однонаправленных соединения. Это принимающая сообщения 
	сторона</p>
* <p>Подробное описание идеи в {@link tuzhms.client.Sender}</p>
*
* @author Tuzhilkin Mikhail
* @since 1.0.0
* @see Ports
* @see tuzhms.client.Sender
*/
public class SubServerThread implements Runnable, Ports{
	
	/** Сокет, в который хранятся данные о подключении*/
	private Socket socket = null;

	/**
	* Запуск потока сервера, для установления соединения
	* <b>Добавить ограничение на число подключений</b>
	*
	* @see ServerSocket
	* @see Socket
	* @see Ports#PORT
	*/
	public void run() {
		try {
			ServerSocket server = new ServerSocket(Ports.PORT);
			socket = server.accept();
		} catch(IOException e) {
			System.out.println("---> Ошибка в сервере");
		}
	}

	/**
	* Получение сокета установившегося соединения
	* Данный метод применять только для организации <b>приёма</b>
	* сообщений
	*
	* @return Сокет установившегося соединения
	* @see Socket
	*/
	public Socket getSocket() {
		return socket;
	}
}