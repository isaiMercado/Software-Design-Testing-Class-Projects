package server.dataBaseAccessClasses;

import server.dataBase.DataBaseConnector;
import shared.modelClasses.*;
import java.sql.*;



/**
 * Any part of the system that needs to access information about Users in 
 * the database will go through the UserDAO class.
 * @author isai
 *
 */
public class UserDAO {
	
	
	
	private DataBaseConnector database;
	
	
	
	/**
	 * constructor that receives an object DataBase
	 * @param database
	 */
	public UserDAO(DataBaseConnector database) {
		super();
		this.database = database;
	}

	
	
	/**
	 * It looks for the user's name and returns an object 
	 * with its information.
	 * @param name
	 * @return User
	 */
	public User searchRow(String firstname) {
		User user = null;
		try{
			user = new User();
			String query = "SELECT * FROM User WHERE firstname = ?";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			preparedStatement.setString(1, firstname);
			ResultSet resultSet = preparedStatement.executeQuery();
			user.setCurrimageid(resultSet.getInt("currimageid"));
			user.setEmail(resultSet.getString("email"));
			user.setFirstname(resultSet.getString("firstname"));
			user.setId(resultSet.getInt("id"));
			user.setIndexedrecords(resultSet.getInt("indexedrecords"));
			user.setLastname(resultSet.getString("lastname"));
			user.setPassword(resultSet.getString("password"));
			user.setUsername(resultSet.getString("username"));
			resultSet.close();
			preparedStatement.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	
	/**
	 * It adds a row to the table in the database;
	 * If successful, it returns true.
	 * If it fails, it returns false.
	 * @param user
	 * @return boolean 
	 */
	public boolean addRow(User user) {
		try {
			String query = "INSERT INTO User (currimageid, email, firstname, indexedrecords, lastname, password, username) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, user.getCurrimageid());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getFirstname());
			preparedStatement.setInt(4, user.getIndexedrecords());
			preparedStatement.setString(5, user.getLastname());
			preparedStatement.setString(6, user.getPassword());
			preparedStatement.setString(7, user.getUsername());
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
	 * If it succeeds, it sends an object User with information from that row
	 * If it fails, it outputs FAILED.
	 * @param rowID
	 * @return User
	 */
	public User getRow(int rowID) {
		User user = new User();
		try{
			String query = "SELECT * FROM User WHERE id = ?";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, rowID);
			ResultSet resultSet = preparedStatement.executeQuery();
			user.setCurrimageid(resultSet.getInt("currimageid"));
			user.setEmail(resultSet.getString("email"));
			user.setFirstname(resultSet.getString("firstname"));
			user.setId(resultSet.getInt("id"));
			user.setIndexedrecords(resultSet.getInt("indexedrecords"));
			user.setLastname(resultSet.getString("lastname"));
			user.setPassword(resultSet.getString("password"));
			user.setUsername(resultSet.getString("username"));
			resultSet.close();
			preparedStatement.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	
	/**
	 * It gets all the rows in the table
	 * If it succeeds, it returns a Users object that contains several 
	 * User objects
	 * If it fails, it outputs FAILED
	 * @return Users
	 */
	public Users getAllRows() {
		Users users = new Users();
		try{
			String query = "SELECT * FROM User";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				User user = new User();
				user.setCurrimageid(resultSet.getInt("currimageid"));
				user.setEmail(resultSet.getString("email"));
				user.setFirstname(resultSet.getString("firstname"));
				user.setId(resultSet.getInt("id"));
				user.setIndexedrecords(resultSet.getInt("indexedrecords"));
				user.setLastname(resultSet.getString("lastname"));
				user.setPassword(resultSet.getString("password"));
				user.setUsername(resultSet.getString("username"));
				users.getUsers().add(new User(user));
			}
			resultSet.close();
			preparedStatement.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return users;
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
			String query = "DELETE FROM User WHERE id = ?";
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
	public boolean editRow(int rowID, User user) {
		try {
			String query = "UPDATE User SET currimageid = ?, email = ?, firstname = ?, indexedrecords = ?, lastname = ?, password = ?, username = ? WHERE id = ? ";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, user.getCurrimageid());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getFirstname());
			preparedStatement.setInt(4, user.getIndexedrecords());
			preparedStatement.setString(5, user.getLastname());
			preparedStatement.setString(6, user.getPassword());
			preparedStatement.setString(7, user.getUsername());
			preparedStatement.setInt(8, rowID);
			preparedStatement.execute();
			preparedStatement.close();
			return true;
		} catch(Exception e) {
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
