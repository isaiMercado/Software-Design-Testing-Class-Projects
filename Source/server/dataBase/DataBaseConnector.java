package server.dataBase;

import java.sql.*;

import server.dataBaseAccessClasses.*;

public class DataBaseConnector {

	
	
	private Connection connection;
	private FieldDAO fieldDAO;
	private ImageDAO imageDAO;
	private ProjectDAO projectDAO;
	private UserDAO userDAO;
	private ValueDAO valueDAO;

	
	
	public DataBaseConnector() {
		this.connection = null;
		this.fieldDAO = null;
		this.imageDAO = null;
		this.projectDAO = null;
		this.userDAO = null;
		this.valueDAO = null;
	}

	
	
	public void initialize() {
		try {
			final String DRIVER = "org.sqlite.JDBC";
			Class.forName(DRIVER);	// in linux to run this from the terminal you need: java -cp ".:sqlite-jdbc-3.7.2.jar" Main
			//connection = DriverManager.getConnection("jdbc:sqlite:test.sqlite"); //do not forget the file.sqlite after the path. Do not give a directory																	 // erase the path so the test driver can test the project. The path is for the terminal. Just leave:  jdbc:sqlite:test.sqlite	
			this.fieldDAO = new FieldDAO(this);
			this.imageDAO = new ImageDAO(this);
			this.projectDAO = new ProjectDAO(this);
			this.userDAO = new UserDAO(this);
			this.valueDAO = new ValueDAO(this);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void initializeTables() {
		System.out.println("initializing tables");
		try {
			String query1 = "CREATE  TABLE Field(helphtml VARCHAR  ,id INTEGER PRIMARY KEY AUTOINCREMENT  UNIQUE , knowndata VARCHAR  , parentid INTEGER  , title VARCHAR  , width INTEGER  , xcoord INTEGER  , fieldxcoord INTEGER)";
			PreparedStatement ps1 = connection.prepareStatement(query1);
			ps1.execute();
			ps1.close();
			
			String query2 = "CREATE  TABLE Project (firstycoord INTEGER  , id INTEGER PRIMARY KEY AUTOINCREMENT  UNIQUE,recordheight INTEGER  ,recordsperimage INTEGER  , title VARCHAR  )";
			PreparedStatement ps2 = connection.prepareStatement(query2);
			ps2.execute();
			ps2.close();
			
			String query3 = "CREATE  TABLE User (currimageid INTEGER  , email VARCHAR  , firstname VARCHAR  , id INTEGER PRIMARY KEY AUTOINCREMENT  UNIQUE ,indexedrecords INTEGER  , lastname VARCHAR  , password VARCHAR  , username VARCHAR  )";
			PreparedStatement ps3 = connection.prepareStatement(query3);
			ps3.execute();
			ps3.close();
			
			String query4 = "CREATE  TABLE Image (file VARCHAR  , id INTEGER PRIMARY KEY AUTOINCREMENT  UNIQUE ,isrecorded BOOL  , parentid INTEGER  , userid INTEGER  )";
			PreparedStatement ps4 = connection.prepareStatement(query4);
			ps4.execute();
			ps4.close();
			
			String query5 = "CREATE  TABLE Value (id INTEGER PRIMARY KEY AUTOINCREMENT  UNIQUE , parentid INTEGER  , value VARCHAR  , xcoordinate INTEGER  , ycoordinate INTEGER  )";
			PreparedStatement ps5 = connection.prepareStatement(query5);
			ps5.execute();
			ps5.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void clear() {
		System.out.println("clearing tables");
		try {
			String query1 = "DROP TABLE Field";
			PreparedStatement ps1 = connection.prepareStatement(query1);
			ps1.execute();
			ps1.close();
			
			String query2 = "DROP TABLE Image";
			PreparedStatement ps2 = connection.prepareStatement(query2);
			ps2.execute();
			ps2.close();
			
			String query3 = "DROP TABLE Project";
			PreparedStatement ps3 = connection.prepareStatement(query3);
			ps3.execute();
			ps3.close();
			
			String query4 = "DROP TABLE User";
			PreparedStatement ps4 = connection.prepareStatement(query4);
			ps4.execute();
			ps4.close();
			
			String query5 = "DROP TABLE Value";
			PreparedStatement ps5 = connection.prepareStatement(query5);
			ps5.execute();
			ps5.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	public void startTransaction() {
		System.out.println("starting transaction");
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:test.sqlite"); //do we need the root directory???
			connection.setAutoCommit(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void endTransaction(boolean commit) {
		if (connection != null) {
			try {
				if(commit == true) {
					connection.commit();
					System.out.println("transaction successful\n");
				} else {
					connection.rollback();
					System.out.println("transaction unsuccessful\n");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public Connection getConnection() {
		return connection;
	}

	
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	

	public FieldDAO getFieldDAO() {
		return fieldDAO;
	}

	
	
	public void setFieldDAO(FieldDAO fieldDAO) {
		this.fieldDAO = fieldDAO;
	}
	
	

	public ImageDAO getImageDAO() {
		return imageDAO;
	}
	
	

	public void setImageDAO(ImageDAO imageDAO) {
		this.imageDAO = imageDAO;
	}
	
	

	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}
	
	

	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}
	
	

	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	

	public ValueDAO getValueDAO() {
		return valueDAO;
	}

	
	
	public void setValueDAO(ValueDAO valueDAO) {
		this.valueDAO = valueDAO;
	}

	
	
	@Override
	public String toString() {
		return "DataBase [connection=" + connection + ", fieldDAO=" + fieldDAO
				+ ", imageDAO=" + imageDAO + ", projectDAO=" + projectDAO
				+ ", userDAO=" + userDAO + ", valueDAO=" + valueDAO + "]";
	}

	

	
	
	
	
	
}
