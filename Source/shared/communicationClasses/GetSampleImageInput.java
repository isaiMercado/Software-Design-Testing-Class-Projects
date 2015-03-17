package shared.communicationClasses;



/**
 * 
 * GetSampleImageInput encapsulates the input's information for the
 * GetSampleImage function
 *
 */
public class GetSampleImageInput {
	
	
	
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
	public GetSampleImageInput(String user, String password, int projectid) {
		super();
		this.user = user;
		this.password = password;
		this.projectid = projectid;
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
	public int getProjectid() {
		return projectid;
	}
	
	
	
	/**
	 * 
	 * @param project
	 */
	public void setProject(int projectid) {
		this.projectid = projectid;
	}

	
	
	@Override
	public String toString() {
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
