package tuzhms.gui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import tuzhms.constants.Title;

import tuzhms.client.Client;
import tuzhms.client.CalledClient;

/**
* Основное окно текстового чата
* В дальнейшем сделать интерфейс с методами из этого класса,
* чтоб можно было развивать интерфейс окна.
*
* @author Tuzhilkin Michael
* @since 1.0.0
* @version 1.1.0
* @see JFrame
* @see Title
*/
public class MainFrame extends JFrame implements Title{

	static Logger log = LoggerFactory.getLogger(MainFrame.class);
	
	/** Поле для ввода сообщений */
	private JTextField writeMessageField;

	/** Область для вывода всех сообщений */
	private JTextArea readMessageField;
	
	/** Текущий клиент */
	private Client you;

	/**
	* Конструктор основного окна текстого чата
	*
	* @param you объект {@link Client}, в котором хранятся данные о контакте
	* @see Client
	* @since 1.0.0
	*/
	public MainFrame(Client you, CalledClient yourFriend) {
		super(Title.CHAT_NAME + ": " + yourFriend.getName());
		this.you = you;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Поле ввода сообщения
		writeMessageField = new JTextField(25);

		//Настройки переноса в области вывода
		readMessageField = new JTextArea("Облать сообщений\n", 20, 25);
		readMessageField.setLineWrap(true);
		readMessageField.setWrapStyleWord(true);

		//Скролл панель для вывода сообщений
		JScrollPane readMessageScrollPane = new JScrollPane(readMessageField);
		readMessageScrollPane.setVerticalScrollBar(
			readMessageScrollPane.createVerticalScrollBar());

		JPanel contents = new JPanel();
		contents.add(readMessageScrollPane, BorderLayout.CENTER);
		contents.add(writeMessageField, BorderLayout.SOUTH);
		setContentPane(contents);

		writeMessageField.addKeyListener(new enterPressed());

		setSize(320, 390);
		setVisible(true);
		log.debug("MainFrame visible");
	}

	/**
	* Отправка сообщения при нажатии клавиши Enter
	*
	* @since 1.0.0
	* @see KeyListener
	* @see MainFrame#wakeThread()
	*/
	class enterPressed implements KeyListener {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == e.VK_ENTER) {
				//Пробуждение потока отправки сообщения
				wakeThread();
			}
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}

	}

	/**
	* Добавление текста в область вывода сообщений
	* <b>В дальнейшем добавить в интерфейс</b>
	*
	* @param message текст, который нужно добавить в область вывода
	* @since 1.0.0
	*/
	public synchronized void addMessage(String message) {
		log.debug(message);
		readMessageField.append(message + "\n");
	}

	/**
	* <p>Получение текста введённого в строке отправки сообщений</p>
	* <p><b>В дальнейшем добавить в интерфейс</b></p>
	* <p>Данный метод должен запускаться из потока отправки сообщений 
		{@link tuzhms.client.WriteMessageThread}.
		Поток отправки ожидает пока пользователь нажмёт Enter, после 
		чего введённое сообщение отправиться, а поле ввода сообщений 
		отчистится. Для пробуждения потока отправки сообщений нужно
		в действие на нажатие кнопки выполнить метод 
		{@link MainFrame#wakeThread()}</p>
	*
	* @return Сообщение из строки отправки сообщений
	* @since 1.0.0
	* @version 1.1.0
	* @see MainFrame#wakeThread()
	* @see tuzhms.client.WriteMessageThread
	*/
	public synchronized String getYourMessage() {
		try {
			wait();
		} catch (InterruptedException e) {
			log.error("Error wait", e);
		}
		String yourMessage = writeMessageField.getText();
		writeMessageField.setText("");
		return yourMessage;
	}

	/**
	* <p>Данный метод пробуждает поток отправки сообщений, остановленный
		в методе {@link MainFrame#getYourMessage()}</p>
	* <p><b>В дальнейшем добавить в интерфейс</b></p>
	*
	* @since 1.0.0
	* @see MainFrame#getYourMessage()
	*/
	public synchronized void wakeThread() {
		notify();
	}
}