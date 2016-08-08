package com.eooz.common.command;

import com.eooz.common.util.RequestWrap;
import com.eooz.common.util.ResponseWrap;

public interface CommandFactory {



	public ICommand getCommand(String commandCode, RequestWrap request, ResponseWrap response);
	
}
