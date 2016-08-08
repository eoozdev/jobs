// 20160730 SAM Command enum: stored reusable command
package com.jobspot.common;

import com.eooz.common.command.CommandType;

public enum Command {
	
	
	
	SEARCH_FOR_VACANCY("EZPS",CommandType.GET),
	VIEW_ABOUT_US("EZVAU", CommandType.GET),
	PUBLISH_VACANCY_VIEW("EZPVV", CommandType.GET),
	PUBLISH_VACANCY("EZPV", CommandType.POST),
	UPDATE_VACANCY("EZUV", CommandType.PUT),
	REGISTER_USER("EZRU", CommandType.POST);
	
	
	private String value;
	private CommandType operation;
	
	private Command(String value, CommandType factoryCode){
		this.value = value;
		this.operation = factoryCode;
	}
	
	public String code(){
		return this.value;
	}
	
	public CommandType type(){
		return this.operation;
	}


	public static Command fromString(String cc) {

		for(Command c : Command.values()){
			
			if(c.code().equalsIgnoreCase(cc))
				return c;
			
		}

		return null;
	}
	
}