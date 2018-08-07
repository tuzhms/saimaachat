package tuzhms.blocks;

import java.net.Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

//import tuzhms.constants.Ports;
//import tuzhms.gui.MainFrame;
import tuzhms.client.Client;
import tuzhms.client.CalledClient;
import tuzhms.client.SubServerThread;
import tuzhms.client.ReadNameThread;
//import tuzhms.client.ReadMessageThread;
//import tuzhms.client.WriteMessageThread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* <p>
	В данном блоке нужно создать потоки приёма и отправки 
	сообщений, и после передать свои данные и получить 
	данные о подключённом пользователе.
</p>
*
* @author Tuzhilkin Michael
* @since 1.1.0
* @version 1.1.0
*/
public class IntermediateBlock {

	static Logger log = LoggerFactory.getLogger(IntermediateBlock.class);

	//private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	//private MainFrame chatFrame;

	public IntermediateBlock(Client you, CalledClient yourFriend, 
		SubServerThread server, Socket socket) {
		try {
			//Потоки ввода и вывода
			in = new BufferedReader(new InputStreamReader(
				server.getSocket().getInputStream()));
			out = new PrintWriter(
				socket.getOutputStream(), true);
			log.trace("In out good");

			Thread readName = new Thread(new ReadNameThread(in, yourFriend));
			readName.start();
			log.trace("ReadName thread good");
			try {
				Thread.sleep(100);
				log.trace("Sleep good");
			} catch(InterruptedException e) {
				log.error("Error sleep", e);
			}
			log.trace("Ready write");
			out.println(you.getName());	

			new DialogBlock(you, yourFriend, in, out);
		} catch (IOException e) {
			log.error("In out error");
		}
	}
}