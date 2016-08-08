package com.eooz.common.util;

import java.sql.Connection;
import javax.sql.DataSource;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SQLConnection {
	
	private static DataSource ds;
	private static Logger logger = Logger.getLogger("SQLConnection");
	private static String DS_NAME_SPACE = "java:jboss/eoozPublic";
	
	
	static {
		
		try{
			ds = (DataSource)new InitialContext().lookup(DS_NAME_SPACE);
		}
		
		catch(NamingException ne){
			logger.log(Level.SEVERE, "--> static init bock: retrieving DataSource", ne);
		}
		logger.log(Level.SEVERE,"data source retrieved");
	}
	
	
	// returns a new javax.sql Connection
	public static Connection getConnection(){
		
		try{
			return ds.getConnection();
		}
		
		catch(Exception e){
			logger.log(Level.SEVERE, "--> getConnection(): retrieving sql connection", e);
			return null;
		}
		
	}
	
}