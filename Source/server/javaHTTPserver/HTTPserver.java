package server.javaHTTPserver;

import java.net.*;
import server.HTTPHandlerClasses.*;
import server.dataBase.DataBaseConnector;
import shared.modelClasses.*;
import com.sun.net.httpserver.*;



//@SuppressWarnings("restriction") // to take restriction out: On Mac OS X/Linux: Eclipse -> Preferences -> Java -> Compiler -> Errors/Warnings -> Deprecated and restricted API -> Forbidden reference (access rules): -> change to warning
public class HTTPserver {
	
	
	
	private static DataBaseConnector databasePersistent;
	private static Fields fieldsCache;
	private static Images imagesCache;
	private static Projects projectsCache;
	private static Values valuesCache;
	private static Users usersCache;
	
	private static HttpServer server;
	
	
	
	static {
		server = null;
		databasePersistent = new DataBaseConnector();
		databasePersistent.initialize();
		fieldsCache = new Fields();
		imagesCache = new Images();
		projectsCache = new Projects();
		valuesCache = new Values();
		usersCache = new Users();
		fieldsCache.importFieldsFromDB(databasePersistent);
		imagesCache.importImagesFromDB(databasePersistent);
		projectsCache.importProjectsFromDB(databasePersistent);
		valuesCache.importValuesFromDB(databasePersistent);
		usersCache.importUsersFromDB(databasePersistent);	
	}
	
	
	
	public static void close() {
		int timeWaitedToStop = 0;
		server.stop(timeWaitedToStop);
	}
	
	
	
	public static DataBaseConnector getDatabasePersistent() {
		return databasePersistent;
	}
	
	
	
	public static Fields getFieldsCache() {
		return fieldsCache;
	}
	
	
	
	public static Images getImagesCache() {
		return imagesCache;
	}
	
	
	
	public static Projects getProjectsCache() {
		return projectsCache;
	}
	
	
	
	public static Values getValuesCache() {
		return valuesCache;
	}
	
	
	
	public static Users getUsersCache() {
		return usersCache;
	}
	
	
	
	public HttpServer getJavaServer() {
		return server;
	}
	
	
	
	public static void main(String[] args) {
		int address = 0;
		if (args.length != 0) {
			address = Integer.parseInt(args[0]);
		} 
		run(address);
	} 
	
	
	
	private static void run(int input) { // changed to static and private
		try {
			int address = 8080;
			if (input != 0) {
				address = input;
			} 
			server = HttpServer.create(new InetSocketAddress(address), 10);
			server.setExecutor(null); 
			server.createContext("/downloadBatch", new DownloadBatchHandler());
			server.createContext("/Records", new DownloadFileHandler());
			server.createContext("/getFields", new GetFieldsHandler());
			server.createContext("/getProjects", new GetProjectsHandler());
			server.createContext("/getSampleImage", new GetSampleImageHandler());
			server.createContext("/search", new SearchHandler());
			server.createContext("/submitBatch", new SubmitBatchHandler());
			server.createContext("/validateUser", new ValidateUserHandler());
			server.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
