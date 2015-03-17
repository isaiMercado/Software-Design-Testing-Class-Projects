package shared.communicationClasses;



/**
 * 
 * GetFieldsInput encapsulates the input's information for the
 * GetFields function
 *
 */
public class GetFieldsInput {
	
	
	
	private String user;
	private String password;
	private int projectid;
	
	private String host;
	private int port;
	
	
	
	/**
	 * Constructor that takes parameters
	 * @param user
	 * @param password
	 * @param project
	 */
	public GetFieldsInput(String user, String password, int project) {
		super();
		this.user = user;
		this.password = password;
		this.projectid = project;
	}

	
	
	public GetFieldsInput(String username, String password) {
		this.user = username;
		this.password = password;
		this.projectid = 0;
	}

	
	
	/**
	 * 
	 * @return user
	 */
	public String getUser() {
		return user;
	}
	
	
	
	/**
	 * 
	 * @param user
	 */
	public void setUser(String user) {
		this.user = user;
	}
	
	
	
	/**
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	
	
	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	/**
	 * 
	 * @return project
	 */
	public int getProject() {
		return projectid;
	}
	
	
	
	/**
	 * 
	 * @param project
	 */
	public void setProjectid(int project) {
		this.projectid = project;
	}

	
	
	@Override
	public String toString() {
		if (projectid == 0)
			return user+"\n"+password+"\n";
		else
			return user+"\n"+password+"\n"+projectid+"\n";
	}

	
	
	public String getHost() {
		return host;
	}

	
	
	public void setHost(String host) {
		this.host = host;
	}

	
	
	public int getPort() {
		return port;
	}

	
	
	public void setPort(int port) {
		this.port = port;
	}
}
