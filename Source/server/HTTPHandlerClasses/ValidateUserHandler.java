package server.HTTPHandlerClasses;

import java.io.IOException;

import server.serverFacade.ServerFacade;
import shared.communicationClasses.ValidateUserInput;
import shared.communicationClasses.ValidateUserOutput;

//import javax.xml.ws.spi.http.HttpExchange;
//import javax.xml.ws.spi.http.HttpHandler;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@SuppressWarnings("restriction")
public class ValidateUserHandler implements HttpHandler {
	
	
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		try {
			XStream xstream = new XStream(new DomDriver()); // this dom driver is super important... it does not deserializes without it
			ValidateUserInput input = (ValidateUserInput) xstream.fromXML(exchange.getRequestBody());
			ValidateUserOutput output = ServerFacade.ValidateUser(input);
			exchange.sendResponseHeaders(200, 0);
			xstream.toXML(output, exchange.getResponseBody());
			exchange.getResponseBody().close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
