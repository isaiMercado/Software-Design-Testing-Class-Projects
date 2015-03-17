package client.views.components;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import java.awt.GridLayout;

import javax.swing.JTextField;

import java.awt.Color;
import java.net.URL;

import javax.swing.SwingConstants;

import client.views.windows.ApplicationWindow;

@SuppressWarnings("serial")
public class FieldHelpComponent extends JScrollPane {
	
	private JEditorPane txtDd;
	private ApplicationWindow appWindow;
	
	public FieldHelpComponent(ApplicationWindow window) {
		appWindow = window;
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		setViewportView(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		txtDd = new JEditorPane();
		txtDd.setContentType("text/html");
		//txtDd.setHorizontalAlignment(SwingConstants.LEFT);
		txtDd.setBackground(Color.WHITE);
		txtDd.setForeground(Color.BLACK);
		panel.add(txtDd);
		//txtDd.setColumns(10);
	}

	public void setText(String text) {
		try {
		txtDd.setPage(new URL("http://"+appWindow.getInfoManager().getHost()+":"+appWindow.getInfoManager().getPort()+"/Records/"+text));
		revalidate();
		repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getText() {
		return txtDd.getText();
	}
}
