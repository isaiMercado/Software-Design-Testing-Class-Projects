package shared.communicationClasses;

import java.util.ArrayList;




/**
 * 
 * SubmitBatchInput encapsulates the input's information for the
 * SubmitBatch function
 *
 */
public class SubmitBatchInput {
	 
	
	
	public static class InputValue {
		
		public String value;
		private int xcoordinate;
		private int ycoordinate;
		private int parentId;
		
		public InputValue(String value, int xcoordinate, int ycoordinate, int parentId) {
			this.value = value;
			this.xcoordinate = xcoordinate;
			this.ycoordinate = ycoordinate;
			this.parentId = parentId;
		}
		
		public InputValue() {
			
		}
		
		public String getValue() {
			return value;
		}
		
		public int getXcoordinate() {
			return xcoordinate;
		}
		
		public int getYcoordinate() {
			return ycoordinate;
		}
		
		public int getParentId() {
			return parentId;
		}	
	}
	
	
	
	private String user;
	private String password;
	private int batch;
	private String field_values_string;
	private ArrayList<ArrayList<InputValue>> field_values;
	private int columnsNeeded;
	private int rowsNeeded;
	private boolean colsAndRowscomplete;
	
	private String host;
	private int port;
	
	
	
	public String getField_values_string() {
		return field_values_string;
	}

	

	public void setField_values_string(String field_values_string) {
		this.field_values_string = field_values_string;
	}
	
	
	
	public boolean isColsAndRowscomplete() {
		return colsAndRowscomplete;
	}

	

	public void setColsAndRowscomplete(boolean colsAndRowscomplete) {
		this.colsAndRowscomplete = colsAndRowscomplete;
	}

	

	public int getColumnsNeeded() {
		return columnsNeeded;
	}

	

	public void setColumnsNeeded(int columnsNeeded) {
		this.columnsNeeded = columnsNeeded;
	}

	

	public int getRowsNeeded() {
		return rowsNeeded;
	}

	

	public void setRowsNeeded(int rowsNeeded) {
		this.rowsNeeded = rowsNeeded;
	}

	

	/**
	 * Constructor that takes parameters
	 * @param user
	 * @param password
	 * @param batch
	 * @param field_values
	 */
	public SubmitBatchInput(String user, String password, int batch, String fieldValues) {
		this.user = user;
		this.password = password;
		this.batch = batch;
		this.field_values_string = fieldValues;
		field_values = new ArrayList<ArrayList<InputValue>>();
		columnsNeeded = 0;
		rowsNeeded = 0;
		colsAndRowscomplete = true;
	}
	
	
	
	public SubmitBatchInput() {
		user = new String();
		password = new String();
		batch = 0;
		field_values_string = new String();
		field_values = new ArrayList<ArrayList<InputValue>>();
		columnsNeeded = 0;
		rowsNeeded = 0;
		colsAndRowscomplete = true;
	}

	

	public void setSubmitBatchInput(String user, String password, int batch, String fieldValues) {
		this.user = user;
		this.password = password;
		this.batch = batch;
		this.field_values_string = fieldValues;
	}
	
	

	public ArrayList<ArrayList<InputValue>> fieldValuesParse(String fieldVals) {
		ArrayList<ArrayList<InputValue>> output = new ArrayList<ArrayList<InputValue>>();
		try {
			colsAndRowscomplete = true;
			int ycoord = 0;
			String[] records = fieldVals.split(";",-1); //it needs the negative 1 to treat space as part of the string
			System.out.println("Arrayrecords length "+records.length);
			if(records.length != rowsNeeded) colsAndRowscomplete = false;
			for(String record : records) {
				ycoord++;
				int xcoord = 0;
				String[] values = record.split(",",-1);
				//System.out.println(ycoord+" Arrayvalues length "+values.length);
				if(values.length != columnsNeeded) colsAndRowscomplete = false;
				ArrayList<InputValue> innerArray = new ArrayList<InputValue>();
				for(String value : values) {
					xcoord++;
					InputValue inputValue = new InputValue(value,xcoord,ycoord,batch);//(String value, int xcoordinate, int ycoordinate, int parentId)
					innerArray.add(inputValue);
				}
				output.add(innerArray);
			//	System.out.print("\n");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return output;
	}
	
	
	
	/**
	 * 
	 * @return user
	 */
	public String getUser() {
		return user;
	}
	
	
	
	/**
	 * 
	 * @param user
	 */
	public void setUser(String user) {
		this.user = user;
	}
	
	
	
	/**
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	
	
	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	/**
	 * 
	 * @return batch
	 */
	public int getBatch() {
		return batch;
	}
	
	
	
	/**
	 * 
	 * @param batch
	 */
	public void setBatch(int batch) {
		this.batch = batch;
	}
	
	
	
	/**
	 * 
	 * @return field_values
	 */
	public ArrayList<ArrayList<InputValue>> getField_values() {
		return field_values;
	}
	
	
	
	/**
	 * 
	 * @param field_values
	 */
	public void setField_values(ArrayList<ArrayList<InputValue>> field_values) {
		this.field_values = field_values;
	}

	
	
	@Override
	public String toString() {
		StringBuilder strb = new StringBuilder();
		strb.append(user+"\n");
		strb.append(password+"\n");
		strb.append(batch+"\n");
		for (ArrayList<InputValue> vals : field_values) {
			for (InputValue val : vals) {
				strb.append(val.getValue()+" ");
			}
			strb.append("\n");
		}
		return strb.toString();
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

	

	public void parseValues() {
		field_values = fieldValuesParse(field_values_string);
	}
}
