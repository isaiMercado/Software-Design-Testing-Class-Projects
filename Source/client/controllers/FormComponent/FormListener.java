package client.controllers.FormComponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

import client.model.Manager;
import client.views.components.FormComponent;

import java.awt.event.*;

public class FormListener implements FocusListener, ActionListener  {

	
	
	private FormComponent formComponent;
	private JTextField parentTextField;
	private boolean keepReading;
	
	
	public boolean isKeepReading() {
		return keepReading;
	}



	public void setKeepReading(boolean keepReading) {
		this.keepReading = keepReading;
	}



	public FormListener(FormComponent formComponent, JTextField parentTextField) {
		this.formComponent = formComponent;
		this.parentTextField = parentTextField;
	}

	

	@Override
	public void focusGained(FocusEvent e) {
		try {
		
			int columnCounter = 1;
			for(JTextField box : formComponent.getTextBoxes()) {
		      if (parentTextField == box) {
		    	  Manager.setSelectedColumn(columnCounter);
		    	  keepReading = false;
		    	  System.out.println("keppReading false");
		    	  break;
		      }
		      columnCounter++;
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		Manager.SetValueinCellInformation(((JTextField)e.getSource()).getText());
	}

}
