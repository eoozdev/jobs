package com.jobspot.dublin;

import java.util.ArrayList;
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
import com.jobspot.dublin.jdbc.operations.GetEmployerList;

public class CmdGetEmployerList extends AbstractCommand implements GetCommand {

	private ResponseWrap response;
	private RequestWrap request;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public CmdGetEmployerList(RequestWrap request, ResponseWrap response) {
		this.request = request;
		this.response = response;
	}

	@Override
	public String doWork() {
		
		IPage page = new Page();

		try{
			
			ArrayList<Object> list = new GetEmployerList().getCollection(Mask.ACTIVE.value());
			page.setCollectionOne("EMPLOYER_LIST", list, new HashMap<>());
		
		}
		
		catch(Exception e){
			logger.error("--> doWork(): General exception"+e);
			page.setMessage(SYSTEM_MESAGE.UNEXPECTED_ERROR.value());
		}
		
		return toJson(page);
	}

	@Override
	public ICommand create(RequestWrap request, ResponseWrap response) {
		return new CmdGetEmployerList(request, response);
	}

}
