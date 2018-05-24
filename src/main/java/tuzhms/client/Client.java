package tuzhms.client;

import java.net.InetAddress;
import java.net.UnknownHostException;

import tuzhms.constants.Title;

/**
* Класс Client содержит сведение о пользователе запустившего
* программу.
*
* @author Tuzhilkin Mikhail
* @version 1.0.0
* @since 1.0.0
*/
public class Client implements Title{
	
	/**
	* Локальный ip-адресс текущего клиента.
	* @version 1.0.0
	* @since 1.0.0
	*/
	private String ip; //Ваш ip адрес

	/**
	* Имя текущего клиента.
	* @version 1.0.0
	* @since 1.0.0
	*/
	private String name; //Ваше имя

	/**
	* Глобальный ip-адресс клиента, к которому нужно подкючиться.
	* @version 1.0.0
	* @since 1.0.0
	* @deprecated
	*/
	private String connectIp; //ip адрес, к которому нужно подключиться

	/**
	* Установить имя текущего клиента.
	* @param name имя подключаемого пользователя
	* @version 1.0.0
	* @since 1.0.0
	*/
	public void setName(String name) {
		this.name = name;
	}

	/**
	* Получить имя текущего клиента.
	* @return имя текущего клиента
	* @version 1.0.0
	* @since 1.0.0
	*/
	public String getName() {
		return name;
	}

	/**
	* Получить локальный ip текущего клиента.
	* @return ip текущего клиента
	* @version 1.0.0
	* @since 1.0.0
	*/
	public String getIp() {
		return ip;
	}

	/**
	* Установить ip клиента, к которому нужно подключиться.
	* @param ip ip-адрес подключаетмого клинта
	* @version 1.0.0
	* @since 1.0.0
	* @deprecated
	*/
	public void setConnectIp(String ip) {
		connectIp = ip;
	}

	/**
	* Получить ip клиента, к которому нужно подключиться.
	* @return ip поключаемого клиента
	* @version 1.0.0
	* @since 1.0.0
	* @deprecated
	*/
	public String getConnectIp() {
		return connectIp;
	}

	/**
	* Создание клиента. Происходит получение локального ip текущего клиента.
	* @version 1.0.0
	* @since 1.0.0
	* @see InetAddress#getLocalHost()
	* @see InetAddress#getHostAddress()
	* @see UnknownHostException
	*/
	public Client() {
		try {
			//Получение локального ip адреса
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			System.out.println(e);
		}
	}

}