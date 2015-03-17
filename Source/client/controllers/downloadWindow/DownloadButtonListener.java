package client.controllers.downloadWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import shared.communicationClasses.*;
import client.model.Manager;
import client.views.windows.DownloadWindow;

public class DownloadButtonListener implements ActionListener{

	
	
	DownloadWindow downloadWindow;
	
	
	
	public DownloadButtonListener(DownloadWindow downloadWindow) {
		this.downloadWindow = downloadWindow;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String username = downloadWindow.getAppWindow().getInfoManager().getUsername();
		String password = downloadWindow.getAppWindow().getInfoManager().getPassword();
		int projectId = downloadWindow.getAppWindow().getInfoManager().getSampleImageProjectId();
		System.out.println("username "+username+" password "+password+" projectId "+projectId);
		DownloadBatchInput input = new DownloadBatchInput(username, password, projectId);
		input.setHost(downloadWindow.getAppWindow().getInfoManager().getHost());
		input.setPort(downloadWindow.getAppWindow().getInfoManager().getPort());
		Manager.DownloadImage(input);
	}

}
