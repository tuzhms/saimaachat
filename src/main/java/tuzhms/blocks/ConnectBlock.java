package tuzhms.blocks;

import java.net.Socket;

import java.io.IOException;

import tuzhms.client.Client;
import tuzhms.client.CalledClient;
import tuzhms.client.SubServerThread;

import tuzhms.constants.Ports;

import tuzhms.gui.ConnectFrame;

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
		connectFrame.waitThread();
		//System.out.println("---> End");
		//System.out.println(connectThread.isInterrupted());
		//System.out.println(yourFriend.getGlobalIp());

		//Долбёжка к подключаемому пользователю
		while (!socketGood) {
			try {
				socket = new Socket(yourFriend.getGlobalIp(), Ports.PORT);
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

		new IntermediateBlock(you, yourFriend, server);
	}
}
