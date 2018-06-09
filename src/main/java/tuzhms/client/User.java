package tuzhms.client;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
* @author Tuzhilkin Michael
* @since 1.1.0
* @version 1.1.0
*/
abstract class User {

	private InetAddress globalIp;
	private String name;

	/*protected void setGlobalIp() {
		globalIp = determineIp();
	}*/

	/** 
	* Установить глобальный ip
	*
	* @throws UnknownHostExceptions
	*/
	public void setGlobalIp(String globalIp) 
			throws UnknownHostException {
		this.globalIp = InetAddress.getByName(globalIp);
	}

	/** Получить глобальный ip */
	public InetAddress getGlobalIp() {
		return globalIp;
	}

	/** Получить глобальный ip */
	public String getGlobalIpToString() {
		return globalIp.getHostAddress();
	}

	/** Установить имя клиента */
	public void setName(String name) {
		this.name = name;
	}

	/** Получить имя клиента */
	public String getName() {
		return name;
	}

}