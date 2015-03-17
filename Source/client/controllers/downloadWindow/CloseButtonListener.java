package client.controllers.downloadWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.model.Manager;
import client.views.windows.DownloadWindow;

public class CloseButtonListener implements ActionListener{


	@Override
	public void actionPerformed(ActionEvent e) {
		Manager.CloseSampleImageWindow();
	}
	
	
}
