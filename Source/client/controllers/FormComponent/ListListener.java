package client.controllers.FormComponent;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import client.model.Manager;

public class ListListener implements ListSelectionListener{
	
	
	
	public JList list;
	
	

	public ListListener(JList list) {
		this.list = list;
	}



	@Override
	public void valueChanged(ListSelectionEvent e) {
		//System.out.println("first index " + e.getFirstIndex());
		//System.out.println("last index " + e.getLastIndex());
			Manager.setSelectedRow(list.getSelectedIndex());
			int row = Manager.getApplicationWindow().getInfoManager().getCurrentSelectedRow();
			int col = Manager.getApplicationWindow().getInfoManager().getCurrentSelectedCol();
			Manager.getApplicationWindow().getForm().refreshValues(row, col);
	}

}
