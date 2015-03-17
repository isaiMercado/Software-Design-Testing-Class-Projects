package shared.communicationClasses;

import java.io.File;




/**
 * 
 * DownloadFileOutput encapsulates the output's information for the
 * DownloadFile function
 *
 */
public class DownloadFileOutput {
	
	public class Succeed {
		
		private File file;
		
		public Succeed() {
		}
		
		public Succeed(String fileAddress) {
			this.file = new File(fileAddress);
		}
		
		public File getFile() {
			return file;
		}
	}
	
	
	
	public class Failed {
	
		private final String OUTPUT = "FAILED";
		
		public String getOUTPUT() {
			return OUTPUT;
		}
	}
	
	
	
	public class Invalid {
	
		private final String OUTPUT = "FAILED";
		
		public String getOUTPUT() {
			return OUTPUT;
		}
	}
	
	
	
	private Succeed succeed;
	private Failed failed;
	private Invalid invalid;
	
	public DownloadFileOutput() {
		this.succeed = null;
		this.failed = null;
		this.invalid = null;
	}

	public Succeed getSucceed() {
		return succeed;
	}

	public void initializeSucceed() {
		this.succeed = new Succeed();
	}

	public Failed getFailed() {
		return failed;
	}

	public void initializeFailed() {
		this.failed = new Failed();
	}

	public Invalid getInvalid() {
		return invalid;
	}

	public void initializeInvalid() {
		this.invalid = new Invalid();
	}
	
	
	
	
	
}
