package shared.modelClasses;


import com.thoughtworks.xstream.annotations.*;



/**
 * The XML file contains information about all of the 
 * user accounts and projects in the data set.
 * @author isai
 *
 */
//@XmlRootElement
@XStreamAlias("indexerdata")
public class IndexerData {

	
	
	@XStreamAlias("users")
	private Users users;
	@XStreamAlias("projects")
	private Projects projects;
	
	
	
	/**
	 * Constructor that takes parameters
	 * @param users
	 * @param projects
	 */
	public IndexerData(Users users, Projects projects) {
		this.users = users;
		this.projects = projects;
	}
	
	
	
	public IndexerData() {
		this.users = new Users();
		this.projects = new Projects();
	}
	
	
	
	/**
	 * 
	 * @return users
	 */
	public Users getUsers() {
		return users;
	}
	
	
	
	/**
	 * 
	 * @param users
	 */
	public void setUsers(Users users) {
		this.users = users;
	}
	
	
	
	/**
	 * 
	 * @return projects
	 */
	public Projects getProjects() {
		return projects;
	}
	
	
	
	/**
	 * 
	 * @param projects
	 */
	public void setProjects(Projects projects) {
		this.projects = projects;
	}
	
	
	
	@Override
	public String toString() {
		return "<IndexerData>\n" + 
				users + 
				projects + 
				"</IndexerData>\n";
	}
}
