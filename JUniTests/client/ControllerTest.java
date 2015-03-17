package client;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import servertester.controllers.*;
import servertester.views.ServerOp;
public class ControllerTest {
	
	public Controller controller = new Controller();
	
	@Test
	public void test_get_projects() {
		try {
			String[] test = new String[2];
			test[0] = "test1";
			test[1] = "test1";
			controller.getView().setParameterValues(test);
			controller.getView().setOperation(ServerOp.GET_PROJECTS);
			controller.operationSelected();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
