package com.eooz.common.util;

public enum SYSTEM_MESAGE {

	NOT_LOGGED_IN("NOT LOGGED IN"), 
	NOT_AUTHORIZED("NOT AUTHORIZED "), 
	NOT_KEYED_IN("NOT KEYED IN"), 
	NOT_NUMERIC("NOT NUMERIC"), 
	NOT_VALID_DATE("NOT VALID DATE");
	
	private String msg;

	SYSTEM_MESAGE(String msg){
		this.msg = msg;
	}
	
	public String value(){
		return this.msg;
	}

}
