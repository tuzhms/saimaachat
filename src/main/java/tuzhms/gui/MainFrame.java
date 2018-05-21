package tuzhms.gui;

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

/**
* Основное окно текстового чата
* @autor Tuzhilkin Michael
* @version 1.0.0
*/
public class MainFrame extends JFrame implements Title{
	
	//Поле для ввода сообщений
	private JTextField writeMessageField;

	//Область для вывода всех сообщений
	private JTextArea readMessageField;

	private Client you;

	/**
	* Конструктор основного окна текстого чата
	*/
	public MainFrame(Client you) {
		super(Title.CHAT_NAME);
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

	}

	class enterPressed implements KeyListener {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == e.VK_ENTER) {
				//System.out.println("---> notify up");
				wakeThread();
				//System.out.println("---> notify down");
			}
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}

	}

	/**
	* Добавление текста в область вывода
	* @param message текст, который нужно добавить в область вывода
	*/
	public synchronized void addMessage(String message) {
		//readMessageField.setText(readMessageField.getText() 
		//	+ "\n" + message);
		readMessageField.append(message);
	}

	/** Строка с введённым сообщением */
	public synchronized String getYourMessage() {
		try {
			//System.out.println("---> Поток вывода ждёт");
			wait();
			//System.out.println("---> Поток вывода работает");
		} catch (InterruptedException e) {
			System.out.println("---> Error wait");
		}
		String yourMessage = you.getName() + "@  "
			+ writeMessageField.getText();
		writeMessageField.setText("");
		return yourMessage;
	}

	//notyify для нажатия на enter
	//разбудить поток
	public synchronized void wakeThread() {
		notify();
	}
}