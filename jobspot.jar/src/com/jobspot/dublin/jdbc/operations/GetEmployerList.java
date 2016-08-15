package com.jobspot.dublin.jdbc.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.util.SQLConnection;
import com.jobspot.dto.Employer;

public class GetEmployerList {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public ArrayList<Object> getCollection(int value) {
		
		String sql = "SELECT NAME, LOGO, TYPE, FROM EMPLOYER WHERE STATUS = ?";
		
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		ArrayList<Object> employers = null;
		
		try{
			
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, value);
			rs = ps.executeQuery();
			
			employers = new ArrayList<Object>();
			while(rs.next()){
				Employer e = new Employer();
				e.setLogo(rs.getString("LOGO"));
				e.setName(rs.getString("NAME"));
				employers.add(e);
			}
				
			
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
		return employers;
	}

}
