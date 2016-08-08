package com.jobspot.employer.form;

public enum FIELD_NAME {

	
	BASIC("basic"),
	EMPLOYER("employer"),
	DURATION("duration"),
	JOB_CATEGORY("jobCategory"),
	ARTWORK("artwork"),
	TOWN("town");
	
	private String value;
	
	
	FIELD_NAME(String value){
		this.value = value;
	}
	
	public String value(){
		return this.value;
	}
	
}
