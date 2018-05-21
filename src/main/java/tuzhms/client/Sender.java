package tuzhms.client;

import java.net.Socket;
import java.net.InetAddress;

import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.io.IOException;

import tuzhms.constants.Ports;
import tuzhms.gui.MainFrame;

/**
* Отправитель сообщений
*
* @autor Tuzhilkin Mikhail
* @version 1.0.0
*/
public class Sender implements Ports {

	private Client you;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private MainFrame chatFrame;
	private boolean stoped = false;
	private boolean socketGood = false;

	public Sender(Client you) {
		this.you = you;
		chatFrame = new MainFrame(you);
		SubServerThread subServerThread = new SubServerThread();
		Thread server = new Thread(subServerThread);
		server.start();
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
					Thread.sleep(200);
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