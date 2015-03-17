package shared.communicationClasses;



/**
 * 
 * GetSampleImageOutput encapsulates the output's information for the
 * GetSampleImage function
 *
 */
public class GetSampleImageOutput {
	
	
	
	public class Succeed {
	
		private String url;
		
		public String toString() {
			return "http://"+host+":"+port+"/Records/"+url+"\n";
		}
		
		public Succeed() {
		this.url = new String();
		}
		
		public void setSucceed(String succeed) {
			this.url = succeed;
		}
		
		public String getUrl() {
			return url;
		}
	}
	
	
	
	public class Failed {
	
		private final String output = "FAILED";
		
		public String toString() {
			return output;
		}
	}
	
	
	
	public class Invalid {
	
		private final String output = "FAILED";
		
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
	public GetSampleImageOutput() {
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

	
	
	@Override
	public String toString() {
		return "GetSampleImageOutput [succeed=" + succeed + ", failed="
				+ failed + "]";
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