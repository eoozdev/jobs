package com.jobspot.employer.form;

public enum FIELD_NAME {

	
	BASIC("basic"),
	EMPLOYER("employer"),
	DISPLAY_DURATION("duration"),
	JOB_CATEGORY("jobCategory"),
	ARTWORK("artwork"),
	TOWN("town"), 
	TITLE("title"),
	START_DATE("startDate"),
	END_DATE("endDate");
	
	private String value;
	
	
	FIELD_NAME(String value){
		this.value = value;
	}
	
	public String value(){
		return this.value;
	}
	
}
