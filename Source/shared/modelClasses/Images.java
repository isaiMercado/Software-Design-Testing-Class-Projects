package shared.modelClasses;

import java.util.ArrayList;

import server.dataBase.DataBaseConnector;
import server.javaHTTPserver.HTTPserver;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;



/**
 * Images is a collection of Image objects
 * @author isai
 *
 */
@XStreamAlias("images")
public class Images {

	
	
	@XStreamImplicit(itemFieldName="image")
	private ArrayList<Image> images;
	
	

	/**
	 * Constructor that takes parameters
	 * @param images
	 */
	public Images(ArrayList<Image> images) {
		super();
		this.images = images;
	}

	
	
	public Images() {
		this.images = new ArrayList<Image>();
	}

	
	
	/**
	 * 
	 * @return images
	 */
	public ArrayList<Image> getImages() {
		return images;
	}

	
	
	public Image getSampleImage(int parentid) {
		Image output = null;
		for(Image image : images) {
			if (image.getParentid() == parentid) {
				output = image;
				break;
			}
		}
		return output;
	}
	
	
	
	public Image assignBatchtoUser(int parentid) {
		Image output = null;
		for(Image image : images) {
			if (image.getParentid() == parentid && image.getIsrecorded() == false && image.getUserid() == 0) {
				output = image;
				break;
			}
		}
		return output;
	}
	
	
	
	public Image searchImage(int parentid, int row_num) {
		Image output = null;
		for(Image image : images) {
			if(image.getIsrecorded() == false && image.getParentid() == parentid && image.getId() == row_num) {
				if(output == null) {
					output = new Image();
				}
				output = image;
			}
		}
		return output;
	}
	
	
	
	/**
	 * 
	 * @param images
	 */
	public void setImages(ArrayList<Image> images) {
		this.images = images;
	}

	
	
	@Override
	public String toString() {
		return "<images>" + 
				images + 
				"</images>\n";
	}

	
	
	public void importImagesFromDB(DataBaseConnector db) {
		try {
			db.startTransaction();
			Images temp = db.getImageDAO().getAllRows();
			images.addAll(temp.getImages());
			db.endTransaction(true);
		} catch(Exception e) {
			e.printStackTrace();
			db.endTransaction(false);
		}
		
	}

	
	
	public void updateCurrUser(int userId, int imageId) {
		try {
			HTTPserver.getDatabasePersistent().startTransaction();
			for(Image image : images) {
				if (image.getId() == imageId) {
					image.setUserid(userId);
					HTTPserver.getDatabasePersistent().getImageDAO().editRow(image.getId(), image);
				}
			}
			HTTPserver.getDatabasePersistent().endTransaction(true);
		} catch(Exception e) {
			e.printStackTrace();
			HTTPserver.getDatabasePersistent().endTransaction(false);
		}
	}
	
	
	
	public void updateIsRecorded(int imageId,boolean bool) {
		try {
			HTTPserver.getDatabasePersistent().startTransaction();
			for(Image image : images) {
				if (image.getId() == imageId) {
					image.setIsrecorded(bool);
					HTTPserver.getDatabasePersistent().getImageDAO().editRow(image.getId(), image);
				}
			}
			HTTPserver.getDatabasePersistent().endTransaction(true);
		} catch(Exception e) {
			e.printStackTrace();
			HTTPserver.getDatabasePersistent().endTransaction(false);
		}
	}
	
	
	
	public Image getImage(int imageId) {
		Image img = null;
		for(Image image : images) {
			if(image.getId() == imageId) {
				img = new Image();
				img = image;
			}
		}
		//System.out.println("in get image by id this should be false"+img.getIsrecorded()+" "+img.getFileUrl());
		return img;
	}
}
