package tuzhms.client;

import java.net.ServerSocket;
import java.net.Socket;

import java.io.IOException;

import tuzhms.constants.Ports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	static Logger log = LoggerFactory.getLogger(SubServerThread.class);
	
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
		log.info("Start SubServerThread");
		try {
			//тут ограничить кол-во подключений
			ServerSocket server = new ServerSocket(Ports.PORT);
			log.debug("new ServerSocket");
			socket = server.accept();
			log.info("Connect with client good. IP: " + socket.getLocalAddress() + 
				" Port: " + socket.getLocalPort());
		} catch(IOException e) {
			log.error("ServerSocket error", e);
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