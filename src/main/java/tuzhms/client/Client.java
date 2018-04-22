package tuzhms.client;

import java.net.InetAddress;
import java.net.UnknownHostException;

import tuzhms.constants.Title;

public class Client implements Title{
	private String ip; //Ваш ip адрес
	private String name; //Ваше имя
	private String connectIp; //ip адрес, к которому нужно подключиться

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public String getIp() {
		return ip;
	}

	public void setConnectIp(String ip) {
		connectIp = ip;
	}

	public Client() {
		try {
			//Получение локального ip адреса
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			System.out.println(e);
		}
	}

}