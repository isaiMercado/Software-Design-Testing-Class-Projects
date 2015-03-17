package client.clientCommunicator;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import shared.communicationClasses.*;
/**
 * 
 * class that Client will use to communicate with Server.
 *
 */
public class ClientCommunicator {
	
	
	
	private static final String validateUser = "validateUser";
	private static final String downloadFile = "downloadFile";
	private static final String getFields = "getFields";
	private static final String getProjects = "getProjects";
	private static final String getSampleImage = "getSampleImage";
	private static final String search = "search";
	private static final String submitBatch = "submitBatch";
	private static final String downloadBatch = "downloadBatch";
	
	private static int port = 8080;
	private static String host = "localhost";
	
	
	
	public static void setPort(int portInput) {
		port = portInput;
	}
	
	
	
	public static void setHost(String hostInput) {
		host = hostInput;
	}

	
	
	private static Object post(Object object, String command) throws IOException {
		Object output = new Object();
		URL url = new URL("http://"+host+":"+port+"/"+command);
		System.out.println("url "+"http://"+host+":"+port+"/"+command);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.connect();
		XStream xstream = new XStream(new DomDriver());
		xstream.toXML(object, connection.getOutputStream());
		connection.getOutputStream().close();
		output = xstream.fromXML(connection.getInputStream());
		connection.getInputStream().close();
		return output;
	}
	
	
	
	private static InputStream get(URL object, String command) throws IOException {
		URL url = new URL("http://"+host+":"+port+"/"+command);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.setDoOutput(true);
		connection.connect();
		connection.getOutputStream().write(object.toString().getBytes());
		connection.getOutputStream().close();
		InputStream output = connection.getInputStream();
		connection.getInputStream().close();
		return output;
	}
	
	
	
	/**
	 * Validates user credentials.
	 * validateUser receives an object with information from the user, and sends 
	 * an object with output of the type failed, valid or invalid according to the 
	 * user's information, and connection status.
	 * @param ValidateUserInput object 
	 * @return ValidateUserOutput object
	 * @throws IOException 
	 */
	public static ValidateUserOutput ValidateUser(ValidateUserInput input) throws IOException {
		input.setHost(host);
		input.setPort(port);
		return (ValidateUserOutput)post(input,validateUser);
	}
	
	
	
	/**
	 * Returns information about all of the available projects.
	 * getProjects receives an object with user's name, and user's password.
	 * It sends an object with output of the type failed, valid or invalid according 
	 * to the user's information, and connection status.
	 * @param GetProjectsInput object 
	 * @return GetProjectsOutput object
	 * @throws IOException 
	 */
	public static GetProjectsOutput GetProjects(GetProjectsInput input) throws IOException {
		input.setHost(host);
		input.setPort(port);
		return (GetProjectsOutput)post(input,getProjects);
	}
	
	
	
	/**
	 * Returns a sample image for the specified project.
	 * It receives an object that encapsulates user's id, password and a project ID. 
	 * It sends an object that may contain an URL or a FAILED
	 * message according to connection status.
	 * @param GetSampleImageInput
	 * @return GetSampleImageOutput
	 * @throws IOException 
	 */
	public static GetSampleImageOutput GetSampleImage(GetSampleImageInput input) throws IOException {
		input.setHost(host);
		input.setPort(port);
		return (GetSampleImageOutput)post(input,getSampleImage);
	}
	
	
	
	/**
	 * Downloads a batch for the user to index.
	 * It receives an object that encapsulates user's ID,and password, and it also
	 * has a project ID. If it succeeds it sends the following information.
	 * image ID, project ID, field ID, image url, first Y coordinate, record height,
	 * number of records, number of fields, field ID, field title, help url
	 * x coordinate, pixel width, known values url.
     * If it fails it outputs FAILED
	 * @param DownloadBatchInput
	 * @return DownloadBatchOutput
	 * @throws IOException 
	 */
	public static DownloadBatchOutput DownloadBatch(DownloadBatchInput input) throws IOException {
		input.setHost(host);
		input.setPort(port);
		return (DownloadBatchOutput)post(input,downloadBatch);
	}
	
	
	
	/**
	 * Submits the indexed record field values for a batch to the Server.
	 * It receives and object with user's ID, password, Image ID, several field values,
	 * and several records values.
	 * It it succeeds, it outputs true.
	 * If it fails, it outputs false.
	 * @param SubmitBatchInput
	 * @return SubmitBatchOutput
	 * @throws IOException 
	 */
	public static SubmitBatchOutput SubmitBatch(SubmitBatchInput input) throws IOException {
		input.setHost(host);
		input.setPort(port);
		return (SubmitBatchOutput)post(input,submitBatch);
	}
	
	
	
	/**
	 * Returns information about all of the fields for the specified project.
	 * It receives an object that contains a user's ID, user's password, and project ID 
	 * Returns information about all of the fields for the specified project
     * If no project is specified, returns information about all of the fields
     * for all projects in the system.
	 * @param GetFieldsInput
	 * @return GetFieldsOutput
	 * @throws IOException 
	 */
	public static GetFieldsOutput GetFields(GetFieldsInput input) throws IOException {
		input.setHost(host);
		input.setPort(port);
		return (GetFieldsOutput)post(input,getFields);
	}
	
	
	
	/**
	 * Searches the indexed records for the specified strings.
	 * It receives an object that encapsulates user's ID, user's password, several
	 * field ID, and several search values.
	 * If the operation succeeds, it sends an object with several
	 * image ID, image url, record ID, field ID.
	 * If it fails, it outputs FAILED.
	 * @param SearchOutput
	 * @return SearchInput
	 * @throws IOException 
	 */
	public static SearchOutput Search(SearchInput input) throws IOException {
		input.setHost(host);
		input.setPort(port);
		return (SearchOutput)post(input,search);
	}
	
	
	
	/**
	 * Clients will use HTTP GET requests to download files from your Server. 
	 * it receives an object with an URL
	 * It sends an object with a file.
	 * @param DownloadFileOutput
	 * @return DownloadFileInput
	 * @throws IOException 
	 */
	public static InputStream DownloadFile(URL input) throws IOException {
		return get(input,downloadFile);
	}
}
