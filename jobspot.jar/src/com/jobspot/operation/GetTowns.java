package com.jobspot.operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.util.SQLConnection;
import com.jobspot.master.JobCategory;

public class GetTowns {

	private int status;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public GetTowns(int status) {
		this.status = status;
	}
	
	
	public Collection<Object> getCollection() {
		
		String sql = "SELECT CODE, NAME FROM TOWN WHERE STATUS = ?";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		Collection<Object> list = null;
		try{
			
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, status);
			rs = ps.executeQuery();
			list = new ArrayList<Object>();
			
			while (rs.next()){
				
				JobCategory c = new JobCategory();
				c.setCode(rs.getString(1));
				c.setName(rs.getString(2));
				list.add(c);
				
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
