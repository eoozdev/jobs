package com.jobspot.jobseeker.jdbc.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.util.SQLConnection;
import com.jobspot.dto.Language;

public class GetLanguages {
	private String js;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public GetLanguages(String js) {
		this.js = js;
		
	}

	public ArrayList<Language> getCollection() {
		
		String sql = "SELECT CODE, NAME, PROFICIENCY FROM LANGUAGE WHERE JOBSEEKER = ? ";
		
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		ArrayList<Language> languages = null;
		
		try{
			
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, js);
			rs = ps.executeQuery();
			
			languages = new ArrayList<Language>();
			
			while (rs.next()){
				

				Language l = new Language();

				l.setCode(rs.getString("CODE"));
				l.setName(rs.getString("NAME"));
				l.setProficiency("PROFICIENCY");
				
				languages.add(l);
				
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
		
		return languages;
	
	}
}
