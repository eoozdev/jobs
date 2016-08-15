package com.jobspot.dublin;

import java.util.Collection;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.command.AbstractCommand;
import com.eooz.common.command.GetCommand;
import com.eooz.common.command.ICommand;
import com.eooz.common.page.IPage;
import com.eooz.common.page.Page;
import com.eooz.common.util.RequestWrap;
import com.eooz.common.util.ResponseWrap;
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
		
		IPage page = new Page();
		
		try{			
			
			ISearch search = getQuery();
			Collection<Object> list = search.getCollection();
			page.setCollectionOne("SEARCH_RESULT", list, new HashMap<>());			
			
		}
		
		catch(Exception e){
			logger.error("--> doWork(): GENERAL ERROR: "+e);
		}
		
		return toJson(page);
	}

	private ISearch getQuery() {
		
		String town = request.getParameter("town");
		String field = request.getParameter("field");
		String keyword = request.getParameter("keyword");
		
		if(field == null && town == null){
			return new SearchByKeyword(keyword);
		}
		
		else if(field == null && keyword == null){
			return new SearchByTown(town);
		}
		
		else if(town == null && keyword == null){
			return new SearchByJobcategory(field);
		}
		
		else if(town == null){
			return new SearchByKeywordAndJobcategory(keyword, field);
		}
		
		else if (field == null){
			return new SearchByTownAndKeyword(town, keyword);
		}
		
		else if (keyword == null){
			return new SearchByTownAndField(town, field);
		}
		
		else {
			return new SearchByKeywordTownAndJobCategory(keyword, town, field);
		}
		
	}

	@Override
	public ICommand create(RequestWrap request, ResponseWrap response) {
		return new CmdSearchForVacancy(request, response);
	}

}
