package com.eooz.common.util;

public enum SYSTEM_MESAGE {

	NOT_LOGGED_IN("NOT LOGGED IN"), NOT_AUTHORIZED("NOT AUTHORIZED ");
	
	private String msg;

	SYSTEM_MESAGE(String msg){
		this.msg = msg;
	}
	
	public String value(){
		return this.msg;
	}

}
