package client.views.windows;

import java.awt.Dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.BoxLayout;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;

import client.controllers.downloadWindow.CancelButtonListener;
import client.controllers.downloadWindow.ComboBoxListener;
import client.controllers.downloadWindow.DownloadButtonListener;
import client.controllers.downloadWindow.SampleButtonListener;
import client.model.Manager;

import java.awt.FlowLayout;

import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;

import shared.communicationClasses.*;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DownloadWindow extends JDialog{
	
	
	
	private ApplicationWindow appWindow;
	SampleImageWindow sampleWindow;
	public SampleImageWindow getSampleWindow() {
		return sampleWindow;
	}



	public void setSampleWindow(SampleImageWindow sampleWindow) {
		this.sampleWindow = sampleWindow;
	}



	private JComboBox<String> comboBox;
	
	public DownloadWindow(ApplicationWindow parent) {
		super(parent, "Download Batch", Dialog.ModalityType.DOCUMENT_MODAL);
		setAppWindow(parent);
		
		getContentPane().setLayout(new GridLayout(2, 0, 0, 0));
		setResizable(false);
		setSize(400,120);
		setLocationRelativeTo(parent);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblProject = new JLabel("Project");
		lblProject.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProject.setBounds(23, 22, 64, 20);
		panel_1.add(lblProject);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ComboBoxListener(this));
		comboBox.setBounds(85, 23, 158, 20);
		populateComboBox();
		panel_1.add(comboBox);
		
		JButton btnViewSample = new JButton("View Sample");
		btnViewSample.addActionListener(new SampleButtonListener(this));
		btnViewSample.setBounds(253, 22, 112, 23);
		panel_1.add(btnViewSample);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new CancelButtonListener(this));
		btnCancel.setBounds(90, 11, 103, 23);
		panel.add(btnCancel);
		
		JButton btnDownload = new JButton("Download");
		btnDownload.addActionListener(new DownloadButtonListener(this));
		btnDownload.setBounds(203, 11, 103, 23);
		panel.add(btnDownload);
	}

	
	
	private void populateComboBox() {
		appWindow.getInfoManager().setProjects(Manager.getProjects().getSucceed().getProjectInfo());
		for(GetProjectsOutput.ProjectInfo item : appWindow.getInfoManager().getProjects()) {
			comboBox.addItem(item.getProjectTitle());	
		}
	}



	public JComboBox<String> getComboBox() {
		return comboBox;
	}



	public void setComboBox(JComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}



	public ApplicationWindow getAppWindow() {
		return appWindow;
	}



	public void setAppWindow(ApplicationWindow appWindow) {
		this.appWindow = appWindow;
	}



	public void OpenSampleWindow(String imageUrl) {
		try{
		sampleWindow = new SampleImageWindow(this, imageUrl);
		sampleWindow.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
