package com.jobspot.dublin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.util.SQLConnection;
import com.jobspot.dto.Employer;
import com.jobspot.dto.JobCategory;
import com.jobspot.dto.Town;
import com.jobspot.dto.Vacancy;

public class GetVacancy {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public VacancyView find(String code){
		
		VacancyView view = new VacancyView();
		Vacancy v = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "SELECT V.TITLE, V.ARTWORK, V.STARTDATE, V.ENDDATE, V.BASIC, E.NAME, E.CONTACTNUMBER, E.CONTACTEMAIL, T.NAME, JC.NAME FROM VACANCY V "
				+ " INNER JOIN EMPLOYER E ON E.CODE = V.EMPLOYER "
				+ " INNER JOIN VACANCY_JOBCATEGORY VJC ON VJC.VACANCY = V.CODE "
				+ " INNER JOIN JOBCATEGORY JC ON JC.CODE = VJC.JOBCATEGORY "
				+ " INNER JOIN VACANCY_LOCATION VT ON VT.VACANCY = V.CODE"
				+ " INNER JOIN TOWN T ON T.CODE = VT.TOWN "
				+ " WHERE V.CODE = ?";
		
		try{
			
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, code);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				v = new Vacancy();
				
				v.setTitle(rs.getString("TITLE"));
				v.setArtwork(rs.getString("ARTWORK"));
				v.setBasic(rs.getString("BASIC"));
				v.setDisplayfrom(rs.getString("STARTDATE"));
				v.setDisplayto(rs.getString("ENDDATE"));
				
				Town t = new Town();
				t.setName(rs.getString("TNAME"));
				
				JobCategory jc = new JobCategory();
				jc.setName(rs.getString("JCNAME"));
				
				Employer e = new Employer();
				e.setName(rs.getString("ENAME"));
				e.setContactnumber(rs.getString("CONTACTNUMBER"));
				e.setContactEmail(rs.getString("CONTACTEMAIL"));
				
				view = new VacancyView(v, t, e, jc);
				
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
				logger.error("find()"+sqle);
			}
		}
		return view;
	}

}
