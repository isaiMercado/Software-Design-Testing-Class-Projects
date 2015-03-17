package shared.modelClasses;

import com.thoughtworks.xstream.annotations.XStreamAlias;



/**
 * Each image element contains information about one of the project's images. 
 * @author isai
 *
 */
@XStreamAlias("image")
public class Image {
	
	
	
	@XStreamAlias("file")
	private String fileurl;
	@XStreamAlias("records")
	private Records records;
	private int parentid;
	private int id;
	private int userid;
	private boolean isrecorded = false;
	
	
	
	/**
	 * Constructor that takes parameters
	 * @param file
	 * @param parentid
	 * @param id
	 * @param userid
	 * @param isrecorded
	 * @param records
	 */
	public Image(String fileurl, int parentid, int id, int userid,
			boolean isrecorded, Records records) {
		super();
		this.fileurl = fileurl;
		this.parentid = parentid;
		this.id = id;
		this.userid = userid;
		this.isrecorded = isrecorded;
		this.records = records;
	}

	
	
	public Image() {
		this.fileurl = new String();
		this.parentid = 0;
		this.id = 0;
		this.userid = 0;
		this.isrecorded = false;
		this.records = new Records();
	}

	

	/**
	 * 
	 * @return file
	 */
	public String getFileUrl() {
		return fileurl;
	}

	
	
	/**
	 * 
	 * @param file
	 */
	public void setFile(String fileurl) {
		this.fileurl = fileurl;
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
	 * @return userid
	 */
	public int getUserid() {
		return userid;
	}

	
	
	/**
	 * 
	 * @param userid
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	
	
	/**
	 * 
	 * @return isrecorded
	 */
	public boolean getIsrecorded() {
		return isrecorded;
	}

	
	
	/**
	 * 
	 * @param isrecorded
	 */
	public void setIsrecorded(boolean isrecorded) {
		this.isrecorded = isrecorded;
	}

	
	
	/**
	 * 
	 * @return records
	 */
	public Records getRecords() {
		return records;
	}

	
	
	/**
	 * 
	 * @param records
	 */
	public void setRecords(Records records) {
		this.records = records;
	}

	
	
	@Override
	public String toString() {
		return "<image>\n" +
				"<file>" + fileurl + "</file>\n" +
				"<parentid>" + parentid + "</parentid>\n" +
				"<id>" + id + "</id>\n" +
				"<userid>" + userid + "</userid>\n" +
				"<isrecorded>" + isrecorded + "</isrecorded>\n" +
				records + 
				"</image>\n";
	}

}
