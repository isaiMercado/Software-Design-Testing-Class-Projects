package client.controllers.applicationWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.model.Manager;

public class HighlightButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Manager.HighlightButtonActivation();
	}

}
