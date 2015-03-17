package server.dataBaseAccessClasses;

import server.dataBase.DataBaseConnector;
import shared.modelClasses.*;
import java.sql.*;



/**
 * Any part of the system that needs to access information about Projects in 
 * the database will go through the ProjectDAO class.
 * @author isai
 *
 */
public class ProjectDAO {

	
	
	private DataBaseConnector database;
	
	
	
	/**
	 * constructor that receives an object DataBase
	 * @param database
	 */
	public ProjectDAO(DataBaseConnector database) {
		super();
		this.database = database;
	}


	
	public Projects searchRow(String title) {
		Projects projects = new Projects();;
		try {
			String query = "SELECT * FROM Project WHERE title = ?";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			preparedStatement.setString(1, title);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Project project = new Project();
				project.setFirstycoord(resultSet.getInt("firstycoord"));
				project.setId(resultSet.getInt("id"));
				project.setRecordheight(resultSet.getInt("recordheight"));
				project.setRecordsperimage(resultSet.getInt("recordsperimage"));
				project.setTitle(resultSet.getString("title"));
				projects.getProjects().add(project);
			}
			resultSet.close();
			preparedStatement.close();
		} catch (Exception e) {
			projects = null;
			e.printStackTrace();
		}
		return projects;
		
	}
	
	
	
	/**
	 * It adds a row to the table in the database;
	 * If successful, it returns true.
	 * If it fails, it returns false.
	 * @param project
	 * @return boolean 
	 */
	public boolean addRow(Project project) {
		try{
			String query = "INSERT INTO Project (firstycoord, recordheight, recordsperimage, title) VALUES (?,?,?,?)";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			preparedStatement.setInt(1,project.getFirstycoord());
			preparedStatement.setInt(2, project.getRecordheight());
			preparedStatement.setInt(3, project.getRecordsperimage());
			preparedStatement.setString(4, project.getTitle());
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
	 * If it succeeds, it sends an object Project with information from that row
	 * If it fails, it outputs FAILED.
	 * @param rowID
	 * @return Project
	 */
	public Project getRow(int rowID) {
		Project project = new Project();
		try {
			String query = "SELECT * FROM Project WHERE id = ?";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, rowID);
			ResultSet resultSet = preparedStatement.executeQuery();
			project.setFirstycoord(resultSet.getInt("firstycoord"));
			project.setId(resultSet.getInt("id"));
			project.setRecordheight(resultSet.getInt("recordheight"));
			project.setRecordsperimage(resultSet.getInt("recordsperimage"));
			project.setTitle(resultSet.getString("title"));
			resultSet.close();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return project;
	}
	
	
	
	/**
	 * It gets all the rows in the table
	 * If it succeeds, it returns a Projects object that contains several 
	 * Project objects
	 * If it fails, it outputs FAILED
	 * @return Projects
	 */
	public Projects getAllRows() {
		Projects projects = new Projects();
		try {
			String query = "SELECT * FROM Project";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Project project = new Project();
				project.setFirstycoord(resultSet.getInt("firstycoord"));
				project.setId(resultSet.getInt("id"));
				project.setRecordheight(resultSet.getInt("recordheight"));
				project.setRecordsperimage(resultSet.getInt("recordsperimage"));
				project.setTitle(resultSet.getString("title"));
				projects.getProjects().add(project);
			}
			resultSet.close();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return projects;
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
			String query = "DELETE FROM Project WHERE id = ?";
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
	public boolean editRow(int rowID, Project project) {
		try {
			String query = "UPDATE Project SET firstycoord = ?, recordheight = ?, recordsperimage = ?, title = ? WHERE id = ?";
			PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, project.getFirstycoord());
			preparedStatement.setInt(2, project.getRecordheight());
			preparedStatement.setInt(3, project.getRecordsperimage());
			preparedStatement.setString(4, project.getTitle());
			preparedStatement.setInt(5, rowID);
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
