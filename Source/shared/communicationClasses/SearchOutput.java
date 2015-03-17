package shared.communicationClasses;

import java.util.ArrayList;



/**
 * 
 * SearchOutput encapsulates the output's information for the
 * Search function
 *
 */
public class SearchOutput {
	
	
	
	public static class SearchResult {
		
		private int batchid;
		private String imageUrl;
		private int recordNum;
		private int fieldid;
		
		public SearchResult() {
			this.batchid = 0;
			this.imageUrl = new String();
			this.recordNum = 0;
			this.fieldid = 0;
		}
		
		public SearchResult(int parentid, String imageUrl, int ycoord, int fieldId) {
			this.batchid = parentid;
			this.imageUrl = imageUrl;
			this.recordNum = ycoord;
			this.fieldid = fieldId;
		}
		
		public int getBatchid() {
			return batchid;
		}
		
		public String getImageUrl() {
			return imageUrl;
		}
		
		public int getRecordNum() {
			return recordNum;
		}
		
		public int getFieldid() {
			return fieldid;
		}	
	}
	
	
	
	public class Succeed {
		
		private ArrayList<SearchResult> searchResult;
		
		public Succeed() {
			this.searchResult = new ArrayList<SearchResult>();
		}
		
		public ArrayList<SearchResult> getSearchResult() {
			return searchResult;
		}
		
		public String toString() {
			StringBuilder strb = new StringBuilder();
			for (SearchResult result : searchResult) {
				strb.append(result.getBatchid()+"\n");
				if(result.getImageUrl().length() != 0)
				strb.append("http://"+host+":"+port+"/Records/"+result.getImageUrl()+"\n");
				strb.append(result.getRecordNum()+"\n");
				strb.append(result.getFieldid()+"\n");
			}
			return strb.toString();
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
	
	
	
	public Invalid getInvalid() {
		return invalid;
	}

	

	public void initializeInvalid() {
		this.invalid = new Invalid();
	}


	
	/**
	 * Constructor that takes parameters
	 * @param succeed
	 * @param failed
	 */
	public SearchOutput() {
		super();
		this.succeed = null;
		this.failed = null;
		this.invalid = null;
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
		return "SearchOutput [succeed=" + succeed + ", failed=" + failed + "]";
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
