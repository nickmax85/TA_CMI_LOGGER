package com.application;

import java.io.File;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.application.server.UDPServer;
import com.application.util.ApplicationProperties;
import com.application.view.Frame;

public class Main {

	private static final Logger logger = Logger.getLogger(Main.class);
	private ResourceBundle resources = ResourceBundle.getBundle("language");

	private Frame frame;
	private UDPServer server;

	public static void main(String[] args) {

		new Main();

	}

	public Main() {

		if (logger.isInfoEnabled())
			logger.info("Programm wurde gestartet.");

		PropertyConfigurator.configure(getClass().getClassLoader().getResource("log4j.properties"));
		ApplicationProperties.configure("application.properties",
				"c:" + File.separator + resources.getString("appname"), "application.properties");
		ApplicationProperties.getInstance().setup();

		// frame = new Frame();
		server = new UDPServer();

	}

}
