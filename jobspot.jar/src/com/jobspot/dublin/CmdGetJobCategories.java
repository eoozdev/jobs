package com.jobspot.dublin;

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
import com.google.gson.Gson;
import com.jobspot.employer.jdbc.operations.GetJobCategories;

public class CmdGetJobCategories extends AbstractCommand implements GetCommand{

	private RequestWrap request;
	private ResponseWrap response;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public CmdGetJobCategories(RequestWrap request, ResponseWrap response){
		this.request = request;
		this.response = response;
	}
	
	public CmdGetJobCategories() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doWork() {
		
		Gson gson = new Gson();
		Collection<Object> list = new GetJobCategories(Mask.ACTIVE.value()).getCollection();
		
		
		return gson.toJson(list);
	}

	@Override
	public ICommand create(RequestWrap request, ResponseWrap response) {
		return new CmdGetJobCategories(request, response);
	}

}
