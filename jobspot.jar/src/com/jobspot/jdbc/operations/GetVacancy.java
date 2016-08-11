package com.jobspot.jdbc.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.util.SQLConnection;
import com.jobspot.dto.Vacancy;

public class GetVacancy {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Vacancy find(String code){
		
		Vacancy v = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "SELECT VT.TOWN, VJC.JOBCATEGORY, V.TITLE, V.ARTWORK, V.BASIC, V.STARTDATE, V.ENDDATE, V.CODE FROM VACANCY V "
				+ " INNER JOIN VACANCY_JOBCATEGORY VJC ON VJC.VACANCY = V.CODE "
				+ " INNER JOIN VACANCY_TOWN VT ON VT.VACANCY = V.CODE WHERE V.CODE = ?";
		
		try{
			
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, code);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				v = new Vacancy();
				v.setTown(rs.getString(1));
				v.setJobcategory(rs.getString(2));
				v.setTitle(rs.getString(3));
				v.setArtwork(rs.getString(4));
				v.setBasic(rs.getString(5));
				v.setDisplayfrom(rs.getString(6));
				v.setDisplayto(rs.getString(7));
				v.setCode(rs.getString(8));
				
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
		return v;
	}

}
