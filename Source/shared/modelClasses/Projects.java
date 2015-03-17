package shared.modelClasses;

import java.util.ArrayList;

import server.dataBase.DataBaseConnector;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;



/**
 * Projects is a collection of Project objects
 * @author isai
 *
 */
@XStreamAlias("projects")
public class Projects {

	
	
	@XStreamImplicit(itemFieldName="project")
	private ArrayList<Project> projects;
	
	
	
	/**
	 * Constructor that takes parameters
	 * @param projects
	 */
	public Projects(ArrayList<Project> pro) {
		super();
		this.projects = new ArrayList<Project>(pro);
	}

	
	
	public Projects() {
		projects = new ArrayList<Project>();
	}

	

	/**
	 * 
	 * @return projects
	 */
	public ArrayList<Project> getProjects() {
		return projects;
	}
	
	
	
	public Projects getAllProjects() {
		if (projects.size() == 0) return null;
		return new Projects(projects);
	}
	
	
	
	public Project searchProject(int projectid) {
		Project output = null;
		for (Project project : projects) {
			if (project.getId() == projectid) {
				output = project;
				break;
			}
		}
		return output;
	}
	
	
	
	/**
	 * 
	 * @param projects
	 */
	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}

	
	
	@Override
	public String toString() {
		return "<projects>\n" + 
				projects + 
				"</projects>\n";
	}



	public void importProjectsFromDB(DataBaseConnector db) {
		try {
			db.startTransaction();
			Projects temp = db.getProjectDAO().getAllRows();
			projects.addAll(temp.getProjects());
			db.endTransaction(true);
		} catch(Exception e) {
			e.printStackTrace();
			db.endTransaction(false);
		}
	}
	
	
	
	public Project getSpecificProject(int projectId) {
		Project output = null;
		for(Project project : projects) {
			if (project.getId() == projectId) {
				output = new Project();
				output = project;
			}
		}
		return output;
	}
}
