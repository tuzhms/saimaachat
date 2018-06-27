package tuzhms.blocks;

import tuzhms.client.Client;
import tuzhms.client.CalledClient;
import tuzhms.client.SubServerThread;

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
public class ConnectBlock{
	private ConnectFrame connectFrame;
	private CalledClient yourFriend;

	public ConnectBlock(Client you, SubServerThread server) {
		yourFriend = new CalledClient();
		//подумать про потоки для остановки блока
		connectFrame = new ConnectFrame(you, yourFriend);
		connectFrame.run();
		connectFrame.waitThread();
		System.out.println("---> End");
	}
}
