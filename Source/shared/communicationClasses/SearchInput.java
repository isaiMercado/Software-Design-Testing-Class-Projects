package shared.communicationClasses;

import java.util.ArrayList;
import java.util.Scanner;



/**
 * 
 * SearchInput encapsulates the input's information for the
 * Search function
 *
 */
public class SearchInput {
	
	
	
	private String user;
	private String password;
	private ArrayList<Integer> fields;
	private ArrayList<String> search_values;
	
	private String host;
	private int port;
	
	
	
	/**
	 * Constructor that takes parameters
	 * @param user
	 * @param password
	 * @param fields
	 * @param search_values
	 */
	public SearchInput(String user, String password, String fields, String search_values) {
		super();
		this.user = user;
		this.password = password;
		this.fields = fieldsParse(fields);
		this.search_values = valuesParse(search_values);
	}

	
	
	private ArrayList<String> valuesParse(String vals) {
		ArrayList<String> output = new ArrayList<String>();
		try {
			String[] array = vals.split(",");
			for(String string : array) {
				output.add(string);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	
	
	private ArrayList<Integer> fieldsParse(String flds) {
		ArrayList<Integer> output = new ArrayList<Integer>();
		try {
			String[] array = flds.split(",");
			for(String string : array) {
				output.add(Integer.parseInt(string));
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
	 * @return fields
	 */
	public ArrayList<Integer> getFields() {
		return fields;
	}
	
	
	
	/**
	 * 
	 * @param fields
	 */
	public void setFields(ArrayList<Integer> fields) {
		this.fields = fields;
	}
	
	
	
	/**
	 * 
	 * @return search_values
	 */
	public ArrayList<String> getSearch_values() {
		return search_values;
	}
	
	
	
	/**
	 * 
	 * @param search_values
	 */
	public void setSearch_values(ArrayList<String> search_values) {
		this.search_values = search_values;
	}

	
	
	@Override
	public String toString() {
		StringBuilder strb = new StringBuilder();
		strb.append(user+"\n");
		strb.append(password+"\n");
		for (Integer inte : fields) {
			strb.append(inte+" ");
		}
		strb.append("\n");
		for(String str : search_values) {
			strb.append(str+" ");
		}
		strb.append("\n");
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
	
}
