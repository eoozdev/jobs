package com.eooz.common.command;

import java.util.HashMap;
import java.util.Map;

public abstract class CmdFactoryService {
	
	// holds onto a list of factories. This is a static list, so use them wisely.
	// Don't blame me for memory leaks caused by your careless use of this design.
	private static Map<CommandType, CommandFactory> factories = new HashMap<CommandType, CommandFactory>();
	
	
	
	// no one creates me, but ME!
	private CmdFactoryService(){}
	
		
	 //looks up the factoryList and returns the factory object in it.
	public static CommandFactory getFactory(CommandType identifier) {
		
		return factories.get(identifier);
		
	}

	//The CommandFactory s use this method to register it self with the CmdFactoryService
	public static void register(CommandType identifier, CommandFactory postCommandFactory) {
		
		factories.put(identifier, postCommandFactory);
		
	}


	
}
