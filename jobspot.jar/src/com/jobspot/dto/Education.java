package com.jobspot.dto;

public class Education {

	private String code, jobseeker, period, qualification, qualificationCode;

	public String getQualificationCode() {
		return qualificationCode;
	}

	public void setQualificationCode(String qualificationCode) {
		this.qualificationCode = qualificationCode;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
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
}
