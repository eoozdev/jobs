package com.jobspot.employer;

import java.util.Collection;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.command.AbstractCommand;
import com.eooz.common.command.ICommand;
import com.eooz.common.page.IPage;
import com.eooz.common.page.Page;
import com.eooz.common.util.RequestWrap;
import com.eooz.common.util.ResponseWrap;
import com.eooz.common.util.SYSTEM_MESAGE;
import com.jobspot.employer.jdbc.operations.GetVacancyApplications;

public class CmdGetVacancyApplications extends AbstractCommand implements ICommand {

	private RequestWrap request;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public CmdGetVacancyApplications(RequestWrap request, ResponseWrap response){
		this.request = request;
	}
	
	public CmdGetVacancyApplications() {}
	
	@Override
	public String doWork() {
		
		IPage page = new Page();
		
		try{
			
			
			String vacancyCode = request.getParameter("code");
			Collection<Object> list = new GetVacancyApplications(vacancyCode).getCollection();
			page.setCollectionOne("applications", list, new HashMap<>());
			
		}
		
		catch(NullPointerException npe){
			logger.error("--> doWork(): NPE: vacancy code is null: ");
			page.setMessage(SYSTEM_MESAGE.NOT_KEYED_IN.value());
		}
		catch(Exception e){
			logger.error("doWork(): general exception"+e);
			page.setMessage(SYSTEM_MESAGE.UNEXPECTED_ERROR.value());
		}
		
		return super.toJson(page);
	}

	@Override
	public ICommand create(RequestWrap request, ResponseWrap response) {
		return new CmdGetVacancyApplications(request, response);
	}

}
