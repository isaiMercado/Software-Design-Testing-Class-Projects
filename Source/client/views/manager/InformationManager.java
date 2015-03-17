package client.views.manager;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import client.model.Manager;
import shared.communicationClasses.*;
import shared.communicationClasses.GetFieldsOutput.FieldInfo;
import shared.communicationClasses.GetProjectsOutput.ProjectInfo;

public class InformationManager {
	
	
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private int recordedImages;
	private String host;
	private int port;
	private int sampleImageProjectId;
	private ArrayList<GetProjectsOutput.ProjectInfo> projects;
	private ArrayList<GetFieldsOutput.FieldInfo> fields;
	private DownloadBatchOutput imageInfo; 
	private int rows;
	private int columns;
	private int currentSelectedRow;
	private int currentSelectedCol;
	private String[][] cellInformation;

	public InformationManager() {
		username = new String();
		password = new String();
		firstName = new String();
		lastName = new String();
		recordedImages = 0;
		host = new String();
		port = 0;
		setSampleImageProjectId(0);
		setProjects(new ArrayList<GetProjectsOutput.ProjectInfo>());
		setFields(new ArrayList<GetFieldsOutput.FieldInfo>()); 
	}



	public void SaveOutputStream(String fileName) {
		try {
			System.out.println("writing outputStream to "+fileName);
			FileOutputStream f = new FileOutputStream(fileName);
		    ObjectOutput s = new ObjectOutputStream(f);
		    s.writeObject(username);
		    s.writeObject(password);
		    s.writeObject(firstName);
		    s.writeObject(lastName);
		    s.writeObject(recordedImages);
		    s.writeObject(host);
		    s.writeObject(port);
		    s.writeObject(sampleImageProjectId);
		    s.writeObject(projects);
		    s.writeObject(fields);
		    s.writeObject(imageInfo);
		    s.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public DownloadBatchOutput getImageInfo() {
		return imageInfo;
	}



	public void setImageInfo(DownloadBatchOutput imageInfo) {
		this.imageInfo = imageInfo;
	}



	public void ReadFromStreamFile(String fileName) {
		try {
			System.out.println("reading inputStream from "+fileName);
			FileInputStream in = new FileInputStream(fileName);
		    ObjectInputStream s = new ObjectInputStream(in);
		    username = (String)s.readObject();
		    password = (String)s.readObject();
		    firstName = (String)s.readObject();
		    lastName = (String)s.readObject();
		    recordedImages = (int)s.readObject();
		    host = (String)s.readObject();
		    port = (int)s.readObject();
		    sampleImageProjectId = (int)s.readObject();
		    projects = (ArrayList<ProjectInfo>)s.readObject();
		    fields = (ArrayList<FieldInfo>)s.readObject();
		    imageInfo = (DownloadBatchOutput)s.readObject();
		    
		    String url = imageInfo.getSucceed().getOutput().getImageUrl();
		    System.out.println("url is  "+url);
		    Manager.getApplicationWindow().LoadImage(url);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public int getRecordedImages() {
		return recordedImages;
	}

	
	
	public void setRecordedImages(int recordedImages) {
		this.recordedImages = recordedImages;
	}

	
	
	public String getFirstName() {
		return firstName;
	}
	
	

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	
	public String getLastName() {
		return lastName;
	}

	
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getHost() {
		return host;
	}



	public void setHost(String host) {
		this.host = host;
	}



	public int getPort() {
		return port;
	}



	public void setPort(int port) {
		this.port = port;
	}



	public int getSampleImageProjectId() {
		return sampleImageProjectId;
	}



	public void setSampleImageProjectId(int sampleImageProjectId) {
		this.sampleImageProjectId = sampleImageProjectId;
	}



	public ArrayList<GetProjectsOutput.ProjectInfo> getProjects() {
		return projects;
	}



	public void setProjects(ArrayList<GetProjectsOutput.ProjectInfo> projects) {
		this.projects = projects;
	}



	public ArrayList<GetFieldsOutput.FieldInfo> getFields() {
		return fields;
	}



	public void setFields(ArrayList<GetFieldsOutput.FieldInfo> fields) {
		this.fields = fields;
	}



	public int getRows() {
		return rows;
	}



	public void setRows(int rows) {
		this.rows = rows;
	}



	public int getColumns() {
		return columns;
	}



	public void setColumns(int columns) {
		this.columns = columns;
	}



	public int getCurrentSelectedRow() {
		return currentSelectedRow;
	}



	public void setCurrentSelectedRow(int currentSelectedRow) {
		System.out.println("InfoManager row "+currentSelectedRow);
		this.currentSelectedRow = currentSelectedRow;
		Manager.getApplicationWindow().UpdatingCoordinated_Table_Form_Image();
	}



	public int getCurrentSelectedCol() {
		return currentSelectedCol;
	}



	public void setCurrentSelectedCol(int currentSelectedCol) {
		System.out.println("InfoManager column "+currentSelectedCol);
		this.currentSelectedCol = currentSelectedCol;
		Manager.getApplicationWindow().UpdatingCoordinated_Table_Form_Image();
		Manager.getApplicationWindow().ShowingFieldHelp(currentSelectedCol);
	}



	public String[][] getCellInformation() {
		return cellInformation;
	}



	public void inizializeCellInformation() {
		this.cellInformation = new String[rows][columns];
	}
	
	
	public void setValueinCellInformation(int row, int col, String value) {
		System.out.println("infoManager setting value "+value+" in row "+row +" col "+col);
		cellInformation[row][col-1] = value;
		Manager.getApplicationWindow().setValueInTable(row,col ,value);
		Manager.getApplicationWindow().setValueInForm(row,col,value);
	}
	
	
	public String getValueinCellInformation(int row, int col) {
		return cellInformation[row][col - 1];
	}

}
