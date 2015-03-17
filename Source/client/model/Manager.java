package client.model;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

import org.apache.commons.io.IOUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import client.clientCommunicator.ClientCommunicator;
import client.controllers.loginWindow.LoginButtonListener;
import client.views.manager.InformationManager;
import client.views.windows.ApplicationWindow;
import client.views.windows.LoginWindow;
import shared.communicationClasses.*;


public class Manager {
	
	
	
	public static void main(String[] args) {
		String host = "localhost";
		int port = 8090;
		if (args.length >= 2) {
			host = args[0];
			port = Integer.parseInt(args[1]);
		}
		infoManager.setHost(host);
		infoManager.setPort(port);
		loginWindow.setVisible(true);
	}

	
	
	private static LoginWindow loginWindow;
	private static ApplicationWindow applicationWindow;
	private static InformationManager infoManager;
	
	
	
	static {
		infoManager = new InformationManager();
		applicationWindow = new ApplicationWindow(Manager.infoManager);
		loginWindow = new LoginWindow(Manager.infoManager);
	}
	
	
	
	public static void LoginButton(LoginButtonListener.Input input) {
		ValidateUserInput validateUser = new ValidateUserInput(input.getUsername(), input.getPassword());
		validateUser.setHost(input.getHost());
		validateUser.setPort(input.getPort());
		
		try {
			ClientCommunicator.setHost(input.getHost());
			ClientCommunicator.setPort(input.getPort());
			ValidateUserOutput output = ClientCommunicator.ValidateUser(validateUser);
			
			if (output.getFailed() != null) { 
				System.out.println("there is no connection");
				loginWindow.showInvalidConnectionWindow();
			} else if (output.getInvalid() != null) {
				System.out.println("input was invalid");
				loginWindow.showInvalidUserWindow();
			} else if (output.getSucceed() != null) {
				System.out.println("input is valid");
				infoManager.ReadFromStreamFile(output.getSucceed().getUserFirstName()+output.getSucceed().getUserLastName());
				infoManager.setFirstName(output.getSucceed().getUserFirstName());
				infoManager.setLastName(output.getSucceed().getUserLastName());
				infoManager.setRecordedImages(output.getSucceed().getNumRecords());
				loginWindow.showValidUserWindow();
				ToogleWindows();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("there is no connection");
			loginWindow.showInvalidConnectionWindow();
		}
	}

	
	
	private static void ToogleWindows() {
		if (loginWindow.isShowing() == true) {
			loginWindow.setVisible(false);
			applicationWindow.setVisible(true);
		} else {
			loginWindow.setVisible(true);
			applicationWindow.setVisible(false);
		}
	}
	
	
	
	public static void exit() {
		loginWindow.dispose();
		applicationWindow.dispose();
		System.exit(0);
	}



	public static void DownloadBatch() {
		applicationWindow.showDownloadBatchWindow();	
	}
	
	
	public static GetProjectsOutput getProjects() {
		GetProjectsOutput output = new GetProjectsOutput();
		try {
			GetProjectsInput input = new GetProjectsInput(infoManager.getUsername(), infoManager.getPassword());
			input.setHost(infoManager.getHost());
			input.setPort(infoManager.getPort());
			output = ClientCommunicator.GetProjects(input);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return output;
	}



	public static void SampleImage(GetSampleImageInput input) {
		GetSampleImageOutput output = new GetSampleImageOutput();
		try {
			output = ClientCommunicator.GetSampleImage(input);
			System.out.println("id "+input.getProjectid());
			System.out.println("sampleImageUrl "+output.getSucceed().getUrl());
			applicationWindow.OpenSampleWindow(output.getSucceed().getUrl());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}



	public static void CloseDownloadWindow() {
		applicationWindow.CloseDownloadWindow();
	}



	public static void DownloadImage(DownloadBatchInput input) {
		DownloadBatchOutput imageInfo = new DownloadBatchOutput();
		try {
			imageInfo = ClientCommunicator.DownloadBatch(input);
			if (imageInfo.getSucceed() != null) {
				String url = imageInfo.getSucceed().getOutput().getImageUrl();
				infoManager.setImageInfo(imageInfo);
				applicationWindow.getDownloadWindow().dispose();
				applicationWindow.getBtnSubmit().setEnabled(true);
				applicationWindow.LoadImage(url);
			} else {
				applicationWindow.ShowAssignedImageWindow();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public static void ZoomIn(double i) {
		applicationWindow.ZoomIn(i);
		
	}


	public static void ZoomOut(double i) {
		applicationWindow.ZoomOut(i);
		
	}
	

	public static void Logout() {
		ToogleWindows();
		infoManager.SaveOutputStream(infoManager.getFirstName()+infoManager.getLastName());
	}



	public static void CloseSampleImageWindow() {
		applicationWindow.getDownloadWindow().getSampleWindow().dispose();
	}



	public static void SetImageInPanel(BufferedImage image) {
		applicationWindow.setImage(image);
	}



	public static LoginWindow getLoginWindow() {
		return loginWindow;
	}



	public static void setLoginWindow(LoginWindow loginWindow) {
		Manager.loginWindow = loginWindow;
	}



	public static ApplicationWindow getApplicationWindow() {
		return applicationWindow;
	}



	public static void setApplicationWindow(ApplicationWindow applicationWindow) {
		Manager.applicationWindow = applicationWindow;
	}



	public static void InvertImage() {
		applicationWindow.invertImage(infoManager.getImageInfo().getSucceed().getOutput().getImageUrl());
	}



	public static void HighlightButtonActivation() {
		applicationWindow.HighlightButtonActivation();
	}



	public static void setSelectedRow(int row) {
		infoManager.setCurrentSelectedRow(row);
	}



	public static void setSelectedColumn(int column) {
		infoManager.setCurrentSelectedCol(column);
	}
	
	
	
	public static String getFieldHelps(String helpFieldUrl) {
		String output = new String();
		try {
		URL url = new URL("http://"+infoManager.getHost()+":"+infoManager.getPort()+"/Records/"+helpFieldUrl);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.connect();
		output = IOUtils.toString(connection.getInputStream(), "UTF-8");
		connection.getInputStream().close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}
	
	public static void SetValueinCellInformation(String value) {
		int row = infoManager.getCurrentSelectedRow();
		int col = infoManager.getCurrentSelectedCol();
		infoManager.setValueinCellInformation(row, col, value);
	}



	public static void SubmitBatch(SubmitBatchInput input){
		try {
			SubmitBatchOutput output = ClientCommunicator.SubmitBatch(input);
			if (output.getFailed() != null)
				JOptionPane.showMessageDialog(applicationWindow, output.getFailed().toString());
			else if (output.getInvalid() != null)
				JOptionPane.showMessageDialog(applicationWindow, output.getInvalid().toString());
			else if (output.getSucceed() != null) {
				JOptionPane.showMessageDialog(applicationWindow, output.getSucceed().toString());
				applicationWindow.getBtnSubmit().setEnabled(false);
				applicationWindow.clean();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
