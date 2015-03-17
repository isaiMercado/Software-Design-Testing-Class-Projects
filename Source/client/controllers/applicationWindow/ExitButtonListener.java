package client.controllers.applicationWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.model.Manager;

public class ExitButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Manager.exit();
	}

}
