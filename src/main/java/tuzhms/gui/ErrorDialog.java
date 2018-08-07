package tuzhms.gui;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Модальное диалоговое окно с текстом ошибки
*
* @author Tuzhilkin Michael
* @version 1.1.0
* @since 1.0.0
* @see JFrame
*/
public class ErrorDialog extends JFrame{

	static Logger log = LoggerFactory.getLogger(ErrorDialog.class);
	
	/**
	* Создание окна ошибки при некорректном событии
	*
	* @param frame родительское окно
	* @param text сообщение ошибки
	* @see Frame
	* @see JDialog
	*/
	public ErrorDialog(Frame frame, String text) {
		log.info(text);
		final JDialog dialog = new JDialog(frame, "Ошибка", true);
		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
		flow.add(new JLabel(text));
		JButton button = new JButton("Ок");

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