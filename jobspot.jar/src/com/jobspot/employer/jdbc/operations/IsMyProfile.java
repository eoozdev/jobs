package com.jobspot.employer.jdbc.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.util.SQLConnection;

public class IsMyProfile {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public boolean no(String employerCode) {

		boolean no = false;
		String sql = "SELECT CODE FROM EMPLOYER WHERE CODE = ?";
		
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, employerCode);
			rs = ps.executeQuery();
			
			no = !rs.next();
				
			
		}
		
		catch(Exception e){
			logger.error("--> no(): SQLE: "+e);
		}
		
		finally{

			try{
				
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			}
			
			catch(SQLException sqle){
				logger.error("--> no(): SQLE"+ sqle);
			}
		}
		
		return no;
	
	
	}
}
