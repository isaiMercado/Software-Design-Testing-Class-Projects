package client.views.windows;

import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import client.controllers.downloadWindow.CloseButtonListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SampleImageWindow extends JDialog {
	
	
	
	private DownloadWindow downloadWindow;
	
	
	
	public SampleImageWindow(DownloadWindow window,String imageUrl) throws IOException {
		super(window, "Sample Image", Dialog.ModalityType.DOCUMENT_MODAL);
		downloadWindow = window;
		
		String host = downloadWindow.getAppWindow().getInfoManager().getHost();
		int port = downloadWindow.getAppWindow().getInfoManager().getPort();
		String completeUrl = "http://"+host+":"+port+"/Records/"+imageUrl;
		
		System.out.println(completeUrl);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		setSize(700,500);
		setLocationRelativeTo(downloadWindow);
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setSize(700,500);
		setContentPane(contentPane);
		
		JLabel label = new JLabel();
		label.setLocation(0, 0);
		BufferedImage img = ImageIO.read(new URL(completeUrl));
		label.setSize(700,435);
		Image img2 = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(img2));
		contentPane.add(label);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new CloseButtonListener());
		btnClose.setBounds(293, 437, 117, 25);
		contentPane.add(btnClose);
	}
}
