package com.jobspot.dublin.jdbc.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.util.SQLConnection;

public class ApplyToVacancy {

	private String vacancy;
	private String jobseeker;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public ApplyToVacancy(String jobseeker, String code) {
		this.vacancy = code;
		this.jobseeker = jobseeker;
	}

	public void run()throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO JOBAPPLICATION (VACANCY, JOBSEEKER) VALUES(?,?)";
		

		try{
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vacancy);
			ps.setString(2, jobseeker);
			
			ps.executeUpdate();
			
		}
		
		catch(SQLException ex){
			logger.error("--> run(): "+ex);
		}
		
		finally{
			
			try{
				
				if(ps != null) ps.close();
				if(con != null) con.close();
				
			}
			
			catch(SQLException sqle){
				logger.error(" run(): resource clean up: "+sqle);
			}
		}
	}

}
