package shared.communicationClasses;

import java.io.Serializable;
import java.util.ArrayList;

import shared.modelClasses.Project;
import shared.modelClasses.Projects;



/**
 * 
 * GetProjectsOutput encapsulates the output's information for the
 * GetProjects function
 *
 */
public class GetProjectsOutput implements Serializable{
	
	public static class ProjectInfo implements Serializable{
		
		private int project_id;
		private String project_title;
		
		public ProjectInfo() {
			this.project_id = 0;
			this.project_title = new String();
		}
		
		public void setProjectInfo(int project_id, String project_title) {
			this.project_id = project_id;
			this.project_title = project_title;
		}
		
		public int getProjectid() {
			return project_id;
		}
		
		public String getProjectTitle() {
			return project_title;
		}
	}
	
	
	
	public class Succeed implements Serializable{
		
		private ArrayList<ProjectInfo> project_info;
		
		public String toString() {
			StringBuilder string = new StringBuilder();
			for (ProjectInfo projectinfo : project_info) {
				string.append(projectinfo.getProjectid()+"\n");
				string.append(projectinfo.getProjectTitle()+"\n");
			}
			return string.toString();
		}
		
		public Succeed() {
			this.project_info = new ArrayList<ProjectInfo>();
		}
		
		public void addProjectInfo(Projects projects) {
			this.project_info = parseProjects(projects);
		}
		
		private ArrayList<ProjectInfo> parseProjects(Projects projects) {
			ArrayList<ProjectInfo> output = new ArrayList<ProjectInfo>();
			for (Project project : projects.getProjects()) {
				ProjectInfo projInf = new ProjectInfo();
				projInf.setProjectInfo(project.getId(), project.getTitle());
				output.add(projInf);
			}
			return output;
		}
		
		public ArrayList<ProjectInfo> getProjectInfo() {
			return project_info;
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
	public GetProjectsOutput() {
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

	
	
	public Invalid getInvalid() {
		return invalid;
	}

	

	public void initializeInvalid() {
		this.invalid = new Invalid();
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
