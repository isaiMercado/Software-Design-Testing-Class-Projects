package shared.modelClasses;

import com.thoughtworks.xstream.annotations.XStreamAlias;



/**
 * Each record element contains the field values for a single record.
 * @author isai
 *
 */
@XStreamAlias("record")
public class Record {
	
	
	
	@XStreamAlias("values")
	private Values values;
	
	
	
	
	/**
	 * Constructor that takes parameters
	 * @param values
	 */
	public Record(Values values) {
		super();
		this.values = values;
	}

	
	
	public Record() {
		values = new Values();
	}
	
	
	
	/**
	 * 
	 * @return values
	 */
	public Values getValues() {
		return values;
	}

	
	
	/**
	 * 
	 * @param values
	 */
	public void setValues(Values values) {
		this.values = values;
	}

	
	
	@Override
	public String toString() {
		return "<record>\n" + 
				values + 
				"</record>\n";
	}
}
