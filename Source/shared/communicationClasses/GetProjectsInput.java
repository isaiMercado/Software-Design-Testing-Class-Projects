package shared.communicationClasses;



/**
 * 
 * GetProjectsInput encapsulates the input's information for the
 * GetProjects function
 *
 */
public class GetProjectsInput {
	
	
	
	private String user;
	private String password;

	private String host;
	private int port;
	
	
	
	/**
	 * Constructor that takes parameters
	 * @param user
	 * @param password
	 */
	public GetProjectsInput(String user, String password) {
		super();
		this.user = user;
		this.password = password;
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

	
	
	@Override
	public String toString() {
		return user+"\n"+password+"\n";
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
