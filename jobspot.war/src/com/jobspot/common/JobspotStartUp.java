package com.jobspot.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class JobspotStartUp implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		try{
			new CommandInitializer().init();
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
