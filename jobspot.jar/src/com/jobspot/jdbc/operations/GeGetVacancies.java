package com.jobspot.jdbc.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.util.SQLConnection;
import com.jobspot.dto.Vacancy;

public class GeGetVacancies {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Collection<Object> find(String employer){
		
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "SELECT VT.TOWN, VJC.JOBCATEGORY, V.TITLE, V.ARTWORK, V.BASIC, V.STARTDATE, V.ENDDATE FROM VACANCY V "
				+ " INNER JOIN VACANCY_JOBCATEGORY VJC ON VJC.VACANCY = V.CODE "
				+ " INNER JOIN VACANCY_TOWN VT ON VT.VACANCY = V.CODE WHERE V.EMPLOYER = ?";
		Collection<Object> vacancies = new ArrayList<Object>();
		
		try{
			
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, employer);
			rs = ps.executeQuery();
			
			while(rs.next()){

				Vacancy v = new Vacancy();

				v.setTown(rs.getString(1));
				v.setJobcategory(rs.getString(2));
				v.setTitle(rs.getString(3));
				v.setArtwork(rs.getString(4));
				v.setBasic(rs.getString(5));
				v.setDisplayfrom(rs.getString(6));
				v.setDisplayto(rs.getString(7));
				vacancies.add(v);
				
			}
			
		}
		
		catch(Exception e){
			logger.error("find()"+e);
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
		
		return vacancies;
	}
}
