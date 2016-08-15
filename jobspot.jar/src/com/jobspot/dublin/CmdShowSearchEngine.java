package com.jobspot.dublin;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.command.AbstractCommand;
import com.eooz.common.command.ICommand;
import com.eooz.common.mask.Mask;
import com.eooz.common.page.IPage;
import com.eooz.common.page.Page;
import com.eooz.common.util.RequestWrap;
import com.eooz.common.util.ResponseWrap;
import com.eooz.common.util.SYSTEM_MESAGE;
import com.jobspot.employer.jdbc.operations.GetJobCategories;
import com.jobspot.employer.jdbc.operations.GetTowns;

public class CmdShowSearchEngine extends AbstractCommand implements ICommand {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public CmdShowSearchEngine(RequestWrap request, ResponseWrap response) {}

	public CmdShowSearchEngine() {}

	@Override
	public String doWork() {
		
		IPage page = new Page();
		
		try{
			
			page.setCollectionOne("towns", 
					new GetTowns(Mask.ACTIVE.value()).getCollection(), 
					new HashMap<>());
			
			page.setCollectionTwo("jobCategories", 
					new GetJobCategories(Mask.ACTIVE.value()).getCollection(), 
					new HashMap<>());
			

		}
		catch(Exception e){
			logger.error("doWork(): general exception"+e);
			page.setMessage(SYSTEM_MESAGE.UNEXPECTED_ERROR.value());
		}
		
		return super.toJson(page);
	}

	@Override
	public ICommand create(RequestWrap request, ResponseWrap response) {
		return new CmdShowSearchEngine(request, response);
	}

}
