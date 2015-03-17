package server.dataBaseAccessClasses;

import static org.junit.Assert.*;

import org.junit.*;

import server.javaHTTPserver.HTTPserver;
import shared.dataImporterProgram.DataImporter;
import shared.modelClasses.*;

public class ValueDAOTests {

	
	
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
	public void searchRowTest() {
		try {
			User user = HTTPserver.getUsersCache().validateUser("test1", "test1");
			int xcoord = 3;
			int ycoord = 4;
			int parentId = 44;
			
			HTTPserver.getDatabasePersistent().startTransaction();
			Value value = HTTPserver.getDatabasePersistent().getValueDAO().searchRow(xcoord, ycoord, parentId);
			HTTPserver.getDatabasePersistent().endTransaction(true);
			
			assertEquals(99,value.getId());
			assertEquals(44,value.getParentId());
			assertEquals("27",value.getValue());
			assertEquals(3,value.getXcoordinate());
			assertEquals(4,value.getYcoordinate());
		} catch(Exception e) {
			e.printStackTrace();
			HTTPserver.getDatabasePersistent().endTransaction(false);
		}
	}
	
	
	
	@Test
	public void addRowTest() {
		try {
		String string = "trying";
		int xcoord = 1;
		int ycoord = 2;
		int batchid = 2;
		Value value = new Value(string, xcoord, ycoord, batchid);
		
		HTTPserver.getDatabasePersistent().startTransaction();
		HTTPserver.getDatabasePersistent().getValueDAO().addRow(value);
		//HTTPserver.getDatabasePersistent().endTransaction(true); // do not end transaction before getting the last id because sql memry is like erased
		//HTTPserver.getDatabasePersistent().startTransaction();
		int last = HTTPserver.getDatabasePersistent().getValueDAO().getLastRowID();
		HTTPserver.getDatabasePersistent().endTransaction(true);
		
		HTTPserver.getDatabasePersistent().startTransaction();
		Value value2 = HTTPserver.getDatabasePersistent().getValueDAO().getRow(last);
		HTTPserver.getDatabasePersistent().endTransaction(true);// each time you access the database you have to start and end transaction
		
		System.out.println("last id is "+last);
		System.out.println(value2);
		
		assertEquals(2,value2.getParentId());
		assertEquals("trying",value2.getValue());
		assertEquals(1,value2.getXcoordinate());
		assertEquals(2,value2.getYcoordinate());
		} catch(Exception e) {
			e.printStackTrace();
			HTTPserver.getDatabasePersistent().endTransaction(false);
		}
	}
	
	

	//Value getRow(int rowID) {
	@Test
	public void getRowTest() {
		try {
			int id = 10;
			HTTPserver.getDatabasePersistent().startTransaction();
			Value value = HTTPserver.getDatabasePersistent().getValueDAO().getRow(id);
			HTTPserver.getDatabasePersistent().endTransaction(true);
			
			assertEquals(10, value.getId());
			assertEquals(41,value.getParentId());
			assertEquals("JEROME",value.getValue());
			assertEquals(2,value.getXcoordinate());
			assertEquals(3,value.getYcoordinate());
		} catch(Exception e) {
			e.printStackTrace();
			HTTPserver.getDatabasePersistent().endTransaction(false);
		}
	}
	
	
	
	@Test // public Values getAllRows() 
	public void getAllRowsTest() {
		try {
			HTTPserver.getDatabasePersistent().startTransaction();
			Values values = HTTPserver.getDatabasePersistent().getValueDAO().getAllRows();
			HTTPserver.getDatabasePersistent().endTransaction(true);
			System.out.println(values);
		} catch(Exception e) {
			e.printStackTrace();
			HTTPserver.getDatabasePersistent().endTransaction(false);
		}
	}
	
	
	
	@Test //public boolean deleteRow(int rowID) {
	public void deleteRowTest() {
		try {
			String string = "trying";
			int xcoord = 1;
			int ycoord = 2;
			int batchid = 2;
			Value value = new Value(string, xcoord, ycoord, batchid);
			
			HTTPserver.getDatabasePersistent().startTransaction();
			HTTPserver.getDatabasePersistent().getValueDAO().addRow(value);
			int last = HTTPserver.getDatabasePersistent().getValueDAO().getLastRowID();
			HTTPserver.getDatabasePersistent().endTransaction(true);
			
			HTTPserver.getDatabasePersistent().startTransaction();
			Value value2 = HTTPserver.getDatabasePersistent().getValueDAO().getRow(last);
			HTTPserver.getDatabasePersistent().endTransaction(true);// each time you access the database you have to start and end transaction
			
			System.out.println("last id is "+last);
			System.out.println(value2);
			
			assertEquals(2,value2.getParentId());
			assertEquals("trying",value2.getValue());
			assertEquals(1,value2.getXcoordinate());
			assertEquals(2,value2.getYcoordinate());
			
			HTTPserver.getDatabasePersistent().startTransaction();
			HTTPserver.getDatabasePersistent().getValueDAO().deleteRow(last);
			HTTPserver.getDatabasePersistent().endTransaction(true);
			
			HTTPserver.getDatabasePersistent().startTransaction();
			Value value3 = HTTPserver.getDatabasePersistent().getValueDAO().getRow(last-1);
			HTTPserver.getDatabasePersistent().endTransaction(true);
			
			assertEquals(60,value3.getParentId());
			assertEquals("ASIAN",value3.getValue());
			assertEquals(4,value3.getXcoordinate());
			assertEquals(7,value3.getYcoordinate());
			
		} catch(Exception e) {
			e.printStackTrace();
			HTTPserver.getDatabasePersistent().endTransaction(false);
		}
	}
	
	
	
	@Test //editRow(int rowID, Value value) {
	public void editRowTest() {
		try {
		String string = "trying";
		int xcoord = 1;
		int ycoord = 2;
		int batchid = 2;
		Value value = new Value(string, xcoord, ycoord, batchid);
		
		HTTPserver.getDatabasePersistent().startTransaction();
		HTTPserver.getDatabasePersistent().getValueDAO().addRow(value);
		int last = HTTPserver.getDatabasePersistent().getValueDAO().getLastRowID();
		HTTPserver.getDatabasePersistent().endTransaction(true);
		
		String string2 = "trying22";
		int xcoord2 = 12;
		int ycoord2 = 222;
		int batchid2 = 22;
		Value value2 = new Value(string2, xcoord2, ycoord2, batchid2);
		
		HTTPserver.getDatabasePersistent().startTransaction();
		HTTPserver.getDatabasePersistent().getValueDAO().editRow(last, value2);
		HTTPserver.getDatabasePersistent().endTransaction(true);
		
		HTTPserver.getDatabasePersistent().startTransaction();
		Value value3 = HTTPserver.getDatabasePersistent().getValueDAO().getRow(last);
		HTTPserver.getDatabasePersistent().endTransaction(true);
		
		assertEquals(22,value3.getParentId());
		assertEquals("trying22",value3.getValue());
		assertEquals(12,value3.getXcoordinate());
		assertEquals(222,value3.getYcoordinate());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test //search(String string, Integer fieldId) {
	public void searchTest() { 
		try {
			String lookingFor = "russell";
			int field = 11;
			HTTPserver.getDatabasePersistent().startTransaction();
			Values values = HTTPserver.getDatabasePersistent().getValueDAO().search(lookingFor, field);
			HTTPserver.getDatabasePersistent().endTransaction(true);
			
			for(Value value : values.getValues()) {
				assertEquals(41,value.getParentId());
				assertEquals("images/draft_image0.png",value.getParentURL());
				assertEquals(1, value.getYcoordinate());
				assertEquals(11,value.getFieldId());
			}
			
			String lookingFor2 = "asian";
			int field2 = 13;
			HTTPserver.getDatabasePersistent().startTransaction();
			Values values2 = HTTPserver.getDatabasePersistent().getValueDAO().search(lookingFor2, field2);
			HTTPserver.getDatabasePersistent().endTransaction(true);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
