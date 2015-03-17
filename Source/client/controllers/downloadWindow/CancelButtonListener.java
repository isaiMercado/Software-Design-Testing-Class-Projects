package client.controllers.downloadWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.model.Manager;
import client.views.windows.DownloadWindow;

public class CancelButtonListener implements ActionListener {



	DownloadWindow downloadWindow;
	
	
	
	public CancelButtonListener(DownloadWindow downloadWindow) {
		this.downloadWindow = downloadWindow;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Manager.CloseDownloadWindow();
	}

}
