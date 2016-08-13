package com.jobspot.jobseeker.jdbc.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.util.SQLConnection;
import com.jobspot.dto.WorkExperience;

public class GetWorkExperience {

	private String jobseekerCode;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public GetWorkExperience(String js) {
		jobseekerCode = js;
		
	}

	
	public ArrayList<WorkExperience> getCollection() {
		
		String sql = "SELECT CODE, PERIOD, WORKEDAT, POSITION FROM WORKEXPERIENCE WHERE JOBSEEKER = ? ";
		
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		ArrayList<WorkExperience> workexperience = null;
		
		try{
			
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, jobseekerCode);
			rs = ps.executeQuery();
			
			 workexperience = new ArrayList<WorkExperience>();
			
			while (rs.next()){
				
				WorkExperience w = new WorkExperience();

				w.setWorkedAt(rs.getString("WORKEDAT"));
				w.setCode(rs.getString("CODE"));
				w.setPeriod(rs.getString("PERIOD"));
				w.setPosition(rs.getString("POSITION"));
				workexperience.add(w);
			}

			
		}
		
		catch(Exception e){
			logger.error("--> getCollection(): SQLE: "+e);
		}
		
		finally{

			try{
				
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			}
			
			catch(SQLException sqle){
				logger.error("--> getCollection(): SQLE"+ sqle);
			}
		}
		
		return workexperience;
	
	}
}
