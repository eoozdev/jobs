package com.jobspot.employer;

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
import com.jobspot.dto.Employer;
import com.jobspot.employer.form.EmployerProfile;
import com.jobspot.jdbc.operations.AddEmployerToSession;
import com.jobspot.jdbc.operations.GetEmployerProfile;
import com.jobspot.jdbc.operations.GetJobCategories;
import com.jobspot.jdbc.operations.GetTowns;
import com.jobspot.jdbc.operations.IsMyProfile;

public class CmdGetEmployerProfile extends AbstractCommand implements GetCommand {

	private RequestWrap request;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public CmdGetEmployerProfile(RequestWrap request, ResponseWrap response) {
		this.request = request;
	}

	public CmdGetEmployerProfile() {
	}

	@Override
	public String doWork() {
		
		IPage page = new Page();
		IsMyProfile isMyProfile = new IsMyProfile();
		
		try{
			
			if(SecurityUtil.isNotAuthenticated()){
				page.setHrefToLogin();
				page.setMessage(SYSTEM_MESAGE.NOT_LOGGED_IN.value());
				return toJson(page);
			}
			
			String employerCode = super.getFromSession("EMPLOYER_CODE", new AddEmployerToSession());
			
			if(isMyProfile.no(employerCode)){
				page.setMessage(SYSTEM_MESAGE.NOT_AUTHORIZED.value());
				return toJson(page);
			}
			
			
			GetTowns gt = new GetTowns(Mask.ACTIVE.value());
			page.setCollectionOne("towns", gt.getCollection(), new HashMap<>());
			
			GetJobCategories gjc = new GetJobCategories(Mask.ACTIVE.value());
			page.setCollectionOne("jobcategories", gjc.getCollection(), new HashMap<>());
				
			
			Employer e = new Employer();
			GetEmployerProfile gep = new GetEmployerProfile(e);
			e = (Employer) gep.getInstance(employerCode);
			EmployerProfile profile = new EmployerProfile(e);
			
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
		return new CmdGetEmployerProfile(request, response);
	}

	@Override
	public IPage getParameters(RequestWrap request) {
		// TODO Auto-generated method stub
		return null;
	}

}
