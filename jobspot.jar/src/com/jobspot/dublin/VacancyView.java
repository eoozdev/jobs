package com.jobspot.dublin;

import com.jobspot.dto.Employer;
import com.jobspot.dto.JobCategory;
import com.jobspot.dto.Town;
import com.jobspot.dto.Vacancy;

public class VacancyView {

	private Vacancy vacancy;
	private Town town;
	private Employer employer;
	private JobCategory jobcategory;

	public VacancyView(Vacancy v, Town t, Employer e, JobCategory jc){
		this.vacancy = v;
		this.town = t;
		this.employer = e;
		this.jobcategory = jc;
	}

	public VacancyView() {
		// TODO Auto-generated constructor stub
	}
}
