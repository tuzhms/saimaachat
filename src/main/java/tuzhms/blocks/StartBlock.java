package tuzhms.blocks;

import tuzhms.client.Client;
import tuzhms.client.SubServerThread;


/**
* <p>
	В данном блоке должно быть реализовано всё, что необходимо
	выполнить до введения пользователём данных о продключении.
	К этому относится: получение локального и глобального
	ip-адресов клиента, <s>передача своих данных на сервер</s>, 
	создание сервер-сокета для подключения, приветственное 
	окно.
</p>
*
* @author Tuzhilkin Michael
* @since 1.1.0
* @version 1.1.0
*/
public class StartBlock {
	//Сам клиент
	private Client you;
	
	public StartBlock() {
		/*
		Создание клиента, которого нужно передавать через все блоки
		При создании клиентв ip определяются автоматически
		*/
		you = Client();

		//Создание и запуск серверного потока, ожидающего подключение
		SubServerThread subServerThread = new SubServerThread();
		Thread server = new Thread(subServerThread);
		server.start();
		
		new ConnectBlock(you, subServerThread);
	}
}