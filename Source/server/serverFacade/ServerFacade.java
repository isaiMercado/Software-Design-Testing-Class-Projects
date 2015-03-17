package server.serverFacade;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import server.javaHTTPserver.HTTPserver;
import shared.communicationClasses.*;
import shared.modelClasses.*;

public class ServerFacade {

	
	
	public static DownloadBatchOutput DownloadBatch(DownloadBatchInput input) {
		DownloadBatchOutput output = new DownloadBatchOutput();
		try {
			User user = HTTPserver.getUsersCache().validateUser(input.getUser(), input.getPassword());
			if (user == null) {
				output.initializeInvalid();
			} else {
				if (!(user.getCurrimageid() == 0)) {
					output.initializeInvalid();
				} else {
				Project project = HTTPserver.getProjectsCache().searchProject(input.getProjectid());
					if (project == null) {
						output.initializeInvalid();
					} else {
						Image image = HTTPserver.getImagesCache().assignBatchtoUser(input.getProjectid());
						if (image == null) {
							output.initializeInvalid();
						} else {
							Fields fields = HTTPserver.getFieldsCache().searchFieldsFromProject(input.getProjectid());
							if (fields == null) {
								output.initializeInvalid();
							} else {
								output.initializeSucceed();
								output.setHost(input.getHost());
								output.setPort(input.getPort());
								ArrayList<DownloadBatchOutput.Field> Bfields = DownloadBatchOutput.convertFieldstoOutputFields(fields);
								DownloadBatchOutput.Output packOfInfo = new DownloadBatchOutput.Output(image.getId(), project.getId(), image.getFileUrl(), project.getFirstycoord(), project.getRecordheight(), project.getRecordsperimage(), fields.getFields().size(), Bfields);
								output.getSucceed().setOutput(packOfInfo);
								HTTPserver.getImagesCache().updateCurrUser(user.getId(),image.getId());
								HTTPserver.getUsersCache().updateCurrImage(user.getId(),image.getId());
							}
						}
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			output.initializeInvalid();
		}
		return output;
	}
	
	
	
	public static InputStream DownloadFile(String input) {
		InputStream output = null;
		try {
			System.out.println("input "+"."+input);
			File file = new File("."+input); //if you do not put the dot, it does not find the root directories
			output = new FileInputStream(file);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return output;
	} 
	
	
	
	public static GetFieldsOutput GetFields(GetFieldsInput input) {
		GetFieldsOutput output = new GetFieldsOutput();
		try {
			User user = HTTPserver.getUsersCache().validateUser(input.getUser(), input.getPassword());
			if (user == null) {
				output.initializeInvalid();
			} else {
				if(input.getProject() < 1) {
					output.initializeSucceed();
					Fields fields = HTTPserver.getFieldsCache().getAllFields();
					output.getSucceed().setFieldInfo(fields);
				} else {
					Project project = HTTPserver.getProjectsCache().searchProject(input.getProject());
					if (project == null) {
						output.initializeInvalid();
					} else {
						output.initializeSucceed();
						output.setHost(input.getHost());
						output.setPort(input.getPort());
						Fields fields = HTTPserver.getFieldsCache().getMyFields(input.getProject());
						output.getSucceed().setFieldInfo(fields);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			output.initializeInvalid();
		}
		return output;
	}
	
	
	
	public static GetProjectsOutput GetProjects(GetProjectsInput input) {
		GetProjectsOutput output = new GetProjectsOutput();
		try {
			User user = HTTPserver.getUsersCache().validateUser(input.getUser(), input.getPassword());
			if (user == null) {
				output.initializeInvalid();
			} else {
				output.initializeSucceed();
				output.setHost(input.getHost());
				output.setPort(input.getPort());
				Projects projects = HTTPserver.getProjectsCache().getAllProjects();
				output.getSucceed().addProjectInfo(projects);
			}
		} catch(Exception e) {
			e.printStackTrace();
			output.initializeInvalid();
		}
		return output;
	}
	
	
	
	public static GetSampleImageOutput GetSampleImage(GetSampleImageInput input) {
		GetSampleImageOutput output = new GetSampleImageOutput();
		try {
			User user = HTTPserver.getUsersCache().validateUser(input.getUser(), input.getPassword());
			if (user == null) {
				output.initializeInvalid();
			} else {
				Image image = HTTPserver.getImagesCache().getSampleImage(input.getProjectid());
				if(image == null) {
					output.initializeInvalid();
				} else {
					output.initializeSucceed();
					output.setHost(input.getHost());
					output.setPort(input.getPort());
					output.getSucceed().setSucceed(image.getFileUrl());
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			output.initializeInvalid();
		}
		return output;
	}
	
	
	
	public static SearchOutput Search(SearchInput input) {
		SearchOutput output = new SearchOutput();
		try {
			User user = HTTPserver.getUsersCache().validateUser(input.getUser(), input.getPassword());
			if(user == null) {
				output.initializeInvalid();
			} else {
				Values MasterValues = new Values();
				for(Integer fieldId : input.getFields()) {
					for(String string : input.getSearch_values()) {
						Values values = HTTPserver.getDatabasePersistent().getValueDAO().search(string, fieldId);
						if(values != null) {
							MasterValues.getValues().addAll(values.getValues());
						}
					}
				}
				if(MasterValues.getValues().size() == 0) {
					output.initializeInvalid();
				} else {
					output.initializeSucceed();
					output.setHost(input.getHost());
					output.setPort(input.getPort());
					for(Value value : MasterValues.getValues()) {
						SearchOutput.SearchResult result = new SearchOutput.SearchResult(value.getParentId(),value.getParentURL(),value.getYcoordinate(),value.getFieldId());
						output.getSucceed().getSearchResult().add(result);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			output.initializeInvalid();
		}
		return output;
	}
	
	
	
	public static SubmitBatchOutput SubmitBatch(SubmitBatchInput input) {
		SubmitBatchOutput output = new SubmitBatchOutput();
		try {
			User user = HTTPserver.getUsersCache().validateUser(input.getUser(), input.getPassword());
			if(user == null) {
				System.out.println("failed because user is invalid");
				output.initializeInvalid();
			} else {
				Image image = HTTPserver.getImagesCache().getImage(input.getBatch());
				if(image.getIsrecorded() == true) {
					output.initializeInvalid();
					System.out.println("failed because image is already recorded");
				} else {
					int projectId = image.getParentid();
					input.setRowsNeeded(HTTPserver.getProjectsCache().getSpecificProject(projectId).getRecordsperimage());
					input.setColumnsNeeded(HTTPserver.getFieldsCache().getNumberOfFieldsFromProject(projectId));
					input.parseValues();
					if(input.isColsAndRowscomplete() == false) {
						output.initializeInvalid();
						System.out.println("failed because format of commas and semicolon is incorrect");
					} else {
						output.initializeSucceed(); 
						output.setHost(input.getHost());
						output.setPort(input.getPort());
						int recordsCounter = 0;
						for(ArrayList<SubmitBatchInput.InputValue> values : input.getField_values()) {
							for(SubmitBatchInput.InputValue inputValue : values) {
								Value value = new Value(inputValue.getValue(),inputValue.getXcoordinate(),inputValue.getYcoordinate(),inputValue.getParentId());
								HTTPserver.getValuesCache().addValue(value);
							}
							recordsCounter++;
						}
						System.out.println(input.toString());
						int totalRecordsSubmited = recordsCounter + user.getIndexedrecords();
						System.out.println("totalRecordsSubmited "+totalRecordsSubmited);
						HTTPserver.getUsersCache().updateRecordsIndexed(user.getId(), totalRecordsSubmited);
						HTTPserver.getImagesCache().updateIsRecorded(image.getId(),true);
						HTTPserver.getUsersCache().updateCurrImage(user.getId(), 0); /// changed in client project
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			output.initializeInvalid();
			System.out.println("failed because there is an exception");
		}
		return output;
	}
	
	
	
	public static ValidateUserOutput ValidateUser(ValidateUserInput input) {
		ValidateUserOutput output = new ValidateUserOutput();
		try {
			User user = HTTPserver.getUsersCache().validateUser(input.getUser(), input.getPassword());
			if(user == null) {
				output.initializeInvalid();
			} else {
				output.initializeSucceed();
				output.setHost(input.getHost());
				output.setPort(input.getPort());
				output.getSucceed().setSucceed(user.getFirstname(), user.getLastname(), user.getIndexedrecords());
			}
		} catch(Exception e) {
			e.printStackTrace();
			output.initializeInvalid();
		}
		return output;
	}
}
