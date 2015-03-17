package client.views.components;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.KeyboardFocusManager;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import client.controllers.FormComponent.FormListener;
import client.controllers.FormComponent.ListListener;
import client.views.windows.ApplicationWindow;

import java.util.ArrayList;

import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class FormComponent extends JScrollPane {
	
	
	private JList<String> list;
	private ArrayList<JTextField> textBoxes;
	private ApplicationWindow appWindow;
	
	
	public FormComponent(ApplicationWindow window) {
		appWindow = window;
		textBoxes = new ArrayList<JTextField>();
		//this.setMinimumSize(new Dimension(150,160));
		//this.setPreferredSize(new Dimension(150,160));
		
		JPanel mainPanel = new JPanel();
		setViewportView(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		
		
		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(100,100));
		leftPanel.setLayout(new GridLayout(1, 0, 0, 0));
		mainPanel.add(leftPanel);
	
		int projects = appWindow.getInfoManager().getRows();
		int fields = appWindow.getInfoManager().getColumns();
		
		JPanel rightPanel = new JPanel();
		mainPanel.add(rightPanel);
		rightPanel.setLayout(new GridLayout(fields, 1, 0, 0)); /// change that 1
		
		//DefaultListModel<String> listModel = new DefaultListModel<String>();
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		for(int a = 0 ; a < projects ; a++) {
			listModel.addElement(String.valueOf(a+1));
		}
		
		list = new JList<String>(listModel);
		list.addListSelectionListener(new ListListener(list));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(projects);
		leftPanel.add(list);
		
		for(int b = 0 ; b < fields ; b++) {
			JPanel temp = new JPanel();
			temp.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 5)); 
			rightPanel.add(temp);
			JLabel lblNewLabel = new JLabel(appWindow.getInfoManager().getImageInfo().getSucceed().getOutput().getFields().get(b).getFieldTittle());
			temp.add(lblNewLabel);
			
			JTextField textField = new JTextField();
			
			//textField.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
			textField.addActionListener(new FormListener(this,textField));
			textField.addFocusListener(new FormListener(this,textField));
			
			//textField.addActionListener(new FormListener(this));
			textField.setColumns(10);
			temp.add(textField);
			textBoxes.add(textField);
		}
	}


	public JList<String> getList() {
		return list;
	}


	public void setList(JList<String> list) {
		this.list = list;
	}


	public ArrayList<JTextField> getTextBoxes() {
		return textBoxes;
	}


	public void setTextBoxes(ArrayList<JTextField> textBoxes) {
		this.textBoxes = textBoxes;
	}


	public void setFocus(int row, int col) {
		list.setSelectedIndex(row);
		//list.requestFocus();
		//textBoxes.get(col).setSelectionEnd(col); array out of bounds
	}


	public void refreshValues(int row, int col) {
		System.out.println("form refreshing row "+row +" col "+col);
		for (int a = 1 ; a <= col ; a++) {
				if (appWindow.getInfoManager().getValueinCellInformation(row, a) != null) {
				System.out.println("column a "+ a + " " + appWindow.getInfoManager().getValueinCellInformation(row, a));
				textBoxes.get(a-1).setText(appWindow.getInfoManager().getValueinCellInformation(row, a));
			} else {
				textBoxes.get(a-1).setText("");
			}
		}
	}
	
	
}
