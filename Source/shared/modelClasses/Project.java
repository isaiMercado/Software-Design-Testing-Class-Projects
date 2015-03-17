package shared.modelClasses;

import com.thoughtworks.xstream.annotations.XStreamAlias;



/**
 * Each project element contains the information about a single project.
 * @author isai
 *
 */
@XStreamAlias("project")
public class Project {

	
	
	@XStreamAlias("title")
	private String title;
	@XStreamAlias("recordsperimage")
	private int recordsperimage;
	@XStreamAlias("firstycoord")
	private int firstycoord;
	@XStreamAlias("recordheight")
	private int recordheight;
	@XStreamAlias("fields")
	private Fields fields;
	@XStreamAlias("images")
	private Images images;
	private int id;
	
	
	
	/**
	 * Constructor that takes parameters
	 * @param title
	 * @param recordsperimage
	 * @param firstycoord
	 * @param recordheight
	 * @param id
	 * @param fields
	 * @param images
	 */
	public Project(String title, int recordsperimage, int firstycoord,
			int recordheight, int id, Fields fields, Images images) {
		super();
		this.title = title;
		this.recordsperimage = recordsperimage;
		this.firstycoord = firstycoord;
		this.recordheight = recordheight;
		this.id = id;
		this.fields = fields;
		this.images = images;
	}
	
	
	
	public Project() {
		this.title = new String();
		this.recordsperimage = 0;
		this.firstycoord = 0;
		this.recordheight = 0;
		this.id = 0;
		this.fields = new Fields();
		this.images = new Images();
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
	 * @return recordsperimage
	 */
	public int getRecordsperimage() {
		return recordsperimage;
	}
	
	
	
	/**
	 * 
	 * @param recordsperimage
	 */
	public void setRecordsperimage(int recordsperimage) {
		this.recordsperimage = recordsperimage;
	}
	
	
	
	/**
	 * 
	 * @return firstycoord
	 */
	public int getFirstycoord() {
		return firstycoord;
	}
	
	
	
	/**
	 * 
	 * @param firstycoord
	 */
	public void setFirstycoord(int firstycoord) {
		this.firstycoord = firstycoord;
	}
	
	
	
	/**
	 * 
	 * @return recordheight
	 */
	public int getRecordheight() {
		return recordheight;
	}
	
	
	
	/**
	 * 
	 * @param recordheight
	 */
	public void setRecordheight(int recordheight) {
		this.recordheight = recordheight;
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
	 * @return fields
	 */
	public Fields getFields() {
		return fields;
	}
	
	
	
	/**
	 * 
	 * @param fields
	 */
	public void setFields(Fields fields) {
		this.fields = fields;
	}
	
	
	
	/**
	 * 
	 * @return images
	 */
	public Images getImages() {
		return images;
	}
	
	
	
	/**
	 * 
	 * @param images
	 */
	public void setImages(Images images) {
		this.images = images;
	}
	
	
	
	@Override
	public String toString() {
		return "<project>\n" +
				"<title>" + title + "</title>\n" +
				"<recordsperimage>" + recordsperimage + "</recordsperimage>\n" +
				"<firstycoord>" + firstycoord + "</firstycoord>\n" +
				"<recordheight>" + recordheight + "</recordheight>\n" +
				"<id>" + id + "</id>\n" +
				fields +
				images + 
				"</project>\n";
	}
}
