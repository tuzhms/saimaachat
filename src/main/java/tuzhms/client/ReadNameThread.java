package tuzhms.client;

import java.io.IOException;
import java.io.BufferedReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Поток для приёма имени подключённого пользователяы
*
* @author Tuzhilkin Michael
* @since 1.1.0
* @version 1.1.0
*/
public class ReadNameThread implements Runnable {

	static Logger log = LoggerFactory.getLogger(ReadNameThread.class);

	private BufferedReader in;
	private CalledClient yourFriend;

	public ReadNameThread(BufferedReader in, CalledClient yourFriend) {
		this.in = in;
		this.yourFriend = yourFriend;
	}

	public void run() {
		try {
			log.trace("ReadNameThread ready read");
			String name = in.readLine();
			yourFriend.setName(name);
			log.trace("ReadNameThread read good: " + name);
		} catch(IOException e) {
			log.error("Raed name error");
		}
	}
}