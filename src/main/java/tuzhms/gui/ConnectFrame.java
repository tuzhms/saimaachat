package tuzhms.gui;
//Написать документацию
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

public class ConnectFrame extends JFrame{

	//Эти 2 параметра вытащены наружу, чтоб были доступны 
	//в классе обработчика событий
	private JTextField name = new JTextField(10);
	private JTextField connectIp = new JTextField("xxx.xxx.xxx.xxx");

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

	class OkButtonAction implements ActionListener {

		private Client you;

		public OkButtonAction (Client you) {
			this.you = you;
		}

		public void actionPerformed(ActionEvent e) {
			if (correctName()) return;
			if (correctConnectIp()) return;
			//System.out.println(you.getName() + " " + you.getConnectIp());
			new MainFrame(you);
			ConnectFrame.this.dispose();
		}

		//Проверка введёного имени на корректность
		private boolean correctName() {
			if (name.getText().equals("")) {
				errorDialog("Введите имя");
				return true;
			} else {
				you.setName(name.getText());
				return false;
			}
		}

		//Проверка на корректность введённого ip
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
				errorDialog("Введите корректный ip");
				return true;
			}
			you.setConnectIp(connectIp.getText());
			return false;
		}

		//Создание окна ошибки при некорректном событии
		private void errorDialog(String text) {
			final JDialog dialog = new JDialog(ConnectFrame.this, "Ошибка", true);
				dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

				JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
				flow.add(new JLabel(text));
				JButton button = new JButton("Ок!");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dialog.dispose();
					}
				});
				flow.add(button);
				dialog.getContentPane().add(flow);

				dialog.setSize(155, 80);
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
		}

	}


}