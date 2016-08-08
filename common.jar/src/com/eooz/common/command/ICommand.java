package com.eooz.common.command;

import com.eooz.common.util.RequestWrap;
import com.eooz.common.util.ResponseWrap;

public interface ICommand {

	public String doWork();

	public ICommand create(RequestWrap request, ResponseWrap response);
	
}
