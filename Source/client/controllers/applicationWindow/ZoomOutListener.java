package client.controllers.applicationWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.model.Manager;
import client.views.windows.ApplicationWindow;

public class ZoomOutListener implements ActionListener {

	
	
	ApplicationWindow appWindow;
	
	
	
	public ZoomOutListener(ApplicationWindow appWindow) {
		this.appWindow = appWindow;
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		Manager.ZoomOut(.4);
	}

}