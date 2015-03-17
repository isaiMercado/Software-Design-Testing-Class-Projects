package shared.communicationClasses;



/**
 * 
 * ValidateUserInput encapsulates the input's information for the
 * ValidateUser function
 *
 */
public class ValidateUserInput {
	
	
	
	/**
	 * Constructor that takes parameters
	 * @param user
	 * @param password
	 */
	public ValidateUserInput(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	
	
	private String user;
	private String password;

	private String host;
	private int port;
	
	
	
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
