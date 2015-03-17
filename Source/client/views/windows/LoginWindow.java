package client.views.windows;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPasswordField;

import client.controllers.loginWindow.ExitButtonListener;
import client.controllers.loginWindow.LoginButtonListener;
import client.views.manager.InformationManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LoginWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	
	
	
	private JTextField textField;
	private JPasswordField passwordField;
	private InformationManager infoManager;
	
	
	
	public InformationManager getInfoManager() {
		return infoManager;
	}

	
	
	public void setInfoManager(InformationManager infoManager) {
		this.infoManager = infoManager;
	}


	
	public LoginWindow(InformationManager manager) {
		infoManager = manager;
		initialize();
	}

	
	
	private void initialize() {
		try{
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new GridLayout(4, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblWelcomeToSuper = new JLabel("WELCOME TO SUPER IMAGE INDEXER");
		lblWelcomeToSuper.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(lblWelcomeToSuper);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUserName.setBounds(10, 11, 86, 43);
		panel_2.add(lblUserName);
		
		textField = new JTextField();
		textField.setBounds(106, 15, 243, 39);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(10, 11, 71, 43);
		panel_3.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(107, 11, 243, 43);
		panel_3.add(passwordField);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new LoginButtonListener(this));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(113, 11, 90, 43);
		panel.add(btnNewButton);

		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ExitButtonListener(this));
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExit.setBounds(236, 11, 90, 43);
		panel.add(btnExit);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	public void showInvalidConnectionWindow() {
			JOptionPane.showMessageDialog(null,"There is no connection");
	}

	
	
	public void showInvalidUserWindow() {
			JOptionPane.showMessageDialog(null,"Invalid username or password");
	}

	
	
	public void showValidUserWindow() {
			JOptionPane.showMessageDialog(null,"Welcome "+infoManager.getFirstName()+" "+infoManager.getLastName()+" you have indexed "+infoManager.getRecordedImages()+" records");
	}
	
	

	public String getUsername() {
		infoManager.setUsername(textField.getText());
		return textField.getText();
	}

	
	
	public String getPassword() {
		infoManager.setPassword(passwordField.getText());
		return passwordField.getText();
	}


	
}
