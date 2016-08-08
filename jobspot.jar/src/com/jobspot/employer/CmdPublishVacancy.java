package com.jobspot.employer;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.command.AbstractCommand;
import com.eooz.common.command.ICommand;
import com.eooz.common.command.PostCommand;
import com.eooz.common.page.IPage;
import com.eooz.common.page.Page;
import com.eooz.common.util.RequestWrap;
import com.eooz.common.util.ResponseWrap;
import com.eooz.common.util.SYSTEM_MESAGE;
import com.eooz.security.SecurityUtil;
import com.jobspot.employer.form.PublishVacancyForm;
import com.jobspot.operation.AutherizedVacancy;

public class CmdPublishVacancy extends AbstractCommand implements PostCommand {

	
	private RequestWrap request;
	private ResponseWrap response;
	private Logger logger = LoggerFactory.getLogger(CmdPublishVacancy.class);
	
	


	//default constructor
	public CmdPublishVacancy(){}

	


	public CmdPublishVacancy(RequestWrap request, ResponseWrap response) {
		this.request = request;
		this.response = response;
	}


	@Override
	//the core of the logic takes place in this magical method
	public String doWork() {
		
		IPage page = new Page();
		AutherizedVacancy authorizedVacancy = new AutherizedVacancy();
		
		try{
						
			if(SecurityUtil.isNotAuthenticated()){
				page.setHrefToLogin();
				page.setError(SYSTEM_MESAGE.NOT_LOGGED_IN.value());
			}

			if(authorizedVacancy.no())
				page.setError(SYSTEM_MESAGE.NOT_AUTHORIZED.value());
			

			else{
	
				
				Map<String, String> params = request.getParameterMap();
				PublishVacancyForm form = new PublishVacancyForm(params);
				
				form.validate();
				
				if(form.validated()){
					
					createVacancy(form.title(), 
							form.jobcategory(), 
							form.town(), 
							form.basic(), 
							form.duration(), 
							form.artwork());
					
					
				}
				

				page.setForm(form);
				
				}
	
			}
		
		catch(Exception e){
			logger.error("doWork(): general exception"+e);
		}	
		
		
		return toJson(page);
	}





	private void createVacancy(Object title, Object jobcategory, Object town, Object basic, Object duration,
			Object artwork) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public ICommand create(RequestWrap request, ResponseWrap response) {
		return new CmdPublishVacancy(request, response);
	}

}
