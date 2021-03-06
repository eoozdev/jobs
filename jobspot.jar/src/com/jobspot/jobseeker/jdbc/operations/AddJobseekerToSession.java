package com.jobspot.jobseeker.jdbc.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.util.AddToSession;
import com.eooz.common.util.SQLConnection;
import com.eooz.security.SecurityUtil;

public class AddJobseekerToSession implements AddToSession {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void add() {

		
		String username = SecurityUtil.getUsername();
		
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "SELECT J.CODE FROM JOBSEEKER J INNER JOIN SYSTEMUSER U ON U.CODE = J.USER WHERE U.USERNAME = ?";
		
		try{
			
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			if(rs.next()){
				String code = rs.getString(1);
				SecurityUtil.addToSession("JOBSEEKER_CODE",code);
			}
			
		}
		
		catch(Exception e){
			logger.error("add()"+e);
		}
		
		finally{
			
			try{
				
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
				
			}
			
			catch(SQLException sqle){
				logger.error("add()"+sqle);
			}
		}
		
		
	
	}

}
