package com.eooz.common.command;

import com.eooz.common.page.IPage;
import com.google.gson.Gson;

public abstract class AbstractCommand {

	
	public String toJson(IPage page){
		
		return new Gson().toJson(page);
	}
	

}
