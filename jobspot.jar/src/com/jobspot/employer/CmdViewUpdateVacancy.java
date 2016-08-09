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
import com.jobspot.employer.form.PublishVacancyForm;
import com.jobspot.master.Vacancy;
import com.jobspot.operation.GetJobCategories;
import com.jobspot.operation.GetTowns;
import com.jobspot.operation.GetVacancy;

public class CmdViewUpdateVacancy extends AbstractCommand implements GetCommand {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	private RequestWrap request;
	
	public CmdViewUpdateVacancy(RequestWrap request, ResponseWrap response) {
		this.request = request;
	}

	public CmdViewUpdateVacancy() { }

	@Override
	public String doWork() {
		
		IPage page = new Page();
		
		try{
						
			if(SecurityUtil.isNotAuthenticated()){
				page.setHrefToLogin();
				page.setError(SYSTEM_MESAGE.NOT_LOGGED_IN.value());
			}
			
			else{
	
				Collection<Object> categories = new ArrayList<Object>();
				categories = new GetJobCategories(Mask.ACTIVE.value()).getCollection();
			
				Collection<Object> towns = new ArrayList<Object>();
				towns = new GetTowns(Mask.ACTIVE.value()).getCollection();
				
				page.setCollectionOne("job_categories",categories, new HashMap<String, Collection<Object>>());
				page.setCollectionTwo("towns",towns, new HashMap<String, Collection<Object>>());
				page.setHref("/member/employer/vacancies.jsp");
					
				String code = request.getParameter("code");
				Vacancy vacancy = new GetVacancy().find(code);
				PublishVacancyForm form = new PublishVacancyForm(vacancy);
				page.setForm(form);
				}
	
			}
		
		catch(Exception e){
			logger.error("doWork(): general exception"+e);
		}	
		
		
		return toJson(page);
	}

	@Override
	public ICommand create(RequestWrap request, ResponseWrap response) {
		return new CmdViewUpdateVacancy(request, response);
	}

	@Override
	public IPage getParameters(RequestWrap request) {
		// TODO Auto-generated method stub
		return null;
	}

}
