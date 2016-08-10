package com.jobspot.employer;

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
import com.eooz.common.util.SYSTEM_MESAGE;
import com.eooz.security.SecurityUtil;
import com.jobspot.operation.GeGetVacancies;

public class CmdShowVacanciesPg extends AbstractCommand implements GetCommand {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public String doWork() {
		
		IPage page = new Page();
		
		try{
			
			if(SecurityUtil.isNotAuthenticated()){
				page.setHrefToLogin();
				page.setMessage(SYSTEM_MESAGE.NOT_LOGGED_IN.value());
			}
			
			else{
				
				String employer = SecurityUtil.getFromSession("EMPLOYER_CODE");
				
				Collection<Object> vacancies = new GeGetVacancies().find(employer); 
				page.setCollectionOne("vacancies", vacancies, new HashMap<String, Collection<Object>>());
				
			}
			
		}
		
		catch(Exception e){
			logger.error("--> doWork: ERR"+e);
		}
		
		
		
		return toJson(page);
	}

	@Override
	public ICommand create(RequestWrap request, ResponseWrap response) {
		return new CmdShowVacanciesPg();
	}

	@Override
	public IPage getParameters(RequestWrap request) {
		// TODO Auto-generated method stub
		return null;
	}

}
