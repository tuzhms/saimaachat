package tuzhms.blocks;

import tuzhms.client.Client;
import tuzhms.client.SubServerThread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	static Logger log = LoggerFactory.getLogger(StartBlock.class);

	//Сам клиент
	private Client you;
	
	public StartBlock() {
		/*
		Создание клиента, которого нужно передавать через все блоки
		При создании клиентв ip определяются автоматически
		*/
		you = new Client();

		//Создание и запуск серверного потока, ожидающего подключение
		//Может сделать демона?
		SubServerThread subServerThread = new SubServerThread();
		Thread server = new Thread(subServerThread);
		log.debug("new subServerThread");
		server.start();
		
		log.info("Go to ConnectBlock");
		new ConnectBlock(you, subServerThread);
	}
}