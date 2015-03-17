package shared.modelClasses;

import java.util.ArrayList;

import server.dataBase.DataBaseConnector;
import server.javaHTTPserver.HTTPserver;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;



/**
 * Values is a collection of Value objects
 * @author isai
 *
 */
@XStreamAlias("values")
public class Values {

	
	
	@XStreamImplicit(itemFieldName="value")
	private ArrayList<String> xmlvalues;
	@XStreamOmitField
	private transient ArrayList<Value> values;
	
	
	
	public ArrayList<String> getXmlvalues() {
		return xmlvalues;
	}



	public void setXmlvalues(ArrayList<String> xmlvalues) {
		this.xmlvalues = xmlvalues;
	}

	
	
	/**
	 * 
	 * @return values
	 */
	public ArrayList<Value> getValues() {
		return values;
	}

	
	
	/**
	 * 
	 * @param values
	 */
	public void setValues(ArrayList<Value> values) {
		this.values = values;
	}

	
	
	@Override
	public String toString() {
		return "<values>\n" + 
				values +
				"<val>" + xmlvalues + "</val>\n" +
				"</values>\n";
	}

	
	
	/**
	 * Constructor that takes parameters
	 * @param values
	 */
	public Values(ArrayList<Value> values) {
		super();
		this.values = values;
	}



	public Values() {
		this.values = new ArrayList<Value>();
		this.xmlvalues = new ArrayList<String>();
	}

	

	public Values(Values vals) {
		this.values = new ArrayList<Value>(vals.getValues());
	}



	public void importValuesFromDB(DataBaseConnector db) {
		try {
			db.startTransaction();
			Values temp = db.getValueDAO().getAllRows();
			values.addAll(temp.getValues());
			db.endTransaction(true);
		} catch(Exception e) {
			e.printStackTrace();
			db.endTransaction(false);
		} 
	}
	
	
	
	public void addValue(Value value) {
		try {
			System.out.println("in addvalue");
			HTTPserver.getDatabasePersistent().startTransaction();
			values.add(value);
			HTTPserver.getDatabasePersistent().getValueDAO().addRow(value);
			HTTPserver.getDatabasePersistent().endTransaction(true);
		} catch(Exception e) {
			e.printStackTrace();
			HTTPserver.getDatabasePersistent().endTransaction(false);
		}
	}



	public void initializeXmlvalues() {
		this.xmlvalues = new ArrayList<String>();	
	}
}
