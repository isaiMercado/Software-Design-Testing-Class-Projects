package client.controllers.applicationWindow;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import client.model.Manager;
import client.views.windows.ApplicationWindow;

public class WindowResizeListener extends ComponentAdapter { // the override tag is necessary for the method to work
  
	
	
	ApplicationWindow appWindow;
	
	
	
	public WindowResizeListener(ApplicationWindow window) {
		appWindow = window;
	}
	
	
	
	@Override
    public void componentResized(ComponentEvent  we) {
		if(appWindow.getHorizontalSplitPane() != null) {
			appWindow.getHorizontalSplitPane().setDividerLocation(0.5);
		}
	}
	
	
	
}
