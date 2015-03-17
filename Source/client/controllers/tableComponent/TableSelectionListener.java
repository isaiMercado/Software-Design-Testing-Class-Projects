package client.controllers.tableComponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import client.model.Manager;

public class TableSelectionListener implements  ListSelectionListener, FocusListener {



	public JTable table;

	
	
	public TableSelectionListener(JTable table) {
		this.table = table;
	}
	
	
	
	@Override
	public void valueChanged(ListSelectionEvent e) {

	        int row = table.getSelectedRow(); 
	        int col = table.getSelectedColumn();

	        //System.out.println("table listener row" + row);
			//System.out.println("table listener col" + col);
			
			if (col != 0) {
		        Manager.setSelectedRow(row);
		        Manager.setSelectedColumn(col);
			}
			
			//int col = Manager.getApplicationWindow().getInfoManager().getCurrentSelectedCol();
			//int row = Manager.getApplicationWindow().getInfoManager().getCurrentSelectedRow();
			Manager.SetValueinCellInformation((String)table.getModel().getValueAt(row, col));
			System.out.println("table listener col" + col + " row " + row);
	      }



	@Override
	public void focusGained(FocusEvent e) {
	    int row = table.getSelectedRow(); 
        int column = table.getSelectedColumn();

       // System.out.println("table listener row" + row);
		//System.out.println("table listener col" + column);
		
		if (column != 0) {
	        Manager.setSelectedRow(row);
	        Manager.setSelectedColumn(column);
		}
	}



	@Override
	public void focusLost(FocusEvent e) {
		int col = Manager.getApplicationWindow().getInfoManager().getCurrentSelectedCol();
		int row = Manager.getApplicationWindow().getInfoManager().getCurrentSelectedRow();
		Manager.SetValueinCellInformation((String)table.getModel().getValueAt(row, col));
	}



	

	    
}
