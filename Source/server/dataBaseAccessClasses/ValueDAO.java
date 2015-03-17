package server.dataBaseAccessClasses;

import server.dataBase.DataBaseConnector;
import server.javaHTTPserver.HTTPserver;
import shared.modelClasses.*;
import java.sql.*;



/**
 * Any part of the system that needs to access information about Values in 
 * the database will go through the ValueDAO class.
 * @author isai
 *
 */
public class ValueDAO {
	
	
	
	private DataBaseConnector database;
	
	
	
	/**
	 * constructor that receives an object DataBase
	 * @param database
	 */
	public ValueDAO(DataBaseConnector database) {
		super();
		this.database = database;
	}

	
	
	public Value searchRow(int xcoord, int ycoord, int parentId) {
		Value value = new Value();
		try {
			String query = "SELECT * FROM Value WHERE xcoordinate = ? AND ycoordinate = ? AND parentid = ?";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, xcoord);
			preparedStatement.setInt(2, ycoord);
			preparedStatement.setInt(3, parentId);
			ResultSet resultSet = preparedStatement.executeQuery();
			value.setValue(resultSet.getString("value"));
			value.setXcoordinate(xcoord);
			value.setYcoordinate(ycoord);
			value.setParentId(resultSet.getInt("parentid"));
			value.setId(resultSet.getInt("id"));
			resultSet.close();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	
	
	/**
	 * It adds a row to the table in the database;
	 * If successful, it returns true.
	 * If it fails, it returns false.
	 * @param value
	 * @return boolean 
	 */
	public boolean addRow(Value value) {
		try {
			String query = "INSERT INTO Value (parentid, value, xcoordinate, ycoordinate) VALUES (?,?,?,?)";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, value.getParentId());
			preparedStatement.setString(2, value.getValue());
			preparedStatement.setInt(3, value.getXcoordinate());
			preparedStatement.setInt(4, value.getYcoordinate());
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
	 * If it succeeds, it sends an object Value with information from that row
	 * If it fails, it outputs FAILED.
	 * @param rowID
	 * @return Value
	 */
	public Value getRow(int rowID) {
		Value value = new Value();
		try {
			String query = "SELECT * FROM Value WHERE id = ?";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, rowID);
			ResultSet resultSet = preparedStatement.executeQuery();
			value.setValue(resultSet.getString("value"));
			value.setXcoordinate(resultSet.getInt("xcoordinate"));
			value.setYcoordinate(resultSet.getInt("ycoordinate"));
			value.setParentId(resultSet.getInt("parentid"));
			value.setId(resultSet.getInt("id"));
			resultSet.close();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	
	
	/**
	 * It gets all the rows in the table
	 * If it succeeds, it returns a Values object that contains several 
	 * Field objects
	 * If it fails, it outputs FAILED
	 * @return Values
	 */
	public Values getAllRows() {
		Values values = new Values();
		try {
			String query = "SELECT * FROM Value";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Value value = new Value();
				value.setValue(resultSet.getString("value"));
				value.setXcoordinate(resultSet.getInt("xcoordinate"));
				value.setYcoordinate(resultSet.getInt("ycoordinate"));
				value.setParentId(resultSet.getInt("parentid"));
				value.setId(resultSet.getInt("id"));
				values.getValues().add(value);
			}
			resultSet.close();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return values;
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
			String query = "DELETE FROM Value WHERE id = ?";
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
	 * @param database rowID
	 * @return boolean
	 */
	public boolean editRow(int rowID, Value value) {
		try{
			String query = "UPDATE Value SET parentid = ?, value = ?, xcoordinate = ?, ycoordinate = ? WHERE id = ?";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, value.getParentId());
			preparedStatement.setString(2, value.getValue());
			preparedStatement.setInt(3, value.getXcoordinate());
			preparedStatement.setInt(4, value.getYcoordinate());
			preparedStatement.setInt(5, rowID);
			preparedStatement.execute();
			preparedStatement.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
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
	
	
	
	public Values search(String string, Integer fieldId) {
		System.out.println("in search "+string+" "+fieldId);
		Values values = null;
		try {
			HTTPserver.getDatabasePersistent().startTransaction(); 
			// the internet said that the values in th sqlite query needed to be surrounded by ' but prepared statement did not like that
			String query = "SELECT Image.id, Image.file, Value.ycoordinate, Field.id FROM Image JOIN Field JOIN Value WHERE Field.id = ? AND Value.value = ? COLLATE NOCASE AND Field.fieldxcoord = Value.xcoordinate AND Image.id = Value.parentid AND Field.parentid = Image.parentid";
			PreparedStatement ps = HTTPserver.getDatabasePersistent().getConnection().prepareStatement(query);
			ps.setInt(1, fieldId);
			ps.setString(2, string);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Value value = new Value();
				value.setParentId(rs.getInt(1));
				value.setParentURL(rs.getString(2));
				value.setYcoordinate(rs.getInt(3));
				value.setFieldId(rs.getInt(4));
				if(values == null) {
					values = new Values();
				}
				values.getValues().add(value);
			} 
			rs.close();
			ps.close();
			HTTPserver.getDatabasePersistent().endTransaction(true);
		} catch(Exception e) {
			e.printStackTrace();
			HTTPserver.getDatabasePersistent().endTransaction(false);
		}
		System.out.println("values "+values);
		return values;
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
