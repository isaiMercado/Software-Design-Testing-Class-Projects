package client.controllers.applicationWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.model.Manager;
import client.views.windows.ApplicationWindow;
import shared.communicationClasses.*;

public class SubmitButtonListener implements ActionListener {

	private ApplicationWindow appWindow;
	
	public SubmitButtonListener(ApplicationWindow appWindow) {
		this.appWindow = appWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String username = appWindow.getInfoManager().getUsername();
		String password = appWindow.getInfoManager().getPassword();
		int imageId = appWindow.getInfoManager().getImageInfo().getSucceed().getOutput().getBatch_id();
		int rows = appWindow.getInfoManager().getRows();
		int cols = appWindow.getInfoManager().getColumns();
		String host = appWindow.getInfoManager().getHost();
		int port = appWindow.getInfoManager().getPort();
		String values = getValue();
		SubmitBatchInput input = new SubmitBatchInput(username,password,imageId,values);//String user, String password, int batch, String fieldValues
		input.setRowsNeeded(rows);
		input.setColumnsNeeded(cols);
		input.setHost(host);
		input.setPort(port);
		Manager.SubmitBatch(input);
	}

	private String getValue() {
		int rows = appWindow.getInfoManager().getRows();
		int cols = appWindow.getInfoManager().getColumns();
		StringBuilder builder = new StringBuilder();
		for (int a = 0 ; a < rows ; a++) {
			for (int b = 0 ; b < cols ; b++) {
				builder.append(appWindow.getInfoManager().getCellInformation()[a][b]);
				if (b < cols - 1)
					builder.append(",");
			}
			if (a < rows - 1)
				builder.append(";");
		}
		System.out.println(builder.toString());
		return builder.toString();
	}

}
