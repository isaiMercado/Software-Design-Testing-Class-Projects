package client.clientCommunicator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import server.javaHTTPserver.HTTPserver;
import shared.communicationClasses.*;
import shared.dataImporterProgram.DataImporter;



public class ClientCommunicatorTests {
	
	
	
	@Before
	public void before() {
		String[] array = {"./test/RecordsTest/Records.xml"};
		DataImporter.main(array);
		HTTPserver.main(new String[0]);
	}
	
	
	
	@After
	public void after() {
		HTTPserver.close();
	}
	
	
	
	@AfterClass
	public static void beforeClass() {
		String[] array = {"./test/RecordsTest/Records.xml"};
		DataImporter.main(array);
		HTTPserver.close();
	}
	
	
	
	@Test
	public void ValidateUser_test() {// in order to see the updates to the code, you have to restart the server
		try {
			ValidateUserInput input = new ValidateUserInput("test2","test2");
			ValidateUserOutput output = ClientCommunicator.ValidateUser(input);
			assertEquals("TRUE\nTest\nTwo\n0\n",output.getSucceed().toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void GetProjects_test() {
		try {
			GetProjectsInput input = new GetProjectsInput("test1","test1");
			GetProjectsOutput output = ClientCommunicator.GetProjects(input);
			assertEquals("1\n1890 Census\n2\n1900 Census\n3\nDraft Records\n",output.getSucceed().toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void GetSampleImage_test() {
		try {
			GetSampleImageInput input = new GetSampleImageInput("test1","test1",1);
			GetSampleImageOutput output = ClientCommunicator.GetSampleImage(input);
			assertEquals("http://localhost:8080/Records/images/1890_image0.png\n",output.getSucceed().toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void DownloadBatch_test() {
		try {
			DownloadBatchInput input = new DownloadBatchInput("test1","test1",1);
			DownloadBatchOutput output = ClientCommunicator.DownloadBatch(input);
			
			// first 2 was 1 before
			assertEquals("1\n1\nhttp://localhost:8080/Records/images/1890_image0.png\n"
					   + "199\n60\n8\n4\n1\n0\nLast Name\nhttp://localhost:8080/Records/fieldhelp/last_name.html\n"
					   + "60\n300\nhttp://localhost:8080/Records/knowndata/1890_last_names.txt\n"
					   + "2\n1\nFirst Name\nhttp://localhost:8080/Records/fieldhelp/first_name.html\n"
					   + "360\n280\nhttp://localhost:8080/Records/knowndata/1890_first_names.txt\n"
					   + "3\n2\nGender\nhttp://localhost:8080/Records/fieldhelp/gender.html\n"
					   + "640\n205\nhttp://localhost:8080/Records/knowndata/genders.txt\n"
					   + "4\n3\nAge\nhttp://localhost:8080/Records/fieldhelp/age.html\n"
					   + "845\n120\n",output.getSucceed().toString());
			
			int imageId = 1;
			assertEquals(imageId,HTTPserver.getUsersCache().validateUser("test1", "test1").getCurrimageid());
			int userId = HTTPserver.getUsersCache().validateUser("test1", "test1").getId();
			
			HTTPserver.getDatabasePersistent().startTransaction();
			assertEquals(imageId,HTTPserver.getDatabasePersistent().getUserDAO().getRow(userId).getCurrimageid());
			HTTPserver.getDatabasePersistent().endTransaction(true);
			
			assertEquals(userId,HTTPserver.getImagesCache().getImage(imageId).getUserid());
			
			HTTPserver.getDatabasePersistent().startTransaction();
			assertEquals(userId,HTTPserver.getDatabasePersistent().getImageDAO().getRow(imageId).getUserid());
			HTTPserver.getDatabasePersistent().endTransaction(true);
			
			DownloadBatchInput input2 = new DownloadBatchInput("test1","test1",1);
			DownloadBatchOutput output2 = ClientCommunicator.DownloadBatch(input2);
			assertEquals("FAILED",output2.getInvalid().toString());
		} catch(Exception e) {
			e.printStackTrace();
			HTTPserver.getDatabasePersistent().endTransaction(false);
		}
	}
	
	
	
	@Test
	public void SubmitBatch_test() {
		try {
			//SubmitBatchInput(String user, String password, int batch, String field_values) {
			SubmitBatchInput input = new SubmitBatchInput("test1","test1",1,"1a,1b,1c,1d;2a,2b,2c,2d;3a,3b,3c,3d;4a,4b,4c,4d;5a,5b,5c,5d;6a,6b,6c,6d;7a,7b,7c,7d;8a,8b,8c,8d");
			input.setColumnsNeeded(4);
			input.setRowsNeeded(8);
			input.parseValues();
			
			assertEquals(true,input.isColsAndRowscomplete());
			SubmitBatchOutput output = ClientCommunicator.SubmitBatch(input);
			
			assertEquals("TRUE",output.getSucceed().toString());
			assertEquals(8,HTTPserver.getUsersCache().validateUser("test1", "test1").getIndexedrecords());
			
			int userId = HTTPserver.getUsersCache().validateUser("test1", "test1").getId();
			HTTPserver.getDatabasePersistent().startTransaction();
			assertEquals(8,HTTPserver.getDatabasePersistent().getUserDAO().getRow(userId).getIndexedrecords());
			HTTPserver.getDatabasePersistent().endTransaction(true);
			
			SubmitBatchInput input2 = new SubmitBatchInput("test1","test1",1,"1a,1b,1c,1d;2a,2b,2c,2d;3a,3b,3c,3d;4a,4b,4c,4d;5a,5b,5c,5d;6a,6b,6c,6d;7a,7b,7c,7d;8a,8b,8c,8d");
			input2.setColumnsNeeded(4);
			input2.setRowsNeeded(8);
			input2.parseValues();
			
			assertEquals(true,input2.isColsAndRowscomplete());
			SubmitBatchOutput output2 = ClientCommunicator.SubmitBatch(input);
			
			assertEquals("FAILED",output2.getSucceed().toString());
			
			SubmitBatchInput input3 = new SubmitBatchInput("test1","test1",1,"1b,1c,1d;2a,2b,2c,2d;3a,3b,3c,3d;4a,4b,4c,4d;5a,5b,5c,5d;6a,6b,6c,6d;7a,7b,7c,7d;8a,8b,8c,8d");
			input3.setColumnsNeeded(4);
			input3.setRowsNeeded(8);
			input3.parseValues();
			
			assertEquals(false,input3.isColsAndRowscomplete());
			SubmitBatchOutput output3 = ClientCommunicator.SubmitBatch(input);
			
			assertEquals("FAILED",output3.getSucceed().toString());
		} catch(Exception e) {
			e.printStackTrace();
			HTTPserver.getDatabasePersistent().endTransaction(false);
		}
	}
	
	
	
	@Test
	public void GetFields_test() {
		try {
			GetFieldsInput input = new GetFieldsInput("test1","test1",1);
			GetFieldsOutput output = ClientCommunicator.GetFields(input);
			assertEquals("1\n1\nLast Name\n1\n2\nFirst Name\n1\n3\nGender\n1\n4\nAge\n",output.getSucceed().toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void Search_test() {
		try {
			SearchInput input = new SearchInput("test1","test1","11,10","fox,russell");
			SearchOutput output = ClientCommunicator.Search(input);
			assertEquals("41\nhttp://localhost:8080/Records/images/draft_image0.png\n1\n11\n41\nhttp://localhost:8080/Records/images/draft_image0.png\n1\n10\n",output.getSucceed().toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
