package com.jobspot.dto;

public class WorkExperience {

	private String code,jobseeker, workedAt, period, position;

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getWorkedAt() {
		return workedAt;
	}

	public void setWorkedAt(String workedAt) {
		this.workedAt = workedAt;
	}

	public String getJobseeker() {
		return jobseeker;
	}

	public void setJobseeker(String jobseeker) {
		this.jobseeker = jobseeker;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	} 
}
