package server.dataBaseAccessClasses;

import java.sql.*;
import server.dataBase.DataBaseConnector;
import shared.modelClasses.*;



/**
 * Any part of the system that needs to access information about Fields in 
 * the database will go through the FieldDAO class.
 * @author isai
 *
 */
public class FieldDAO {
	
	
	
	private DataBaseConnector database;
	
	
	
	/**
	 * constructor that receives an object DataBase
	 * @param database
	 */
	public FieldDAO(DataBaseConnector database) {
		this.database = database;
	}
	
	
	
	public Field searchRow(String title) {
		Field field = new Field();
		try{
			String query = "SELECT * FROM Field WHERE title = ?";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			preparedStatement.setString(1, title);
			ResultSet resultSet = preparedStatement.executeQuery();
			field.setHelphtml(resultSet.getString("helphtml"));
			field.setId(resultSet.getInt("id"));
			field.setKnowndata(resultSet.getString("knowndata"));
			field.setParentid(resultSet.getInt("parentid"));
			field.setTitle(resultSet.getString("title"));
			field.setWidth(resultSet.getInt("width"));
			field.setXcoord(resultSet.getInt("xcoord")); //do not use getNString method
			field.setFieldCoord(resultSet.getInt("fieldxcoord"));
			resultSet.close();
			preparedStatement.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return field;
	}
	
	
	
	/**
	 * It adds a row to the table in the database;
	 * If successful, it returns true.
	 * If it fails, it returns false.
	 * @param field
	 * @return boolean 
	 */
	public boolean addRow(Field field) {
		try{
			String query = "INSERT INTO Field (helphtml, knowndata, parentid, title, width, xcoord, fieldxcoord) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			preparedStatement.setString(1,field.getHelphtml());
			preparedStatement.setString(2, field.getKnowndata());
			preparedStatement.setInt(3, field.getParentid());
			preparedStatement.setString(4, field.getTitle());
			preparedStatement.setInt(5, field.getWidth());
			preparedStatement.setInt(6, field.getXcoord());
			preparedStatement.setInt(7, field.getFieldCoord());
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
	 * If it succeeds, it sends an object Field with information from that row
	 * If it fails, it outputs FAILED.
	 * @param rowID
	 * @return Field
	 */
	public Field getRow(int rowID) {
		Field field = new Field();
		try{
			String query = "SELECT * FROM Field WHERE id = ?";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, rowID);
			ResultSet resultSet = preparedStatement.executeQuery();
			field.setHelphtml(resultSet.getString("helphtml"));
			field.setId(resultSet.getInt("id"));
			field.setKnowndata(resultSet.getString("knowndata"));
			field.setParentid(resultSet.getInt("parentid"));
			field.setTitle(resultSet.getString("title"));
			field.setWidth(resultSet.getInt("width"));
			field.setXcoord(resultSet.getInt("xcoord")); //do not use getNString method
			field.setFieldCoord(resultSet.getInt("fieldxcoord"));
			resultSet.close();
			preparedStatement.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return field;
	}
	
	
	
	/**
	 * It gets all the rows in the table
	 * If it succeeds, it returns a Fields object that contains several 
	 * Field objects
	 * If it fails, it outputs FAILED
	 * @return Fields
	 */
	public Fields getAllRows() {
		Fields fields = new Fields();
		try{
			String query = "SELECT * FROM Field";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Field field = new Field();
				field.setHelphtml(resultSet.getString("helphtml"));
				field.setId(resultSet.getInt("id"));
				field.setKnowndata(resultSet.getString("knowndata"));
				field.setParentid(resultSet.getInt("parentid"));
				field.setTitle(resultSet.getString("title"));
				field.setWidth(resultSet.getInt("width"));
				field.setXcoord(resultSet.getInt("xcoord")); //do not use getNString method
				field.setFieldCoord(resultSet.getInt("fieldxcoord"));
				fields.getFields().add(field);
			}
			resultSet.close();
			preparedStatement.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return fields;
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
			String query = "DELETE FROM Field WHERE id = ?";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			preparedStatement.setInt(1,rowID);
			preparedStatement.execute();
			preparedStatement.close();
			return true;
		} catch (Exception e) {
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
	public boolean editRow(int rowID, Field field) {
		try {
			String query = "UPDATE Field SET helphtml = ?, knowndata = ?, parentid = ?, title = ?, width = ?, xcoord = ?, fieldxcoord = ? WHERE id = ?";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			preparedStatement.setString(1, field.getHelphtml());
			preparedStatement.setString(2, field.getKnowndata());
			preparedStatement.setInt(3, field.getParentid()); 
			preparedStatement.setString(4, field.getTitle());
			preparedStatement.setInt(5, field.getWidth());
			preparedStatement.setInt(6, field.getXcoord());
			preparedStatement.setInt(7, rowID);
			preparedStatement.setInt(8, field.getFieldCoord());
			preparedStatement.execute();
			preparedStatement.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
}
