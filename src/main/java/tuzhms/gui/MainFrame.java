package tuzhms.gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import tuzhms.constants.Title;


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

	/**
	* Конструктор основного окна текстого чата
	*/
	public MainFrame() {
		super(Title.CHAT_NAME);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Поле ввода сообщения
		writeMessageField = new JTextField(25);

		//Настройки переноса в области вывода
		readMessageField = new JTextArea("Облать сообщений", 20, 25);
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

		setSize(320, 390);
		setVisible(true);

	}

	/**
	* Добавление текста в область вывода
	* @param message текст, который нужно добавить в область вывода
	*/
	public void addMessage(String message) {
		//readMessageField.setText(readMessageField.getText() 
		//	+ "\n" + message);
		readMessageField.append(message);
	}

	/** Строка с введённым сообщением */
	public String getYourMessage() {
		return writeMessageField.getText();
	}
}