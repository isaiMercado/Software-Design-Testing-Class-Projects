package shared.communicationClasses;

import java.io.Serializable;
import java.util.ArrayList;

import shared.modelClasses.Field;
import shared.modelClasses.Fields;



/**
 * 
 * GetFieldsOutput encapsulates the output's information for the
 * GetFields function
 *
 */
public class GetFieldsOutput implements Serializable{
	
	
	
	public static class FieldInfo implements Serializable{
		
		private int projectid;
		private int fieldid;
		private String fieldTittle;
		
		public FieldInfo() {
			this.projectid = 0;
			this.fieldid = 0;
			this.fieldTittle = new String();
		}
		
		public FieldInfo(int projectid, int fieldid, String fieldTittle) {
			this.projectid = projectid;
			this.fieldid = fieldid;
			this.fieldTittle = fieldTittle;
		}
		
		public int getProjectid() {
			return projectid;
		}
		
		public int getFieldid() {
			return fieldid;
		}
		
		public String getFieldTittle() {
			return fieldTittle;
		}	
	}
	
	
	
	public class Succeed implements Serializable{
	
		private ArrayList<FieldInfo> fieldInfo;
		
		public Succeed() {
			this.fieldInfo = new ArrayList<FieldInfo>();
		}
		
		public ArrayList<FieldInfo> getFieldInfo() {
			return fieldInfo;
		}
		
		public void setFieldInfo(Fields fields) {
			for (Field field : fields.getFields()) {
				fieldInfo.add(new FieldInfo(field.getParentid(), field.getId(), field.getTitle()));
			}
		}
		
		public String toString() {
			StringBuilder strb = new StringBuilder();
			for (FieldInfo field : fieldInfo) {
				strb.append(field.getProjectid()+"\n");
				strb.append(field.getFieldid()+"\n");
				strb.append(field.getFieldTittle()+"\n");
			}
			return strb.toString();
		}
	}
	
	
	
	public class Failed implements Serializable{
	
		private final String output = "FAILED";
		
		public String toString() {
			return output;
		}
	}
	
	
	
	public class Invalid implements Serializable{
	
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
	public GetFieldsOutput() {
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

	
	
	public Invalid getInvalid() {
		return invalid;
	}


	
	public void initializeInvalid() {
		this.invalid = new Invalid();
	}

	

	@Override
	public String toString() {
		return "GetFieldsOutput [succeed=" + succeed + ", failed=" + failed
				+ "]";
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
