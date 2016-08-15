package com.jobspot.search;

import com.jobspot.dto.Employer;
import com.jobspot.dto.JobCategory;
import com.jobspot.dto.Town;
import com.jobspot.dto.Vacancy;

public class SearchResult {


	private Town town;
	private Vacancy vacancy;
	private Employer employer;
	private JobCategory jobcategory;
	
	
	public Town getTown() {
		return town;
	}
	public void setTown(Town town) {
		this.town = town;
	}
	
	public Vacancy getVacancy() {
		return vacancy;
	}
	public void setVacancy(Vacancy vacancy) {
		this.vacancy = vacancy;
	}
	
	public Employer getEmployer() {
		return employer;
	}
	public void setEmployer(Employer employer) {
		this.employer = employer;
	}
	
	public JobCategory getJobcategory() {
		return jobcategory;
	}
	public void setJobcategory(JobCategory jobcategory) {
		this.jobcategory = jobcategory;
	}
}
