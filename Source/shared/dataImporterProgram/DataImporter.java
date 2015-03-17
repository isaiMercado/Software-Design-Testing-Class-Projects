package shared.dataImporterProgram;
import java.io.File;
import server.dataBase.DataBaseConnector;
import shared.modelClasses.*;
import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.io.*;

public class DataImporter {

	
	
	private DataBaseConnector db;
	
	
	
	public DataImporter() {
		db = new DataBaseConnector();
		db.initialize(); // do not forget to start connection because new database does not do anything
	}
	
	
	
	public static void main(String[] args) {
		new DataImporter().XStreamImporter(args[0]);
	}
	
	
	
	public boolean XStreamImporter(String address) {
		try {
		/*
		 * (**APACHE**)
		 */
		File xmlFile = new File(address);
		File dest = new File("Records");
			//	We make sure that the directory we are copying is not the the destination
			//	directory.  Otherwise, we delete the directories we are about to copy.
			if(!xmlFile.getParentFile().getCanonicalPath().equals(dest.getCanonicalPath()))
				FileUtils.deleteDirectory(dest);
				
			//	Copy the directories (recursively) from our source to our destination.
			if(!xmlFile.getParentFile().getCanonicalPath().equals(dest.getCanonicalPath()))
			FileUtils.copyDirectory(xmlFile.getParentFile(), dest);
		/*
		 * (**APACHE**)
		 */
			
			XStream xstream = new XStream(new DomDriver()); // i had a problem here, but it was fixed by new DomDriver()
			xstream.processAnnotations(IndexerData.class);
			xstream.processAnnotations(Field.class);
			xstream.processAnnotations(Fields.class);
			xstream.processAnnotations(Image.class);
			xstream.processAnnotations(Images.class);
			xstream.processAnnotations(Project.class);
			xstream.processAnnotations(Projects.class);
			xstream.processAnnotations(Record.class);
			xstream.processAnnotations(Records.class);
			xstream.processAnnotations(User.class);
			xstream.processAnnotations(Users.class);
			xstream.processAnnotations(Value.class);
			xstream.processAnnotations(Values.class);
			IndexerData data = (IndexerData)xstream.fromXML(new File(address));
			db.startTransaction();
			db.clear();
			db.initializeTables();
			loadingDataBase(data);
			System.out.println("DataBase's loading successful ");
			db.endTransaction(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			db.endTransaction(false);
		}
		return false;
	}



	private void loadingDataBase(IndexerData data) {
		//System.out.println("loadingDataBase");
		if (data.getUsers() != null) getUsers(data.getUsers());
		if (data.getProjects() != null) getProjects(data.getProjects());
	}

	
	
	private void getProjects(Projects projects) {
		//System.out.println("getProjects");
		for (Project project : projects.getProjects()) {
			getProject(project);
		}
	}

	
	
	private void getProject(Project project) {
		//System.out.println("getProject");
		db.getProjectDAO().addRow(project);
		int projectid = db.getProjectDAO().getLastRowID();
		if (project.getFields() != null) getFields(project.getFields(), projectid);
		if (project.getImages() != null) getImages(project.getImages(), projectid);
	}

	
	
	private void getImages(Images images, int projectid) {
		//System.out.println("getImages");
		for(Image image : images.getImages()) {
			image.setParentid(projectid);
			getImage(image);
		}
	}

	
	
	private void getImage(Image image) {
		//System.out.println("getImage");
		db.getImageDAO().addRow(image);
		int imageid = db.getImageDAO().getLastRowID();
		if(image.getRecords() != null) getRecords(image.getRecords(), imageid);
	}

	
	
	private void getRecords(Records records, int imageid) {
		//System.out.println("getRecords");
		int ycoordinate = 1;
		for(Record record : records.getRecords()) {
			getRecord(record, ycoordinate, imageid);
			ycoordinate++;
		}
	}

	
	
	private void getRecord(Record record, int ycoordinate, int imageid) {
		//System.out.println("getRecord");
		getValues(record.getValues(), ycoordinate, imageid);
	}

	
	
	private void getValues(Values values, int ycoordinate, int imageid) {
		//System.out.println("getValues");
		int xcoordinate = 1;
		for(String string : values.getXmlvalues()) {
			getValue(string, ycoordinate, xcoordinate, imageid);
			xcoordinate++;
		}
	}

	
	
	private void getValue(String string, int ycoordinate, int xcoordinate, int imageid) {
		//System.out.println("getValue");
		Value value = new Value();
		value.setValue(string);
		value.setXcoordinate(xcoordinate);
		value.setYcoordinate(ycoordinate);
		value.setParentId(imageid);
		db.getValueDAO().addRow(value);
	}

	
	
	private void getFields(Fields fields, int projectid) {
		//System.out.println("getFields");
		int fieldCoord = 1;
		for (Field field : fields.getFields()) {
			field.setParentid(projectid);
			field.setFieldCoord(fieldCoord);
			getField(field);
			fieldCoord++;
		}
	}

	
	
	private void getField(Field field) {
		//System.out.println("getField");
		db.getFieldDAO().addRow(field);
	}

	
	
	private void getUsers(Users users) {
		//System.out.println("getUsers");
		for (User user : users.getUsers()) {
			if (user != null) getUser(user);
		}
	}

	
	
	private void getUser(User user) {
		//System.out.println("getUser");
		db.getUserDAO().addRow(user);
	}
}
