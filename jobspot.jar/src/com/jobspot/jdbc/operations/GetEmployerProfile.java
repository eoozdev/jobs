package com.jobspot.jdbc.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.util.SQLConnection;
import com.jobspot.dto.Employer;

public class GetEmployerProfile {


	private Employer e;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	

	public GetEmployerProfile(Employer e) {
		this.e = e;
	}

	public Employer getEmployer() {
		return e;
	}

	public void setEmployer(Employer e) {
		this.e = e;
	}
	
	
	public Object getInstance(String code) {
		
		String sql = "SELECT CODE, NAME, STATUS, TOWN, INDUSTRY, COMPANYSIZE, ABOUT, LOGO, TYPE FROM EMPLOYER WHERE CODE = ?";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try{
			
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, code);
			rs = ps.executeQuery();
			
			while (rs.next()){
				
				e = new Employer();
				e.setCode(rs.getString(1));
				e.setName(rs.getString(2));
				e.setStatus(rs.getString(3));
				e.setTown(rs.getString(4));
				e.setIndustry(rs.getString(5));
				e.setCompanySize(rs.getString(6));
				e.setAbout(rs.getString(7));
				e.setLogo(rs.getString(8));
				e.setType(rs.getString(9));
				
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
		
		return e;
	}
	
	}
