package server.dataBaseAccessClasses;

import java.sql.*;
import server.dataBase.DataBaseConnector;
import shared.modelClasses.*;



/**
 * Any part of the system that needs to access information about Images in 
 * the database will go through the ImageDAO class.
 * @author isai
 *
 */
public class ImageDAO {
	
	
	
	private DataBaseConnector database;
	
	
	
	/**
	 * constructor that receives an object DataBase
	 * @param database
	 */
	public ImageDAO(DataBaseConnector database) {
		super();
		this.database = database;
	}


	
	public Image searchRow(int userid) {
		Image image = new Image();
		try {
			String query = "SELECT * FROM Image WHERE userid = ?";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, userid);
			ResultSet resultSet = preparedStatement.executeQuery();
			image.setFile(resultSet.getString("file"));
			image.setId(resultSet.getInt("id"));
			image.setIsrecorded(resultSet.getBoolean("isrecorded"));
			image.setParentid(resultSet.getInt("parentid"));
			image.setUserid(resultSet.getInt("userid"));
			resultSet.close();
			preparedStatement.close();
		} catch(Exception e) {
			
		}
		return image;
	}
	
	
	
	/**
	 * It adds a row to the table in the database;
	 * If successful, it returns true.
	 * If it fails, it returns false.
	 * @param image
	 * @return boolean 
	 */
	public boolean addRow(Image image) {
		//System.out.println("this image should be false "+image.getIsrecorded()+" "+image.getFileUrl());
		try {
			String query = "INSERT INTO Image (file, isrecorded, parentid, userid) VALUES (?,?,?,?)";
			PreparedStatement preparedStatement  = database.getConnection().prepareStatement(query);
			preparedStatement.setString(1, image.getFileUrl());
			preparedStatement.setBoolean(2, image.getIsrecorded());
			preparedStatement.setInt(3, image.getParentid());
			preparedStatement.setInt(4, image.getUserid());
			preparedStatement.execute();
			preparedStatement.close();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	/**
	 * It returns the specified row.
	 * If it succeeds, it sends an object Image with information from that row
	 * If it fails, it outputs FAILED.
	 * @param rowID
	 * @return Image
	 */
	public Image getRow(int rowID) {
		Image image = new Image();
		try {
			String query = "SELECT * FROM Image WHERE id = ?";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, rowID);
			ResultSet resultSet = preparedStatement.executeQuery();
			image.setFile(resultSet.getString("file"));
			image.setId(resultSet.getInt("id"));
			image.setIsrecorded(resultSet.getBoolean("isrecorded"));
			image.setParentid(resultSet.getInt("parentid"));
			image.setUserid(resultSet.getInt("userid"));
			resultSet.close();
			preparedStatement.close();
		} catch(Exception e) {
			
		}
		return image;
	}
	
	
	
	/**
	 * It gets all the rows in the table
	 * If it succeeds, it returns a Images object that contains several 
	 * Image objects
	 * If it fails, it outputs FAILED
	 * @return Images
	 */
	public Images getAllRows() {
		Images images = new Images();
		try {
			String query = "SELECT * FROM Image";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Image image = new Image();
				image.setFile(resultSet.getString("file"));
				image.setId(resultSet.getInt("id"));
				image.setIsrecorded(resultSet.getBoolean("isrecorded"));
				//System.out.println("in getting all images this should be false "+image.getIsrecorded()+" "+image.getFileUrl());
				image.setParentid(resultSet.getInt("parentid"));
				image.setUserid(resultSet.getInt("userid"));
				images.getImages().add(image);
			}
			resultSet.close();
			preparedStatement.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return images;
	}
	
	
	
	/**
	 * It deletes a row in the table
	 * If it succeeds it returns true.
	 * If it fails it returns false.
	 * @param rowID
	 * @return boolean
	 */
	public boolean deleteRow(int rowID) {
		try {
			String query = "DELETE FROM Image WHERE id = ?";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, rowID);
			preparedStatement.execute();
			preparedStatement.close();
			return true;
		} catch(Exception e) {
		e.printStackTrace();	
		}
		return false;
	}
	
	
	
	/**
	 * It changes a row 
	 * If it succeeds, it returns true.
	 * If it fails, it returns false.
	 * @param rowID
	 * @return boolean
	 */
	public boolean editRow(int rowID, Image image) {
		try {
			String query = "UPDATE Image SET file = ?, isrecorded = ?, parentid = ?, userid = ? WHERE id = ?";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			preparedStatement.setString(1, image.getFileUrl());
			preparedStatement.setBoolean(2, image.getIsrecorded());
			preparedStatement.setInt(3, image.getParentid());
			preparedStatement.setInt(4, image.getUserid());
			preparedStatement.setInt(5, rowID);
			preparedStatement.execute();
			preparedStatement.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	
	
	/**
	 * It gets the database
	 * @return database
	 */
	public DataBaseConnector getDatabase() {
		return database;
	}

	
	
	/**
	 * It sets the database
	 * @param database
	 */
	public void setDatabase(DataBaseConnector database) {
		this.database = database;
	}
	
	
	
	/**
	 * It returns a string of the database
	 */
	public String toString() {
		return "FieldDAO [database=" + database + "]";
	}



	public int getLastRowID() {
		int id = 0;
		try {
			String query = "SELECT LAST_INSERT_ROWID()";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			id = resultSet.getInt(1);
			resultSet.close();
			preparedStatement.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}
