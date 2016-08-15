package com.jobspot.employer;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.command.AbstractCommand;
import com.eooz.common.command.ICommand;
import com.eooz.common.page.IPage;
import com.eooz.common.page.Page;
import com.eooz.common.util.RequestWrap;
import com.eooz.common.util.ResponseWrap;
import com.eooz.common.util.SYSTEM_MESAGE;
import com.eooz.security.SecurityUtil;
import com.jobspot.dto.Education;
import com.jobspot.dto.Jobseeker;
import com.jobspot.dto.Language;
import com.jobspot.dto.WorkExperience;
import com.jobspot.jobseeker.CmdGetJobseekerProfile;
import com.jobspot.jobseeker.JobseekerProfile;
import com.jobspot.jobseeker.jdbc.operations.AddJobseekerToSession;
import com.jobspot.jobseeker.jdbc.operations.GetEducation;
import com.jobspot.jobseeker.jdbc.operations.GetJobseeker;
import com.jobspot.jobseeker.jdbc.operations.GetLanguages;
import com.jobspot.jobseeker.jdbc.operations.GetWorkExperience;
import com.jobspot.jobseeker.jdbc.operations.IsMyProfile;

public class CmdViewApplication extends AbstractCommand implements ICommand {

	private ResponseWrap response;
	private RequestWrap request;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public CmdViewApplication(RequestWrap request, ResponseWrap response) {
		this.request = request;
		this.response = response;
	}
	
	public CmdViewApplication() {
	}

	@Override
	public String doWork() {
		
		IPage page = new Page();
		
		try{
			
			if(SecurityUtil.isNotAuthenticated()){
				page.setHrefToLogin();
				page.setMessage(SYSTEM_MESAGE.NOT_LOGGED_IN.value());
				return toJson(page);
			}
				
			Jobseeker js = new Jobseeker(request.getParameter("code"));
			
			js = new GetJobseeker(js).find();
			ArrayList<Language> ll = new GetLanguages(js.getCode()).getCollection();
			ArrayList<Education> el = new GetEducation(js.getCode()).getCollection();
			ArrayList<WorkExperience> wl = new GetWorkExperience(js.getCode()).getCollection();
		
			js.setLanguage(ll);
			js.setEducation(el);
			js.setWorkExperience(wl);
			
			JobseekerProfile profile = new JobseekerProfile(js);
			page.setForm(profile);
			
			
			}


		catch(Exception e){
			logger.error("doWork(): general exception"+e);
			page.setMessage(SYSTEM_MESAGE.UNEXPECTED_ERROR.value());
		}
		return toJson(page);
	}

	@Override
	public ICommand create(RequestWrap request, ResponseWrap response) {
		return new CmdViewApplication(request, response);
	}



}
