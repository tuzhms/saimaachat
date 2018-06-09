package tuzhms.client;

import java.net.InetAddress;
//import java.net.Inet4Address;
import java.net.UnknownHostException;

import tuzhms.constants.Title;

/**
* Класс Client содержит сведение о пользователе запустившего
* программу.
*
* @author Tuzhilkin Mikhail
* @since 1.0.0
* @version 1.1.0
*/
public class Client extends User {
	
	/**
	* Локальный ip-адресс текущего клиента.
	*/
	private InetAddress localIp; //Ваш ip адрес
	
	/** 
	* Установить локальный ip
	*
	* @throws UnknownHostExceptions
	*/
	private void setLocalIp() throws UnknownHostException {
		localIp = InetAddress.getLocalHost();
	}

	/** Получить локальный ip */
	public InetAddress getLocalIp() {
		return localIp;
	}

	/** Получить локальный ip */
	public String getLocalIpToString() {
		return localIp.getHostAddress();
	}

	/**
	* Создание клиента. Происходит получение локального ip текущего клиента.
	* @since 1.0.0
	* @version 1.1.0
	* @see InetAddress#getLocalHost()
	* @see InetAddress#getHostAddress()
	* @see UnknownHostException
	*/
	public Client() {
		try {
			setLocalIp();
			setGlobalIp(determineIp());
		} catch (UnknownHostException e) {
			System.out.println(e);
		}
	}

	/** Определение глобатьного ip адреса */
	public String determineIp() {
		//Добавить код
	}

}