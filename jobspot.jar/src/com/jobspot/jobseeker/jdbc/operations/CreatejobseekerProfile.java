package com.jobspot.jobseeker.jdbc.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.util.SQLConnection;

public class CreatejobseekerProfile {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void create(String userCode) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO JOBSEEKER (USER) VALUES(?)";
		
		try{
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userCode);
			ps.executeUpdate();
			
		}
		
		catch(SQLException e){
			logger.error("--> create(): SQLERR"+e);
		}
		
		finally{
			
			try{
				if(ps != null) ps.close();
				if(con != null) con.close();
			}
			catch(SQLException e){
				logger.error("--> create(): SQLERR"+e);
			}
		}
		
	
	}

}
