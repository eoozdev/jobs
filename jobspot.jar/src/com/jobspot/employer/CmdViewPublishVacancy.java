package com.jobspot.employer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.command.AbstractCommand;
import com.eooz.common.command.GetCommand;
import com.eooz.common.command.ICommand;
import com.eooz.common.mask.Mask;
import com.eooz.common.page.IPage;
import com.eooz.common.page.Page;
import com.eooz.common.util.RequestWrap;
import com.eooz.common.util.ResponseWrap;
import com.eooz.common.util.SYSTEM_MESAGE;
import com.eooz.security.SecurityUtil;
import com.jobspot.employer.jdbc.operations.GetJobCategories;
import com.jobspot.employer.jdbc.operations.GetTowns;

public class CmdViewPublishVacancy extends AbstractCommand implements GetCommand{


	private Logger logger = LoggerFactory.getLogger(this.getClass());
	

	CmdViewPublishVacancy(){
		logger.info("--> default construction of command.");
	}

	
	
	
	public CmdViewPublishVacancy(RequestWrap request, ResponseWrap response) {
		// TODO Auto-generated constructor stub
	}




	@Override
	public String doWork() {
		
		IPage page = new Page();
		
		try{
						
			if(SecurityUtil.isNotAuthenticated()){
				page.setHrefToLogin();
				page.setMessage(SYSTEM_MESAGE.NOT_LOGGED_IN.value());
			}
			
			else{
	
				Collection<Object> categories = new ArrayList<Object>();
				categories = new GetJobCategories(Mask.ACTIVE.value()).getCollection();
			
				Collection<Object> towns = new ArrayList<Object>();
				towns = new GetTowns(Mask.ACTIVE.value()).getCollection();
				
				page.setCollectionOne("job_categories",categories, new HashMap<String, Collection<Object>>());
				page.setCollectionTwo("towns",towns, new HashMap<String, Collection<Object>>());
				page.setHref("/member/employer/public_vacancy.jsp");
					
				}
	
			}
		
		catch(Exception e){
			logger.error("doWork(): general exception"+e);
		}	
		
		return toJson(page);
	}



	@Override
	public ICommand create(RequestWrap request, ResponseWrap response) {
		return new CmdViewPublishVacancy(request, response);
	}








}
