package com.jobspot.dublin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.command.AbstractCommand;
import com.eooz.common.command.ICommand;
import com.eooz.common.page.IPage;
import com.eooz.common.page.Page;
import com.eooz.common.util.RequestWrap;
import com.eooz.common.util.ResponseWrap;
import com.eooz.common.util.SYSTEM_MESAGE;

public class CmdViewVacancy extends AbstractCommand implements ICommand {

	private RequestWrap request;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public CmdViewVacancy(RequestWrap request, ResponseWrap response){
		this.request = request;
	}
	
	public CmdViewVacancy() {}

	@Override
	public String doWork() {
		
		IPage page = new Page();
		
		try{
			
			
			String vacancyCode = request.getParameter("code");
			
			VacancyView v = new GetVacancy().find(vacancyCode);
			page.setInstance(v);
			
			
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
		// TODO Auto-generated method stub
		return new CmdShowSearchEngine(request, response);
	}

}
