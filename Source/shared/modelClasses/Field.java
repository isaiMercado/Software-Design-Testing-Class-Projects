package shared.modelClasses;
import com.thoughtworks.xstream.annotations.XStreamAlias;



/**
 * Each field element contains the information about one of the project's fields. 
 * @author isai
 *
 */
@XStreamAlias("field")
public class Field {

	
	
	@XStreamAlias("title")
	private String title;
	@XStreamAlias("xcoord")
	private int xcoord;
	@XStreamAlias("width")
	private int width;
	@XStreamAlias("helphtml")
	private String helphtml;
	@XStreamAlias("knowndata")
	private String knowndata;
	private int parentid;
	private int id;
	private int fieldCoord;
	
	
	
	public int getFieldCoord() {
		return fieldCoord;
	}



	public void setFieldCoord(int fieldCoord) {
		this.fieldCoord = fieldCoord;
	}



	/**
	 * Constructor that takes parameters
	 * @param title
	 * @param xcoord
	 * @param width
	 * @param helphtml
	 * @param knowndata
	 * @param parentid
	 * @param colnum
	 * @param id
	 */
	public Field(String helphtml, String knowndata, int parentid, String title, int width, int xcoord) {
		this.title = new String(title);
		this.xcoord = xcoord;
		this.width = width;
		this.helphtml = new String(helphtml);
		this.knowndata = new String(knowndata);
		this.parentid = parentid;
	}
	
	
	
	public Field() {
		this.title = new String();
		this.xcoord = 0;
		this.width = 0;
		this.helphtml = new String();
		this.knowndata = new String();
		this.parentid = 0;
	}
	
	
	
	public Field(Field field) {
		this.title = new String(field.title);
		this.xcoord = field.xcoord;
		this.width = field.width;
		this.helphtml = new String(field.helphtml);
		this.knowndata = new String(field.knowndata);
		this.parentid = field.parentid;
		this.id = field.getId();
	}


	
	/**
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	
	
	
	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	/**
	 * 
	 * @return xcoord
	 */
	public int getXcoord() {
		return xcoord;
	}
	
	
	
	/**
	 * 
	 * @param xcoord
	 */
	public void setXcoord(int xcoord) {
		this.xcoord = xcoord;
	}
	
	
	
	/**
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}
	
	
	
	/**
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	
	
	/**
	 * 
	 * @return helphtml
	 */
	public String getHelphtml() {
		return helphtml;
	}
	
	
	
	/**
	 * 
	 * @param helphtml
	 */
	public void setHelphtml(String helphtml) {
		this.helphtml = helphtml;
	}
	
	
	
	/**
	 * 
	 * @return knowndata
	 */
	public String getKnowndata() {
		return knowndata;
	}
	
	
	
	/**
	 * 
	 * @param knowndata
	 */
	public void setKnowndata(String knowndata) {
		this.knowndata = knowndata;
	}
	
	
	
	/**
	 * 
	 * @return parentid
	 */
	public int getParentid() {
		return parentid;
	}
	
	
	
	/**
	 * 
	 * @param parentid
	 */
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	
	
	
	/**
	 * id
	 * @return
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
	
	
	
	@Override
	public String toString() {
		return "<field>\n" +
				"<title>" + title + "</title>\n" +
				"<xcoord>" + xcoord + "</xcoord>\n" +
				"<width>" + width + "</width>\n" +
				"<helphtml>" + helphtml + "</helphtml>\n" +
				"<knowndata>" + knowndata + "</knowndata>\n" +
				"<parentid>" + parentid + "</parentid>\n" + 
				"<id>" + id + "</id>\n" +
				"</field>\n";
	}
	
	
	
	public String parseableString() {
		return  title+" "+xcoord+" "+width+" "+helphtml+" "+knowndata+" "+parentid+" "+id;
	}
	
}
