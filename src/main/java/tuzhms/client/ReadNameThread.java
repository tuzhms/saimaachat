package tuzhms.client;

import java.io.IOException;
import java.io.BufferedReader;

/**
*
*
* @author Tuzhilkin Michael
* @since 1.1.0
* @version 1.1.0
*/
public class ReadNameThread implements Runnable {
	private BufferedReader in;
	private CalledClient yourFriend;

	public ReadNameThread(BufferedReader in, CalledClient yourFriend) {
		this.in = in;
		this.yourFriend = yourFriend;
	}

	public void run() {
		try {
			System.out.println("---> ReadNameThread good");
			//Thread.sleep(10000);
			//Thread.dumpStack();
			String name = in.readLine();
			yourFriend.setName(name);
			System.out.println("---> ReadNameThread very good");
		} catch(IOException e) {
			System.out.println("---> Ошибка приёма имени");
		}
	}
}