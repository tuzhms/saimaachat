package tuzhms.gui;

import javax.swing.JFrame;
import tuzhms.constants.Title;
import java.awt.Window;

import tuzhms.client.Client;

public class MainFrame extends JFrame implements Title{
	public MainFrame(Client you) {
		super(Title.CHAT_NAME);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 600);
		setVisible(true);

	}
}