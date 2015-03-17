package shared.modelClasses;

import java.util.ArrayList;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;



/**
 * Records is a collection of Record objects.
 * @author isai
 *
 */
@XStreamAlias("records")
public class Records {
	
	
	
	@XStreamImplicit(itemFieldName="record")
	private ArrayList<Record> records;
	
	
	
	/**
	 * Constructor that takes parameters
	 * @param records
	 */
	public Records(ArrayList<Record> records) {
		super();
		this.records = records;
	}

	
	
	public Records() {
		records = new ArrayList<Record>();
	}


	
	/**
	 * 
	 * @return records
	 */
	public ArrayList<Record> getRecords() {
		return records;
	}

	
	
	/**
	 * 
	 * @param records
	 */
	public void setRecords(ArrayList<Record> records) {
		this.records = records;
	}

	
	
	@Override
	public String toString() {
		return "<records>\n" + 
				records + 
				"</records>\n";
	}
}
