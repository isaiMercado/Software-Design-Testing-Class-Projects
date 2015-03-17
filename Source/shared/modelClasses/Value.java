package shared.modelClasses;



/**
 * Value is the value in a single spot in the table of database
 * @author isai
 *
 */
public class Value {
	
	
	
	private String value;
	private int xcoordinate;
	private int ycoordinate;
	private int id;
	private int parentId;
	private String parentURL;
	private int fieldId;
	private int projectId;
	
	
	
	public int getProjectId() {
		return projectId;
	}



	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}



	public int getFieldId() {
		return fieldId;
	}



	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}



	public String getParentURL() {
		return parentURL;
	}



	public void setParentURL(String parentURL) {
		this.parentURL = parentURL;
	}



	/**
	 * Constructor that takes parameters
	 * @param value
	 * @param xcoordinate
	 * @param ycoordinate
	 * @param id
	 * @param parentId
	 */
	public Value(String value, int xcoordinate, int ycoordinate, int parentId) {
		super();
		this.value = value;
		this.xcoordinate = xcoordinate;
		this.ycoordinate = ycoordinate;
		this.id = 0;
		this.parentId = parentId;
	}
	
	
	
	public Value() {
		this.value = new String();
		this.xcoordinate = 0;
		this.ycoordinate = 0;
		this.id = 0;
		this.parentId = 0;
	}


	
	public Value(Value val) {
		this.value = new String(val.getValue());
		this.xcoordinate = val.getXcoordinate();
		this.ycoordinate = val.getYcoordinate();
		this.id = val.getId();
		this.parentId = val.getParentId();
	}



	/**
	 * 
	 * @return value
	 */
	public String getValue() {
		return value;
	}
	
	
	
	/**
	 * 
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	/**
	 * 
	 * @return xcoordinate
	 */
	public int getXcoordinate() {
		return xcoordinate;
	}
	
	
	
	/**
	 * 
	 * @param xcoordinate
	 */
	public void setXcoordinate(int xcoordinate) {
		this.xcoordinate = xcoordinate;
	}
	
	
	
	/**
	 * 
	 * @return ycoordinate
	 */
	public int getYcoordinate() {
		return ycoordinate;
	}
	
	
	
	/**
	 * 
	 * @param ycoordinate
	 */
	public void setYcoordinate(int ycoordinate) {
		this.ycoordinate = ycoordinate;
	}
	
	
	
	/**
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	
	
	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	/**
	 * 
	 * @return parentId
	 */
	public int getParentId() {
		return parentId;
	}
	
	
	
	/**
	 * 
	 * @param parentId
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	
	
	@Override
	public String toString() {
		return "<value>" + value + "</value>\n" +
				"<xcoordinate>" + xcoordinate + "</xcoordinate>\n" +
				"<ycoordinate>" + ycoordinate + "</ycoordinate>\n" +
				"<id>" + id + "</id>\n" +
				"<parentid>" + parentId + "</parentid>\n";
	}
}
