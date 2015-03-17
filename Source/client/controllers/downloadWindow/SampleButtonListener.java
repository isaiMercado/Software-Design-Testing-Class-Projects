package client.controllers.downloadWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import shared.communicationClasses.*;
import client.model.Manager;
import client.views.windows.DownloadWindow;

public class SampleButtonListener implements ActionListener {

	DownloadWindow downloadWindow;
	
	public SampleButtonListener (DownloadWindow window) {
		downloadWindow = window;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String username = downloadWindow.getAppWindow().getInfoManager().getUsername();
		String password = downloadWindow.getAppWindow().getInfoManager().getPassword();
		int projectId = downloadWindow.getAppWindow().getInfoManager().getSampleImageProjectId();
		GetSampleImageInput input = new GetSampleImageInput(username,password, projectId);
		Manager.SampleImage(input); 
	}

}
