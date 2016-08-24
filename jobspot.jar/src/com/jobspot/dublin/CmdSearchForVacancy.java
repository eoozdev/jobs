package com.jobspot.dublin;

import java.util.Collection;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.command.AbstractCommand;
import com.eooz.common.command.GetCommand;
import com.eooz.common.command.ICommand;
import com.eooz.common.util.RequestWrap;
import com.eooz.common.util.ResponseWrap;
import com.eooz.common.util.SYSTEM_MESAGE;
import com.google.gson.Gson;
import com.jobspot.search.ISearch;
import com.jobspot.search.SearchByJobcategory;
import com.jobspot.search.SearchByKeyword;
import com.jobspot.search.SearchByKeywordAndJobcategory;
import com.jobspot.search.SearchByKeywordTownAndJobCategory;
import com.jobspot.search.SearchByTown;
import com.jobspot.search.SearchByTownAndField;
import com.jobspot.search.SearchByTownAndKeyword;

public class CmdSearchForVacancy extends AbstractCommand implements GetCommand{

	private RequestWrap request;
	private ResponseWrap response;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public CmdSearchForVacancy(RequestWrap request, ResponseWrap response) {
		this.request = request;
		this.response = response;
	}

	public CmdSearchForVacancy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doWork() {
		
		String response = SYSTEM_MESAGE.UNEXPECTED_ERROR.value();
		
		try{
			
			Gson gson = new Gson();
			ISearch search = getQuery();
			Collection<Object> list = search.getCollection();
			response = gson.toJson(list);
		}
		
		catch(Exception e){
			logger.error("--> doWork(): e: "+e);
		}
		
		
		return response;
	}

	private ISearch getQuery(){
		
		String town = request.getParameter("town");
		String field = request.getParameter("field");
		String keyword = request.getParameter("keyword");
		
		int pageSize = 10;
		int pageNo = 1;
		
		try{
			pageNo = Integer.parseInt(request.getParameter("pgNo"));
		}
		
		catch(NumberFormatException nfe){
			pageNo = 1;
		}
		
		if(field == null && town == null){
			return new SearchByKeyword(keyword, pageNo, pageSize);
		}
		
		else if(field == null && keyword == null){
			return new SearchByTown(town, pageNo, pageSize);
		}
		
		else if(town == null && keyword == null){
			return new SearchByJobcategory(field, pageNo, pageSize);
		}
		
		else if(town == null){
			return new SearchByKeywordAndJobcategory(keyword, field, pageNo, pageSize);
		}
		
		else if (field == null){
			return new SearchByTownAndKeyword(town, keyword, pageNo, pageSize);
		}
		
		else if (keyword == null){
			return new SearchByTownAndField(town, field, pageNo, pageSize);
		}
		
		else {
			return new SearchByKeywordTownAndJobCategory(keyword, town, field, pageNo, pageSize);
		}
		
	}

	@Override
	public ICommand create(RequestWrap request, ResponseWrap response) {
		return new CmdSearchForVacancy(request, response);
	}

}
