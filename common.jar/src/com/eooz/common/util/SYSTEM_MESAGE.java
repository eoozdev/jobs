package com.eooz.common.util;

public enum SYSTEM_MESAGE {

	NOT_LOGGED_IN("NOT LOGGED IN"), 
	NOT_AUTHORIZED("NOT AUTHORIZED "), 
	NOT_KEYED_IN("NOT KEYED IN"), 
	NOT_NUMERIC("NOT NUMERIC"), 
	NOT_VALID_DATE("NOT VALID DATE"), 
	UNEXPECTED_ERROR("UNEXPECTED ERROR"), 
	UPLOAD_SUCCESSFUL("UPLOAD SUCCESSFUL"), 
	FILE_TOO_LARGE("FILE TOO LARGE"), 
	SUCCESSFUL("SUCCESSFUL");
	
	private String msg;

	SYSTEM_MESAGE(String msg){
		this.msg = msg;
	}
	
	public String value(){
		return this.msg;
	}

}
