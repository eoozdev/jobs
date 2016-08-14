// 20160730 SAM Command enum: stored reusable command
package com.jobspot.common;

import com.eooz.common.command.CommandType;

public enum Command {
	
	
	
	SEARCH_FOR_VACANCY("EZPS",CommandType.GET),
	VIEW_ABOUT_US("EZVAU", CommandType.GET),
	PUBLISH_VACANCY_VIEW("EZPVV", CommandType.GET),
	PUBLISH_VACANCY("EZPV", CommandType.POST),
	UPDATE_VACANCY("EZUV", CommandType.PUT),
	REGISTER_USER("EZRU", CommandType.POST), 
	UPDATE_VACANCY_VIEW("EZUVV", CommandType.GET), 
	EMPLOYER_VACANCY_PG("EZEVP", CommandType.GET),
	EMPLOYER_PROFILE("EZEP",CommandType.GET),
	UPDATE_EMPLOYER_PROFILE("EZUEP", CommandType.POST),
	
	VIEW_MY_PROFILE("EZVMP", CommandType.GET),
	UPDATE_JOBSEEKER("EZUJP",CommandType.PUT), 
	UPDATE_JS_WORKEXPERIENCE("EZUWE",CommandType.PUT), 
	UPDATE_JS_LANGUAGE("EZUL",CommandType.PUT), 
	UPDATE_JS_EDUCATION("EZUE",CommandType.PUT),
	CREATE_JS_LANGUAGE("EZCL",CommandType.POST),
	CREATE_JS_EDUCATION("EZCE",CommandType.POST),
	CREATE_JS_WORKEXPERIENCE("EZCW",CommandType.POST);
	
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