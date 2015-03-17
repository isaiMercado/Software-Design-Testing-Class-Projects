package client.views.components;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import client.controllers.tableComponent.TableSelectionListener;
import client.views.windows.ApplicationWindow;

@SuppressWarnings("serial")
public class TableComponent extends JTable {
	
	ApplicationWindow appWindow;
	
	public TableComponent(ApplicationWindow window) {
		appWindow = window;
		int rows = appWindow.getInfoManager().getRows();
		int columns = appWindow.getInfoManager().getColumns();
		
		DefaultTableModel tableModel = new DefaultTableModel(){
			@Override
		    public boolean isCellEditable(int row, int column) {
				if(column == 0)
					return false;
				else
					return true;
		    }
		};
		
		tableModel.addColumn("Record Number");
		for (int a = 0 ; a < rows ; a++) {
			for (int b = 0 ; b < columns ; b++) {
				if (a == 0) {
					tableModel.addColumn(appWindow.getInfoManager().getImageInfo().getSucceed().getOutput().getFields().get(b).getFieldTittle());
				}
			}
			String[] rowData = new String[columns+1];
			rowData[0] = String.valueOf(a+1);
			tableModel.addRow(rowData);
		}
		
		setModel(tableModel);
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		setRowSelectionAllowed(false);
		getTableHeader().setReorderingAllowed(false);
		setSurrendersFocusOnKeystroke(true);
		setCellSelectionEnabled(true);
		this.addFocusListener(new TableSelectionListener(this));
		
	    ListSelectionModel cellSelectionModel = getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    cellSelectionModel.addListSelectionListener(new TableSelectionListener(this));
	    
	}
	
	
	public void setFocus(int row, int col) {
		System.out.println("table Component row" + row);
		System.out.println("table Component col" + col);
		changeSelection(row, col, false, false);
		requestFocus();
	}
}
