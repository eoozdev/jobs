package com.jobspot.dto;

public class Language {

	private String code, name, proficiency, jobseeker;

	public String getJobseeker() {
		return jobseeker;
	}

	public void setJobseeker(String jobseeker) {
		this.jobseeker = jobseeker;
	}

	public String getProficiency() {
		return proficiency;
	}

	public void setProficiency(String proficiency) {
		this.proficiency = proficiency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
