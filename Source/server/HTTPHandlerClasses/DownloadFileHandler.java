package server.HTTPHandlerClasses;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import server.serverFacade.ServerFacade;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

@SuppressWarnings("restriction")
public class DownloadFileHandler implements HttpHandler {
	
	
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		try{
			InputStream output = ServerFacade.DownloadFile(exchange.getRequestURI().getPath());
			exchange.sendResponseHeaders(200, 0); // I was having exceptions of headers not sent because this line goes before copying inputStream into OutputStream
			IOUtils.copy(output, exchange.getResponseBody());
			exchange.getResponseBody().close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	System.out.println("in download handle");
	System.out.println("host "+exchange.getRequestURI().getHost());
	System.out.println("path "+exchange.getRequestURI().getPath());
	System.out.println("port "+exchange.getRequestURI().getPort());
	System.out.println("query "+exchange.getRequestURI().getQuery());
	System.out.println("rawAuthority "+exchange.getRequestURI().getRawAuthority());
	System.out.println("rawFragment "+exchange.getRequestURI().getRawFragment());
	System.out.println("rawPath "+exchange.getRequestURI().getRawPath());
	System.out.println("rawQuery "+exchange.getRequestURI().getRawQuery());
	System.out.println("rawSchemesSpecificPart "+exchange.getRequestURI().getRawSchemeSpecificPart());
	System.out.println("rawuserInfo "+exchange.getRequestURI().getRawUserInfo());
	System.out.println("scheme "+exchange.getRequestURI().getScheme());
	System.out.println("schemesSpecificPart "+exchange.getRequestURI().getSchemeSpecificPart());
	System.out.println("userInfo "+exchange.getRequestURI().getUserInfo());
	*/

}
