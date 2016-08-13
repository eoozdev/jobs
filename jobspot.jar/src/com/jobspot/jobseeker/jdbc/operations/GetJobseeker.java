package com.jobspot.jobseeker.jdbc.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.util.SQLConnection;
import com.jobspot.dto.Jobseeker;

public class GetJobseeker {

	private Jobseeker js;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public GetJobseeker(Jobseeker js) {
		this.js = js;
		
	}

	public Jobseeker find() {
		
		String sql = "SELECT JS.FIRSTNAME, JS.LASTNAME, JS.DOB, JS.STATUS AS JSTATUS  FROM JOBSEEKER JS WHERE JS.CODE = ? ";
		
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try{
			
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, js.getCode());
			rs = ps.executeQuery();
						
			this.js = new Jobseeker();
			
			while (rs.next()){
								
				js.setDob(rs.getString("DOB"));
				js.setFirstname(rs.getString("FIRSTNAME"));
				js.setLastname(rs.getString("LASTNAME"));
				js.setStatus(rs.getString("JSTATUS"));
								
			}

			
		}
		
		catch(Exception e){
			logger.error("--> getInstance(): SQLE: "+e);
		}
		
		finally{

			try{
				
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			}
			
			catch(SQLException sqle){
				logger.error("--> getInstance(): SQLE"+ sqle);
			}
		}
		
		return this.js;
	
	}

}
