package com.eooz.common.command;

public enum CommandType {

	
	GET("GET"),
	POST("POST"),
	PUT("PUT"),
	DELETE("DELETE"), 
	UPLOAD("UPLOAD");
	
	private String operation;
	
	CommandType(String o){
		this.operation = o;
	}
	
	public String value(){
		return this.operation;
	}
	
	
}
