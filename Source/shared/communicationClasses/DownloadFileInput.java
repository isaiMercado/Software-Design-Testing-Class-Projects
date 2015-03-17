package shared.communicationClasses;



/**
 * 
 * DownloadFileInput encapsulates the input's information for the
 * DownloadFile function
 *
 */
public class DownloadFileInput {

	
	
	private String url;

	
	
	/**
	 * Constructor that takes parameters
	 * @param url
	 */
	public DownloadFileInput(String url) {
		super();
		this.url = url;
	}

	
	
	@Override
	public String toString() {
		return "DownloadFileInput [url=" + url + "]";
	}

	
	
	/**
	 * 
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	
	
	/**
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	} 
}
