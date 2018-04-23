package tuzhms.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import tuzhms.client.Client;

public class ConnectFrame extends JFrame{

	private JTextField name = new JTextField(10);
	private JTextField connectIp = new JTextField("xxx.xxx.xxx.xxx");
	//JButton okButton = new JButton("Поехали");


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
		setVisible(true);

	}

	class OkButtonAction implements ActionListener {

		private Client you;

		public OkButtonAction (Client you) {
			this.you = you;
		}
		public void actionPerformed(ActionEvent e) {
			you.setName(name.getText());
			you.setConnectIp(connectIp.getText());
			System.out.println(you.getName() + " " + you.getConnectIp());
			new MainFrame(you);
			ConnectFrame.this.dispose();
		}
	}
}