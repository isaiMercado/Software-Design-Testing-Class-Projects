package server.HTTPHandlerClasses;

import java.io.IOException;
import server.serverFacade.ServerFacade;
import shared.communicationClasses.*;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@SuppressWarnings("restriction")
public class SubmitBatchHandler implements HttpHandler {
	
	
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		try {
			XStream xstream = new XStream(new DomDriver());
			SubmitBatchInput input = (SubmitBatchInput) xstream.fromXML(exchange.getRequestBody());
			SubmitBatchOutput output = ServerFacade.SubmitBatch(input);
			exchange.sendResponseHeaders(200, 0);
			xstream.toXML(output, exchange.getResponseBody());
			exchange.getResponseBody().close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
