package client.controllers.applicationWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.model.Manager;
import client.views.windows.ApplicationWindow;

public class ZoomInListener implements ActionListener {

	
	
	ApplicationWindow appWindow;
	
	
	
	public ZoomInListener(ApplicationWindow appWindow) {
		this.appWindow = appWindow;
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		Manager.ZoomIn(.4);
	}

}
