package servertester.controllers;

import java.util.*;

import client.clientCommunicator.ClientCommunicator;
import servertester.views.*;
import shared.communicationClasses.*;

public class Controller implements IController {

	private IView _view;
	
	public Controller() {
		return;
	}
	
	public IView getView() {
		return _view;
	}
	
	public void setView(IView value) {
		_view = value;
	}
	
	// IController methods
	//
	
	@Override
	public void initialize() {
		getView().setHost("localhost");
		getView().setPort("39640");
		operationSelected();
	}

	@Override
	public void operationSelected() {
		ArrayList<String> paramNames = new ArrayList<String>();
		paramNames.add("User");
		paramNames.add("Password");
		
		switch (getView().getOperation()) {
		case VALIDATE_USER:
			break;
		case GET_PROJECTS:
			break;
		case GET_SAMPLE_IMAGE:
			paramNames.add("Project");
			break;
		case DOWNLOAD_BATCH:
			paramNames.add("Project");
			break;
		case GET_FIELDS:
			paramNames.add("Project");
			break;
		case SUBMIT_BATCH:
			paramNames.add("Batch");
			paramNames.add("Record Values");
			break;
		case SEARCH:
			paramNames.add("Fields");
			paramNames.add("Search Values");
			break;
		default:
			assert false;
			break;
		}
		
		getView().setRequest("");
		getView().setResponse("");
		getView().setParameterNames(paramNames.toArray(new String[paramNames.size()]));
	}

	@Override
	public void executeOperation() {
		switch (getView().getOperation()) {
		case VALIDATE_USER:
			validateUser();
			break;
		case GET_PROJECTS:
			getProjects();
			break;
		case GET_SAMPLE_IMAGE:
			getSampleImage();
			break;
		case DOWNLOAD_BATCH:
			downloadBatch();
			break;
		case GET_FIELDS:
			getFields();
			break;
		case SUBMIT_BATCH:
			submitBatch();
			break;
		case SEARCH:
			search();
			break;
		default:
			assert false;
			break;
		}
	}
	
	
	
	private void validateUser() {
		System.out.println("controller");
		ValidateUserOutput output = new ValidateUserOutput();
		try {
			String[] strings = getView().getParameterValues();
			String username = strings[0];
			String password = strings[1];
			if(getView().getPort() != null && getView().getPort().length() != 0) {
				ClientCommunicator.setPort(Integer.parseInt(getView().getPort()));
			}
			if(getView().getHost() != null && getView().getHost().length() != 0) {
				ClientCommunicator.setHost(getView().getHost());
			}
			ValidateUserInput input = new ValidateUserInput(username, password);
			getView().setRequest(input.toString());
			output = ClientCommunicator.ValidateUser(input);
			if (output.getSucceed() != null) {
				getView().setResponse(output.getSucceed().toString());
			} else {
				getView().setResponse(output.getInvalid().toString());
			}
		} catch(Exception e) {
			output.initializeFailed();
			getView().setResponse(output.getFailed().toString());
			e.printStackTrace();
		}
 	}
	
	
	
	private void getProjects() {
		System.out.println("controller");
		GetProjectsOutput output = new GetProjectsOutput();
		try {
			String[] strings = getView().getParameterValues();
			String username = strings[0];
			String password = strings[1];
			if(getView().getPort() != null && getView().getPort().length() != 0) {
				ClientCommunicator.setPort(Integer.parseInt(getView().getPort()));
			}
			if(getView().getHost() != null && getView().getHost().length() != 0) {
				ClientCommunicator.setHost(getView().getHost());
			}
			GetProjectsInput input = new GetProjectsInput(username, password);
			getView().setRequest(input.toString());
			output = ClientCommunicator.GetProjects(input);
			if (output.getSucceed() != null) {
				getView().setResponse(output.getSucceed().toString());
			} else {
				getView().setResponse(output.getInvalid().toString());
			}
		} catch(Exception e) {
			output.initializeFailed();
			getView().setResponse(output.getFailed().toString());
			e.printStackTrace();
		}
	}
	
	
	
	private void getSampleImage() {
		GetSampleImageOutput output = new GetSampleImageOutput();//2
		try {
			String[] strings = getView().getParameterValues();
			String username = strings[0];
			String password = strings[1];
			String projectid = strings[2];
			if(getView().getPort() != null && getView().getPort().length() != 0) {
				ClientCommunicator.setPort(Integer.parseInt(getView().getPort()));
			}
			if(getView().getHost() != null && getView().getHost().length() != 0) {
				ClientCommunicator.setHost(getView().getHost());
			}
			GetSampleImageInput input = new GetSampleImageInput(username, password, Integer.parseInt(projectid));//2
			getView().setRequest(input.toString());
			output = ClientCommunicator.GetSampleImage(input);//1
			if (output.getSucceed() != null) {
				getView().setResponse(output.getSucceed().toString());
			} else {
				getView().setResponse(output.getInvalid().toString());
			}
		} catch(Exception e) {
			output.initializeFailed();
			getView().setResponse(output.getFailed().toString());
			e.printStackTrace();
		}
	}
	
	
	
	private void downloadBatch() {
		DownloadBatchOutput output = new DownloadBatchOutput();//2
		try {
			String[] strings = getView().getParameterValues();
			String username = strings[0];
			String password = strings[1];
			String projectid = strings[2];
			if(getView().getPort() != null && getView().getPort().length() != 0) {
				ClientCommunicator.setPort(Integer.parseInt(getView().getPort()));
			}
			if(getView().getHost() != null && getView().getHost().length() != 0) {
				ClientCommunicator.setHost(getView().getHost());
			}
			DownloadBatchInput input = new DownloadBatchInput(username, password, Integer.parseInt(projectid));//2
			getView().setRequest(input.toString());
			output = ClientCommunicator.DownloadBatch(input);//1
			if (output.getSucceed() != null) {
				getView().setResponse(output.getSucceed().toString());
			} else {
				getView().setResponse(output.getInvalid().toString());
			}
		} catch(Exception e) {
			output.initializeFailed();
			getView().setResponse(output.getFailed().toString());
			e.printStackTrace();
		}
	}
	
	
	
	private void getFields() { // how to test empty string??
		GetFieldsOutput output = new GetFieldsOutput();//2
		try {
			String[] strings = getView().getParameterValues();
			String username = strings[0];
			String password = strings[1];
			String projectid = null;
			if (strings[2] != null) 
				if (strings[2] != "") 
					if (strings[2] != " ") 
					projectid = strings[2];
			if(getView().getPort() != null && getView().getPort().length() != 0) {
				ClientCommunicator.setPort(Integer.parseInt(getView().getPort()));
			}
			if(getView().getHost() != null && getView().getHost().length() != 0) {
				ClientCommunicator.setHost(getView().getHost());
			}
			GetFieldsInput input = new GetFieldsInput(username, password);//2
			if (projectid != null) input.setProjectid(Integer.parseInt(projectid));
			//if (strings[2] != null) if (strings[2] != "") input.setProjectid(Integer.parseInt(strings[2]));
			getView().setRequest(input.toString());
			output = ClientCommunicator.GetFields(input);//1
			if (output.getSucceed() != null) {
				getView().setResponse(output.getSucceed().toString());
			} else {
				getView().setResponse(output.getInvalid().toString());
			}
		} catch(Exception e) {
			output.initializeFailed();
			getView().setResponse(output.getFailed().toString());
			e.printStackTrace();
		}
	}
	
	
	
	private void submitBatch() {
		SubmitBatchOutput output = new SubmitBatchOutput();//2
		try {
			String[] strings = getView().getParameterValues();
			String username = strings[0];
			String password = strings[1];
			String imageid = strings[2];
			String fieldValues = strings[3];
			if(getView().getPort() != null && getView().getPort().length() != 0) {
				ClientCommunicator.setPort(Integer.parseInt(getView().getPort()));
			}
			if(getView().getHost() != null && getView().getHost().length() != 0) {
				ClientCommunicator.setHost(getView().getHost());
			}
			SubmitBatchInput input = new SubmitBatchInput(username, password,Integer.parseInt(imageid), fieldValues);
			input.parseValues();
			getView().setRequest(input.toString());
			output = ClientCommunicator.SubmitBatch(input);//1
			if (output.getSucceed() != null) {
				getView().setResponse(output.getSucceed().toString());
			} else {
				getView().setResponse(output.getInvalid().toString());
			}
		} catch(Exception e) {
			output.initializeFailed();
			getView().setResponse(output.getFailed().toString());
			e.printStackTrace();
			System.out.println("failed because there is no connection");
		}
	}
	
	
	
	private void search() {
		SearchOutput output = new SearchOutput();//2
		try {
			String[] strings = getView().getParameterValues();
			String username = strings[0];
			String password = strings[1];
			String field_ids = strings[2];
			String searchValues = strings[3];
			if(getView().getPort() != null && getView().getPort().length() != 0) {
				ClientCommunicator.setPort(Integer.parseInt(getView().getPort()));
			}
			if(getView().getHost() != null && getView().getHost().length() != 0) {
				ClientCommunicator.setHost(getView().getHost());
			}
			SearchInput input = new SearchInput(username, password, field_ids, searchValues);//2
			getView().setRequest(input.toString());
			output = ClientCommunicator.Search(input);//1
			if (output.getSucceed() != null) {
				getView().setResponse(output.getSucceed().toString());
			} else {
				getView().setResponse(output.getInvalid().toString());
			}
		} catch(Exception e) {
			output.initializeFailed();
			getView().setResponse(output.getFailed().toString());
			e.printStackTrace();
		}
	}

}

