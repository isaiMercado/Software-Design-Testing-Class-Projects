package server;

public class ServerUnitTests {
	
	public static void main(String[] args) {
		
		String[] testClasses = new String[] {
				"server.dataBaseAccessClasses.ValueDAOTests",
				"client.clientCommunicator.ClientCommunicatorTests",
		};

		org.junit.runner.JUnitCore.main(testClasses);
	}
	
}

