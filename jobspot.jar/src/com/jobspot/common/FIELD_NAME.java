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
	ABOUT("about"),
	
	JS_FNAME("jsFname"),
	JS_LNAME("jsLname"),
	JS_DOB("jsDob"), 
	LANG_NAME("lngName"), 
	LANG_PROFICIENCY("lngProficiency"), 
	EDU_QUALIFICATION_CODE("eduQualificationCode"), 
	EDU_QUALIFICATION("eduQualification"), 
	EDU_PERIOD("eduPeriod"), 
	WORK_WORKEDAT("workWorkedAt"), 
	WORK_POSITION("workPosition"), 
	WORK_PERIOD("workPeriod");
	
	private String value;
	
	
	FIELD_NAME(String value){
		this.value = value;
	}
	
	public String value(){
		return this.value;
	}
	
}
