package com.jobspot.employer.jdbc.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.util.SQLConnection;
import com.jobspot.dto.Application;

public class GetVacancyApplications{
	
	private String vacancy;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public GetVacancyApplications(String code){
		this.vacancy = code;
	}
	
	public Collection<Object> getCollection(){
		

		
		String sql = "SELECT APPLIEDON, JOBSEEKER FROM JOBAPPLICATION WHERE VACANCY = ? ";
		
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		ArrayList<Object> list = null;
		
		try{
			
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vacancy);
			rs = ps.executeQuery();
			
			list = new ArrayList<Object>();
			
			while (rs.next()){
				
				Application a = new Application();
				a.setAppliedOn(rs.getString(1));
				a.setJobseeker(rs.getString(2));
				list.add(a);
				
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
		
		return list;
	
	
		
	}
}