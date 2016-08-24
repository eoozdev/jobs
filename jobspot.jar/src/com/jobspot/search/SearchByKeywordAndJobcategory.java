package com.jobspot.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.util.SQLConnection;
import com.jobspot.dto.Employer;
import com.jobspot.dto.JobCategory;
import com.jobspot.dto.Vacancy;

public class SearchByKeywordAndJobcategory implements ISearch {

	private int pgNo = 1;
	private int pgSize = 10;

	private String field, keyword;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public SearchByKeywordAndJobcategory(String keyword, String field, int pageNo, int pgSize) {
		this.keyword = keyword;
		this.field = field;

		this.pgNo = pageNo;
		this.pgSize = pgSize;
	}


	@Override
	public Collection<Object> getCollection() {

		
		String sql = "SELECT V.CODE AS VCODE, V.TITLE, V.BASIC, E.CODE, E.NAME, JC.CODE AS JCODE, JC.NAME AS JCNAME FROM VACANCY V INNER JOIN EMPLOYER E ON E.CODE = V.EMPLOYER "
				+ " INNER JOIN VACANCY_JOBCATEGORY VJC ON VJC.VACANCY = V.CODE INNER JOIN JOBCATEGORY JC ON VJC.JOBCATEGORY = JC.CODE "
				+ " WHERE JC.CODE = ? AND V.TITLE LIKE ? AND V.STARTDATE <= current_timestamp AND V.ENDDATE >= current_timestamp"
				+ "	LIMIT "+(pgNo-1)*pgSize+", "+pgSize+"";
		
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		ArrayList<Object> list = null;
		
		try{
			
			
			con = SQLConnection.getConnection();
			ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ps.setMaxRows(pgSize);
			ps.setFetchSize(pgSize);
			ps.setString(1, field);
			ps.setString(2, "%"+keyword+"%");
			rs = ps.executeQuery();
			
			list = new ArrayList<Object>();
			
			while(rs.next()){
				
				SearchResult res = new SearchResult();
				
				Vacancy v = new Vacancy();
				v.setCode(rs.getString("VCODE"));
				v.setTitle(rs.getString("TITLE"));
				v.setBasic(rs.getString("BASIC"));
				
				Employer e = new Employer();
				e.setCode(rs.getString("CODE"));
				e.setName(rs.getString("NAME"));
				
				JobCategory jc = new JobCategory();
				jc.setCode(rs.getString("JCODE"));
				jc.setName(rs.getString("JNAME"));
				
				res.setJobcategory(jc);
				res.setVacancy(v);
				res.setEmployer(e);
				list.add(res);
				
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
		return list;
	
	}

}
