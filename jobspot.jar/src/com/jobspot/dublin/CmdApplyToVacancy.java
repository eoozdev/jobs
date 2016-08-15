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
import com.jobspot.dublin.jdbc.operations.ApplyToVacancy;
import com.jobspot.jobseeker.jdbc.operations.AddJobseekerToSession;

public class CmdApplyToVacancy extends AbstractCommand implements ICommand {

	private RequestWrap request; 
	private ResponseWrap response;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public CmdApplyToVacancy(RequestWrap request, ResponseWrap response) {
		this.request = request;
		this.response = response;
	}

	public CmdApplyToVacancy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doWork() {
		
		IPage page = new Page();
		
		try{
			
			String jobseeker = super.getFromSession("JOBSEEKER", new AddJobseekerToSession());
			String code = request.getParameter("code");
			
			new ApplyToVacancy(jobseeker, code).run();
			page.setMessage(SYSTEM_MESAGE.SUCCESSFUL.value());
			
		}
		
		catch(Exception e){
			logger.error("doWork(): general exception"+e);
			page.setMessage(SYSTEM_MESAGE.UNEXPECTED_ERROR.value());
		}
		
		
		return super.toJson(page);
	}

	@Override
	public ICommand create(RequestWrap request, ResponseWrap response) {
		return new CmdApplyToVacancy(request, response);
	}

}
