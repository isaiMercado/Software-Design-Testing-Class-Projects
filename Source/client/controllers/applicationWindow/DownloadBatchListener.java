package client.controllers.applicationWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.model.Manager;
import client.views.windows.ApplicationWindow;

public class DownloadBatchListener implements ActionListener {

	private ApplicationWindow appWindow;
	
	
	public DownloadBatchListener(ApplicationWindow window) {
		appWindow = window;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Manager.DownloadBatch();
	}

}
