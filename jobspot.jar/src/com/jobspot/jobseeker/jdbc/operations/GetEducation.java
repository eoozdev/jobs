package com.jobspot.jobseeker.jdbc.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.util.SQLConnection;
import com.jobspot.dto.Education;

public class GetEducation {
	
	private String jobseekerCode;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public GetEducation(String js) {
		jobseekerCode = js;
		
	}

	public ArrayList<Education> getCollection() {
		
		String sql = "SELECT CODE, PERIOD, QUALIFICATION, QUALIFCATIONCODE FROM EDUCATION WHERE JOBSEEKER = ? ";
		
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		ArrayList<Education> education = null;
		
		try{
			
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, jobseekerCode);
			rs = ps.executeQuery();
			
			education = new ArrayList<Education>();
			
			while (rs.next()){
				
				Education e = new Education();
	
				e.setCode(rs.getString("CODE"));
				e.setPeriod(rs.getString("PERIOD"));
				e.setQualification(rs.getString("QUALIFICATION"));
				e.setQualificationCode(rs.getString("QUALIFCATIONCODE"));

				education.add(e);
				
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
		
		return education;
	
	}
}
