package com.jobspot.jobseeker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.util.SQLConnection;
import com.jobspot.dto.Education;
import com.jobspot.dto.Jobseeker;
import com.jobspot.dto.Language;
import com.jobspot.dto.WorkExperience;

public class GetJobseekerProfile {

	private Jobseeker js;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public GetJobseekerProfile(Jobseeker js) {
		this.js = js;
		
	}

	public Jobseeker find() {
		
		String sql = "SELECT JS.FIRSTNAME, JS.LASTNAME, JS.DOB, JS.STATUS, W.WORKEDAT, W.PERIOD AS WPERIOD, W.POSITION, W.CODE AS WCODE, "
				+ "		L.NAME, L.PROFICIENCY, L.CODE AS LCODE, E.CODE AS ECODE, E.QUALIFICATION, E.PERIOD AS EPERIOD, E.QUALIFICATIONCODE FROM JOBSEEKER"
				+ "		INNER JOIN WORKEXPERIENCE W ON W.JOBSEEKER = JS.CODE INNER JOIN EDUCATION E ON E.JOBSEEKER = J.CODE INNER JOIN ON L.JOBSEEKER = J.CODE WHERE J.CODE = ? ";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try{
			
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, js.getCode());
			rs = ps.executeQuery();
			
			ArrayList<Language> languages = new ArrayList<Language>();
			ArrayList<Education> education = new ArrayList<Education>();
			ArrayList<WorkExperience> workexperience = new ArrayList<WorkExperience>();
			
			this.js = new Jobseeker();
			
			while (rs.next()){
				

				Language l = new Language();
				Education e = new Education();
				WorkExperience w = new WorkExperience();
				
				js.setDob(rs.getString("DOB"));
				js.setFirstname(rs.getString("FIRSTNAME"));
				js.setLastname(rs.getString("LASTNAME"));
				js.setStatus(rs.getString("STATUS"));
				
				w.setWorkedAt(rs.getString("WORKEDAT"));
				w.setCode(rs.getString("WCODE"));
				w.setPeriod(rs.getString("WPERIOD"));
				w.setPosition(rs.getString("POSITION"));
				
				e.setCode(rs.getString("ECODE"));
				e.setPeriod(rs.getString("EPERIOD"));
				e.setQualification(rs.getString("QUALIFICATION"));
				e.setQualificationCode(rs.getString("QUALIFICATIONCODE"));

				l.setCode(rs.getString("LCODE"));
				l.setName(rs.getString("NAME"));
				l.setProficiency("PROFICIENCY");
				
				languages.add(l);
				education.add(e);
				workexperience.add(w);
				
			}

			this.js.setLanguage(languages);
			this.js.setEducation(education);
			this.js.setWorkExperience(workexperience);
			
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
