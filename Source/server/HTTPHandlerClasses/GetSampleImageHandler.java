package server.HTTPHandlerClasses;

import java.io.IOException;

import server.serverFacade.ServerFacade;
import shared.communicationClasses.*;

//import javax.xml.ws.spi.http.HttpExchange;
//import javax.xml.ws.spi.http.HttpHandler;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@SuppressWarnings("restriction")
public class GetSampleImageHandler implements HttpHandler {
	
	
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		try {
			XStream xstream = new XStream(new DomDriver());
			GetSampleImageInput input = (GetSampleImageInput) xstream.fromXML(exchange.getRequestBody());
			GetSampleImageOutput output = ServerFacade.GetSampleImage(input);
			exchange.sendResponseHeaders(200, 0);
			xstream.toXML(output, exchange.getResponseBody());
			exchange.getResponseBody().close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
