package client.controllers.loginWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.model.*;
import client.views.windows.LoginWindow;

public class LoginButtonListener implements ActionListener {

	
	
	private LoginWindow loginWindow;
	
	
	
	public LoginButtonListener(LoginWindow loginWindow) {
		this.loginWindow = loginWindow;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Input input = new Input();
		
		input.setUsername(loginWindow.getUsername());
		input.setPassword(loginWindow.getPassword());
		input.setHost(loginWindow.getInfoManager().getHost());
		input.setPort(loginWindow.getInfoManager().getPort());
		
		System.out.println("username: "+loginWindow.getInfoManager().getUsername()+
				           "password: "+loginWindow.getInfoManager().getPassword()+
				           "host :"    +loginWindow.getInfoManager().getHost()+
				           "port: "    +loginWindow.getInfoManager().getPort());
		
		Manager.LoginButton(input);
	}

	
	
	public static class Input {

		private String username;
		private String password;
		private String host;
		private int port;
		
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
	}
}
