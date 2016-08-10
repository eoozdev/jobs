package com.eooz.common.command;

import java.util.HashMap;
import java.util.Map;

import com.eooz.common.util.RequestWrap;
import com.eooz.common.util.ResponseWrap;

public class PutCommandFactory implements CommandFactory{

	//stores post commands. These commands are not 'new'd up unlike the factories.
	//so don't muck it up. i.e keep an eye on thread safety.
	private static Map<String, ICommand> commandList = new HashMap<String, ICommand>();
	
	// one time registration
	static{
		CmdFactoryService.register(CommandType.PUT, new PutCommandFactory());
	}
	


	@Override
	public ICommand getCommand(String identifier, RequestWrap request, ResponseWrap response) {
			
		return commandList.get(identifier).create(request, response).create(request, response);
		
	}
	
	
	//The concrete commands use this method to register themselves with the PostCommandFactorys
	public static void register(String identifier, ICommand command){
		commandList.put(identifier, command);
	}
}
