package tuzhms.client;

/**
* Подключаемый клиент
*
* @author Tuzhilkin Michael
* @since 1.1.0
* @version 1.1.0
*/
public class CalledClient extends User {

	public CalledClient(String name, String ip){
		setName(name);
		setGlobalIp(ip);
	}

}