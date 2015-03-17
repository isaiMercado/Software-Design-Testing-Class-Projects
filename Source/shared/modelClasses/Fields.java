package shared.modelClasses;

import java.util.ArrayList;

import server.dataBase.DataBaseConnector;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;



/**
 * Fields is a collection of Field objects
 * @author isai
 *
 */
@XStreamAlias("fields")
public class Fields {

	
	
	@XStreamImplicit(itemFieldName="field")
	private ArrayList<Field> fields;
	
	
	
	/**
	 * Constructor that takes parameters
	 * @param fields
	 */
	public Fields(ArrayList<Field> fields) {
		super();
		this.fields = fields;
	}

	
	
	public Fields() {
		fields = new ArrayList<Field>();
	}

	
	
	/**
	 * 
	 * @return fields
	 */
	public ArrayList<Field> getFields() {
		return fields;
	}

	
	
	public Fields getMyFields(int parentid) {
		Fields output = new Fields();
		for(Field field : fields) {
			if(field.getParentid() == parentid) {
			output.getFields().add(field);
			} 
		}
		return output;
	}
	
	
	
	public Fields getAllFields() {
		Fields output = new Fields();
		output.getFields().addAll(fields);
		return output;
	}
	
	
	
	/**
	 * 
	 * @param fields
	 */
	public void setFields(ArrayList<Field> fields) {
		this.fields = fields;
	}

	
	
	@Override
	public String toString() {
		return "<fields>\n" + 
				fields + 
				"</fields>\n";
	}

	
	
	public Fields searchStringInFields(ArrayList<Integer> flds, ArrayList<String> strs) {
		Fields output = null;
		Fields Myflds = getFieldsForSearching(flds);
		for(String string : strs) {
			for(Field field : Myflds.getFields()) {
				Field tried = Search(string,field);
				if (tried != null) {
					if(output == null) {
						output = new Fields();
					}
					output.getFields().add(tried);
				}
			}
		}
		return output;
	}
	
	
	
	public Field Search(String string, Field field) {
		for(int a = 0 ; a < field.parseableString().length()-string.length() ; a++) {
			if(field.parseableString().substring(a, a+string.length()).equalsIgnoreCase(string)) {
				return field;
			}
		}
		return null;
	}



	public Fields getFieldsForSearching(ArrayList<Integer> flds) {
		Fields output = new Fields();
		for(Integer ntgr : flds) {
			output.getFields().add(searchByid(ntgr));
		}
		return output;
	}
	
	
	
	public Field searchByid(int fieldId) {
		Field output = new Field();
		for(Field field : fields) {
			if(field.getId() == fieldId) {
				output = field;
				break;
			}
		}
		return output;
	}
	
	
	
	public Fields searchFieldsFromProject(int parentid) {
		Fields output = new Fields();
		for (Field field : fields) {
			if (field.getParentid() == parentid) {
				output.getFields().add(field);
			}
		}
		return output;
	}
	
	
	
	public void importFieldsFromDB(DataBaseConnector db) {
		try {
			db.startTransaction();
			Fields temp = db.getFieldDAO().getAllRows();
			fields.addAll(temp.getFields());
			db.endTransaction(true);
		} catch(Exception e) {
			e.printStackTrace();
			db.endTransaction(false);
		}
	}
	
	
	
	public int getNumberOfFieldsFromProject(int projectId) {
		int counter = 0;
		for(Field field : fields) {
			if(field.getParentid() == projectId) {
			counter++;
			}
		} 
		return counter;
	}
}
