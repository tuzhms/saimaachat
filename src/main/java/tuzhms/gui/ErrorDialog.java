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

public class ErrorDialog extends JFrame{
	
	//Создание окна ошибки при некорректном событии
		public ErrorDialog(Frame frame, String text) {
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