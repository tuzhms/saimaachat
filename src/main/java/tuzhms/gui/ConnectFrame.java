package tuzhms.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


import tuzhms.client.Client;

public class ConnectFrame extends JFrame{
	public ConnectFrame(Client you) {
		super("Подключение");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Элементы окна
		JLabel yourIp = new JLabel("Ваш IP: " + you.getIp());
		JLabel nameLabel = new JLabel("Ваше имя: ");
		JTextField name = new JTextField(10);
		JLabel connectIpLabel = new JLabel("Подключиться к IP: ");
		JTextField connectIp = new JTextField("xxx.xxx.xxx.xxx");
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


		pack();
		setVisible(true);

	}
}