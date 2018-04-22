package tuzhms;

import tuzhms.gui.MainFrame;
import tuzhms.gui.ConnectFrame;

import tuzhms.client.Client;


public class Run {

	public static void main(String[] args) {
		Client you = new Client();
		ConnectFrame c = new ConnectFrame(you);

		//MainFrame frame = new MainFrame();
	}

}
