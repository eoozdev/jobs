package com.jobspot.common;

public enum FIELD_NAME {

	VACANCY("vacancy"),
	BASIC("basic"),
	EMPLOYER("employer"),
	DISPLAY_DURATION("duration"),
	JOB_CATEGORY("jobCategory"),
	ARTWORK("artwork"),
	TOWN("town"), 
	TITLE("title"),
	START_DATE("startDate"),
	END_DATE("endDate"),
	
	EMPLOYER_NAME("employerName"),
	COMPANY_TYPE("companyType"),
	COMPANY_SIZE("companySize"),
	ABOUT("about");
	
	private String value;
	
	
	FIELD_NAME(String value){
		this.value = value;
	}
	
	public String value(){
		return this.value;
	}
	
}
