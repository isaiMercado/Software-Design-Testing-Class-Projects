package shared.communicationClasses;

/**
 * 
 * DownloadBatchInput encapsulates the input's information for the
 * DownloadBatch function
 *
 */
public class DownloadBatchInput {
	
	
	
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
	public DownloadBatchInput(String username, String password, int projectid) {
		super();
		this.user = username;
		this.password = password;
		this.projectid = projectid;
	}

	
	
	public DownloadBatchInput(String username, String password) {
		this.user = username;
		this.password = password;
	}

	
	
	/**
	 * It gets user
	 * @return String
	 */
	public String getUser() {
		return user;
	}
	
	
	
	/**
	 * It sets user
	 * @param user
	 */
	public void setUser(String user) {
		this.user = user;
	}
	
	
	
	/**
	 * It gets password;
	 * @return String
	 */
	public String getPassword() {
		return password;
	}
	
	
	
	/**
	 * It sets password
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	/**
	 * It gets Project
	 * @return project
	 */
	public int getProjectid() {
		return projectid;
	}
	
	
	
	/**
	 * It sets project
	 * @param project
	 */
	public void setProjectid(int projectid) {
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
