package com.jobspot.dto;

import java.util.Collection;

public class Jobseeker {
	
	private String code;
	private String firstname;
	private String lastname;
	private String dob;
	private String user;
	private String status;
	private Collection<WorkExperience> workExperience;
	private Collection<Education> education;
	private Collection<Language> language;
	
	public Jobseeker(){}
	
	public Jobseeker(String jobseekerCode) {
		this.code = jobseekerCode;
	}
	
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Collection<WorkExperience> getWorkExperience() {
		return workExperience;
	}
	public void setWorkExperience(Collection<WorkExperience> workExperience) {
		this.workExperience = workExperience;
	}
	public Collection<Education> getEducation() {
		return education;
	}
	public void setEducation(Collection<Education> education) {
		this.education = education;
	}
	public Collection<Language> getLanguage() {
		return language;
	}
	public void setLanguage(Collection<Language> language) {
		this.language = language;
	}


}
