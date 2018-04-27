/***************************************************\
|     _____         _   __      __                  |
|    / ___/   /\   | | |  \    /  |   /\     /\     |
|   | |___   /  \  | | |   \  /   |  /  \   /  \    |
|    \__  \  /  \  | | | |\ \/ /| |  /  \   /  \    |
|    ___| | /_  _\ | | | | \  / | | /_  _\ /_  _\   |
|   /_____/   ||   |_| |_|  \/  |_|   ||     ||     |
|                                                   |
\***************************************************/

package tuzhms;

import tuzhms.gui.MainFrame;
import tuzhms.gui.ConnectFrame;

import tuzhms.client.Client;


public class Run {

	public static void main(String[] args) {
		Client you = new Client();
		//ConnectFrame c = new ConnectFrame(you);

		MainFrame frame = new MainFrame();
	}

}
