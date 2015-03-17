package shared.communicationClasses;



/**
 * 
 * ValidateUserOutput encapsulates the output's information for the
 * ValidateUser function
 *
 */
public class ValidateUserOutput {
	
	
	
	public class Succeed {
		
		private final String output = "TRUE";
		private String userFirstName;
		private String userLastName;
		private int numRecords;
		
		public String toString() {
			return output+"\n"+userFirstName+"\n"+userLastName+"\n"+numRecords+"\n";
		}
		
		public Succeed() {
			this.userFirstName = new String();
			this.userLastName = new String();
			this.numRecords = 0;
		}
		
		public void setSucceed(String userFirstName, String userLastName, int numRecords) {
			this.userFirstName = userFirstName;
			this.userLastName = userLastName;
			this.numRecords = numRecords;
		}
		
		public String getOutput() {
			return output;
		}
		
		public String getUserFirstName() {
			return userFirstName;
		}
		
		public String getUserLastName() {
			return userLastName;
		}
		
		public int getNumRecords() {
			return numRecords;
		}
	}
	
	
	
	public class Failed {
	
		private final String output = "FAILED";
		
		public String toString() {
			return output;
		}
	}
	
	
	
	public class Invalid {
	
		private final String output = "FALSE";
		
		public String toString() {
			return output;
		}
	}
	
	
	
	private Succeed succeed;	
	private Failed failed;
	private Invalid invalid;
	
	private String host;
	private int port;
	
	
	
	/**
	 * Constructor that takes parameters
	 * @param succeed
	 * @param failed
	 */
	public ValidateUserOutput() {
		super();
		this.succeed = null;
		this.failed = null;
		this.invalid = null;
	}
	
	
	
	public Invalid getInvalid() {
		return invalid;
	}



	public void initializeInvalid() {
		this.invalid = new Invalid();
	}



	/**
	 * 
	 * @return succeed
	 */
	public Succeed getSucceed() {
		return succeed;
	}

	
	
	/**
	 * 
	 * @param succeed
	 */
	public void initializeSucceed() {
		this.succeed = new Succeed();
	}

	
	
	/**
	 * 
	 * @return failed
	 */
	public Failed getFailed() {
		return failed;
	}

	
	
	/**
	 * 
	 * @param failed
	 */
	public void initializeFailed() {
		this.failed = new Failed();
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
