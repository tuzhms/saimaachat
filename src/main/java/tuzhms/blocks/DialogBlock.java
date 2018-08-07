package tuzhms.blocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.PrintWriter;
//import java.io.IOException;

//import tuzhms.constants.Ports;
import tuzhms.gui.MainFrame;
import tuzhms.client.Client;
import tuzhms.client.CalledClient;
//import tuzhms.client.SubServerThread;
import tuzhms.client.ReadMessageThread;
import tuzhms.client.WriteMessageThread;

/**
* <p>
	Собственно само общение: приём и передача сообщений.
</p>
*
* @author Tuzhilkin Michael
* @since 1.1.0
* @version 1.1.0
*/
public class DialogBlock {

	static Logger log = LoggerFactory.getLogger(DialogBlock.class);

	private MainFrame chatFrame;

	public DialogBlock(Client you, CalledClient yourFriend, 
		BufferedReader in, PrintWriter out) {
		log.info("Start DialogBlock");

		chatFrame = new MainFrame(you, yourFriend);
		
		//Потоки на чтение и запись, объявлены как демоны
		Thread readThread = new Thread(new ReadMessageThread(chatFrame, in, yourFriend));
		Thread writeThered = new Thread(new WriteMessageThread(chatFrame, out, you));
		readThread.setDaemon(true);
		writeThered.setDaemon(true);
		log.trace("Read and write message thread is ready");

		//Запуск потоков
		readThread.start();
		writeThered.start();

		
	}
}