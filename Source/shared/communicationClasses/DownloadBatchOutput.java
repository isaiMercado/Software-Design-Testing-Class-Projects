package shared.communicationClasses;

import java.io.Serializable;
import java.util.ArrayList;






/**
 * 
 * DownloadBatchOutput encapsulates the output's information for the
 * DownloadBatch function
 *
 */
@SuppressWarnings("serial")
public class DownloadBatchOutput implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public static ArrayList<DownloadBatchOutput.Field> convertFieldstoOutputFields(shared.modelClasses.Fields input) {
		ArrayList<DownloadBatchOutput.Field> output = new ArrayList<DownloadBatchOutput.Field>();
		int field_num = 0;
		for (shared.modelClasses.Field fld : input.getFields()) {
			Field field = new Field(fld.getId(),field_num,fld.getTitle(),fld.getHelphtml(),fld.getXcoord(),fld.getWidth());
			if(fld.getKnowndata() != null) {
				field.setKnownValues(fld.getKnowndata());
			}
			output.add(field);
			field_num++;
		}
		return output;
	}
	
	
	
	public static class Field implements Serializable{
		
		private int field_id;
		private int field_num;
		private String field_tittle;
		private String help_url;
		private int x_coord;
		private int pixel_width;
		private String known_values_url;
		
		public Field() {
			this.field_id = 0;
			this.field_num = 0;
			this.field_tittle = new String();
			this.help_url = new String();
			this.x_coord = 0;
			this.pixel_width = 0;
			this.known_values_url = new String();
		}
		
		public Field(int field_id, int field_num, String field_tittle,
				String help_url, int x_coord, int pixel_width) {
			this.field_id = field_id;
			this.field_num = field_num;
			this.field_tittle = field_tittle;
			this.help_url = help_url;
			this.x_coord = x_coord;
			this.pixel_width = pixel_width;
			this.known_values_url = new String();
		}
		
		public void setKnownValues(String knownValues) {
			known_values_url = knownValues;
		}
		
		public int getFieldid() {
			return field_id;
		}
		
		public int getFieldnum() {
			return field_num;
		}
		
		public String getFieldTittle() {
			return field_tittle;
		}
		
		public String getHelpUrl() {
			return help_url;
		}
		
		public int getXcoord() {
			return x_coord;
		}
		
		public int getPixelWidth() {
			return pixel_width;
		}
		
		public String getKnownValuesUrl() {
			return known_values_url;
		}	
	}
	
	
	
	public static class Output implements Serializable{
		
		private int batch_id;
		private int project_id;
		private int field_id;
		private String image_url;
		private int first_y_coord;
		private int record_height;
		private int num_records;
		private int num_fields;
		private ArrayList<Field> fields;
		
		public Output() {
			this.batch_id = 0;
			this.project_id = 0;
			this.image_url = new String();
			this.first_y_coord = 0;
			this.record_height = 0;
			this.num_records = 0;
			this.num_fields = 0;
			this.fields = new ArrayList<Field>();
		}
		
		public Output(int batch_id, int project_id,
				String image_url, int first_y_coord, int record_height,
				int num_records, int num_fields, ArrayList<Field> fieldArray) {
			this.batch_id = batch_id;
			this.project_id = project_id;
			this.image_url = image_url;
			this.first_y_coord = first_y_coord;
			this.record_height = record_height;
			this.num_records = num_records;
			this.num_fields = num_fields;
			this.fields = fieldArray;
		}
		
		public int getBatch_id() {
			return batch_id;
		}
		
		public int getProjectid() {
			return project_id;
		}
		
		public int getFieldid() {
			return field_id;
		}
		
		public String getImageUrl() {
			return image_url;
		}
		
		public int getFirstYcoord() {
			return first_y_coord;
		}
		
		public int getRecordHeight() {
			return record_height;
		}
		
		public int getNumRecords() {
			return num_records;
		}
		
		public int getNumFields() {
			return num_fields;
		}
		
		public ArrayList<Field> getFields() {
			return fields;
		}
	}
	
	
	
	public class Succeed implements Serializable{
		
		private Output output;
		
		public Succeed() {
			this.output = new Output();
		}
		
		public Output getOutput() {
			return output;
		}
		
		public void setOutput(Output input) {
			output = input;
		}
		
		public String toString() {
			StringBuilder strb = new StringBuilder();
			strb.append(output.getBatch_id()+"\n");
			strb.append(output.getProjectid()+"\n");
			strb.append("http://"+host+":"+port+"/Records/"+output.getImageUrl()+"\n");
			strb.append(output.getFirstYcoord()+"\n");
			strb.append(output.getRecordHeight()+"\n");
			strb.append(output.getNumRecords()+"\n");
			strb.append(output.getNumFields()+"\n");
			for(Field field : output.getFields()) {
				strb.append(field.getFieldid()+"\n");
				strb.append(field.getFieldnum()+"\n");
				strb.append(field.getFieldTittle()+"\n");
				strb.append("http://"+host+":"+port+"/Records/"+field.getHelpUrl()+"\n");
				strb.append(field.getXcoord()+"\n");
				strb.append(field.getPixelWidth()+"\n");
				if(field.getKnownValuesUrl().length() != 0)
				strb.append("http://"+host+":"+port+"/Records/"+field.getKnownValuesUrl()+"\n");
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

	
	
	public Invalid getInvalid() {
		return invalid;
	}
	
	
	
	public void initializeInvalid() {
		this.invalid = new Invalid();
	}
	
	
	
	/**
	 * Constructor 
	 */
	public DownloadBatchOutput() {
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
		return "DownloadBatchOutput [succeed=" + succeed + ", failed=" + failed
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
