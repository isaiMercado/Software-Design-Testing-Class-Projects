package client.controllers.applicationWindow;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import client.model.Manager;

public class WindowClosingListener extends WindowAdapter { // the override tag is necessary for the method to work
        @Override
        public void windowClosing(WindowEvent we) {
            Manager.exit();  
    }
}
