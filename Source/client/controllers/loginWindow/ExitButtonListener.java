package client.controllers.loginWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.model.Manager;
import client.views.windows.LoginWindow;

public class ExitButtonListener implements ActionListener{

	
	
	private LoginWindow loginWindow;
	
	
	
	public ExitButtonListener(LoginWindow window) {
		loginWindow = window;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Manager.exit();
	}

}
