package tuzhms.blocks;

import java.net.Socket;
//import java.net.InetAddress;

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
	private Socket socket;
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
			//System.out.println("---> in out good");

			Thread readName = new Thread(new ReadNameThread(in, yourFriend));
			readName.start();
			//System.out.println("---> run good");
			try {
				Thread.sleep(100);
				//System.out.println("---> sleep good");
			} catch(InterruptedException e) {
				System.out.println("---> Error sleep");
			}
			//System.out.println("---> ready write");
			out.println(you.getName());	

			/*//Потоки на чтение и запись, объявлены как демоны
			Thread readThread = new Thread(new ReadMessageThread(chatFrame, in));
			Thread writeThered = new Thread(
				new WriteMessageThread(chatFrame, out));
			readThread.setDaemon(true);
			writeThered.setDaemon(true);

			//Запуск потоков
			readThread.start();
			writeThered.start();*/

		} catch (IOException e) {
			System.out.println("---> В сокете проблема!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}