package tuzhms.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

import java.lang.Exception;

import tuzhms.client.Client;
import tuzhms.client.Sender;

/**
* Этот класс описывает окно подключения чата.
* В нем указан ваш ip-адрес, а так же поля для ввода имени
* и ip-адреса устройства, к которому необходимо подключиться.
*
* @author Tuzhilkin Mikhail
* @version 1.0.0
* @since 1.0.0
* @see JFrame
*/
public class ConnectFrame extends JFrame{

	/**
	* Поле ввода имени. 
	* Выведено в поля класса, чтоб было доступно для класса кнопки.
	*/
	private JTextField name;

	/**
	* Поле ввода ip-адреса, к которому необходимо подключиться. 
	* Выведено в поля класса, чтоб было доступно для класса кнопки.
	*/
	private JTextField connectIp;

	/**
	* Конструктор окна подключения.
	*
	* @param you объект {@link Client}, в котором хранятся данные о контакте
	* @see Client
	* @since 1.0.0
	*/
	public ConnectFrame(Client you) {
		super("Подключение");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Элементы окна
		JLabel yourIp = new JLabel("Ваш IP: " + you.getIp());
		JLabel nameLabel = new JLabel("Ваше имя: ");
		name = new JTextField(10);
		JLabel connectIpLabel = new JLabel("Подключиться к IP: ");
		connectIp = new JTextField("xxx.xxx.xxx.xxx");
		JButton okButton = new JButton("Поехали");

		//Объявнение GroupLayout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		//Расположение компонентов в вертикальной группе
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addComponent(yourIp)
			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(nameLabel)
				.addComponent(name))
			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(connectIpLabel)
				.addComponent(connectIp))
			.addComponent(okButton));

		//Рассположение компонентов в горизонтальной группе
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.CENTER)
			.addComponent(yourIp)
			.addGroup(layout.createSequentialGroup()
				.addComponent(nameLabel)
				.addComponent(name))
			.addGroup(layout.createSequentialGroup()
				.addComponent(connectIpLabel)
				.addComponent(connectIp))
			.addComponent(okButton));

		//Слушатель кнопки
		ActionListener okButtonAction = new OkButtonAction(you);
		okButton.addActionListener(okButtonAction);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	/**
	* Слушатель нажатия кнопки "Ок".
	* В случае ошибок открывается {@link ErrorDialog}
	*
	* @since 1.0.0
	* @see ActionListener
	* @see ActionEvent
	* @see JButton
	* @see ErrorDialog
	*/
	class OkButtonAction implements ActionListener {

		/** Текущий клиент */
		private Client you;

		/**
		* Конструктор слушателя событий кнопки
		*
		* @param you текущий клиент
		* @see Client
		*/
		public OkButtonAction (Client you) {
			this.you = you;
		}

		/**
		* Действие при нажатии кнопки Ок.
		* Проверяется на корректность введённое имя и ip,
		* закрывается окно и запускается {@link Sender}
		*
		* @param e событие {@link ActionEvent}
		* @see Sender
		* @see ActionEvent
		* @see OkButtonAction#correctName()
		* @see OkButtonAction#correctConnectIp()
		*/
		public void actionPerformed(ActionEvent e) {
			if (!correctName()) return;
			if (!correctConnectIp()) return;
			new Sender(you);
			ConnectFrame.this.dispose();
		}

		/**
		* Проверка введёного имени на корректность.
		* В случае ошибок открывается {@link ErrorDialog}
		*
		* @return true - если имя не пустое
		* @see Client
		* @see Client#setName()
		* @see ErrorDialog
		*/
		private boolean correctName() {
			if (name.getText().equals("")) {
				new ErrorDialog(ConnectFrame.this, "Введите имя");
				return false;
			} else {
				you.setName(name.getText());
				return true;
			}
		}

		/**
		* <p>Проверка на корректность введённого ip.</p>
		* <p>В случае ошибок открывается {@link ErrorDialog}.</p>
		* <p><b>Переделать через регулярные выражения</b></p>
		*
		* @return true - если ip правильного формата
		* @see Client
		* @see Client#setConnectIp()
		* @see ErrorDialog
		*/
		private boolean correctConnectIp() {
			String[] ip;
			ip = connectIp.getText().split("\\.");
			try {
				int j = 0;
				for (String s: ip) {
					int i = Integer.parseInt(s);
					j++;
					if (i > 255 || i < 0) throw new Exception();
				} 
				if (j != 4) throw new Exception();
			} catch (Exception e) {
				new ErrorDialog(ConnectFrame.this, "Введите корректный ip");
				return false;
			}
			you.setConnectIp(connectIp.getText());
			return true;
		}

		

	}

}