package com.eooz.common.command;

import com.eooz.common.page.IPage;
import com.eooz.common.util.AddToSession;
import com.eooz.security.SecurityUtil;
import com.google.gson.Gson;

public abstract class AbstractCommand {

	
	public String toJson(IPage page){
		
		return new Gson().toJson(page);
	}

	public String getFromSession(String code, AddToSession sessionHandle) {
		String info = "";
		info = SecurityUtil.getFromSession(code);
		
		if(info == null){
			sessionHandle.add();
			info = SecurityUtil.getFromSession(code);
		}
		return info;
	}
	

}
