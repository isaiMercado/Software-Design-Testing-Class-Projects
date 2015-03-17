package client.controllers.downloadWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import shared.communicationClasses.GetProjectsOutput;
import client.views.windows.DownloadWindow;

public class ComboBoxListener implements ActionListener {

	DownloadWindow downloadWindow;
	
	public ComboBoxListener(DownloadWindow downloadWindow) {
		this.downloadWindow = downloadWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int index = downloadWindow.getComboBox().getSelectedIndex();
		int counter = 0;
		int projectId = 0;
		for(GetProjectsOutput.ProjectInfo project : downloadWindow.getAppWindow().getInfoManager().getProjects()) {
			if (index == counter) {
				projectId = project.getProjectid();
				break;
			}
			counter++;
		}
		downloadWindow.getAppWindow().getInfoManager().setSampleImageProjectId(projectId);
	}

}
