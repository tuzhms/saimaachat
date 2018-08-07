package tuzhms.blocks;

import java.net.Socket;

import java.io.IOException;

import tuzhms.client.Client;
import tuzhms.client.CalledClient;
import tuzhms.client.SubServerThread;

import tuzhms.constants.Ports;

import tuzhms.gui.ConnectFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* <p>
	В данном блоке необходимо вывести окно подключения 
	{@link tuzhms.gui.ConnectFrame} для введения пользователем
	данных о подключении, и при корректном введении данных 
	стучать сокетом к подключаемому пользователю, ожидая когда
	он так же введёт данные.
</p>
*
* @author Tuzhilkin Michael
* @since 1.1.0
* @version 1.1.0
*/
public class ConnectBlock implements Ports{

	static Logger log = LoggerFactory.getLogger(ConnectBlock.class);

	private ConnectFrame connectFrame;
	private CalledClient yourFriend;
	private Socket socket;

	//Тригер успешного подключения ко второму пользователю
	private boolean socketGood = false;

	public ConnectBlock(Client you, SubServerThread server) {
		yourFriend = new CalledClient();
		connectFrame = new ConnectFrame(you, yourFriend);
		Thread connectThread = new Thread(connectFrame);
		connectThread.run();
		log.debug("Start wait connectThread");
		connectFrame.waitThread();
		log.debug("End wait connectThread");

		//Долбёжка к подключаемому пользователю
		log.info("Start dolbyezhki");
		while (!socketGood) {
			try {
				socket = new Socket(yourFriend.getGlobalIp(), Ports.PORT);
				log.info("Connect with server good");
				socketGood = true;
			} catch(IOException e) {
				log.error("Connect with server error", e);
			} finally {
				try {
					/* Задержка нужна, чтоб опаздывающий успел подключиться
					до инициализации потоков ввода и вывода пользователя,
					который подключился первым. Иначе возникает ошибка */
					Thread.sleep(500);
				} catch (InterruptedException e) {
					log.error("Sleep error", e);
				}
			} 
			
		}

		log.info("Go to IntermediateBlock");
		new IntermediateBlock(you, yourFriend, server, socket);
	}
}
