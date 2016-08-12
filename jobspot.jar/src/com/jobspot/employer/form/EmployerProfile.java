package com.jobspot.employer.form;

import java.util.Map;

import com.eooz.common.form.Form;
import com.jobspot.dto.Employer;

public class EmployerProfile implements Form {

	private Employer employer;
	private boolean validated;
	
	public EmployerProfile(Employer e) {
		this.employer = e;
	}

	public EmployerProfile(Map<String, String> params) {
		this.employer = new Employer();
		if(params.containsKey(FIELD_NAME.EMPLOYER_NAME.value()))
			this.employer.setName(params.get(FIELD_NAME.EMPLOYER_NAME.value()));
		
		if(params.containsKey(FIELD_NAME.TOWN.value()))
			this.employer.setTown(params.get(FIELD_NAME.TOWN.value()));
		
		if(params.containsKey(FIELD_NAME.JOB_CATEGORY.value()))
			this.employer.setIndustry(params.get(FIELD_NAME.JOB_CATEGORY.value()));
		
		if(params.containsKey(FIELD_NAME.COMPANY_SIZE.value()))
			this.employer.setCompanySize(params.get(FIELD_NAME.COMPANY_SIZE.value()));
		
		if(params.containsKey(FIELD_NAME.ABOUT.value()))
			this.employer.setAbout(params.get(FIELD_NAME.ABOUT.value()));
		
		if(params.containsKey(FIELD_NAME.ARTWORK.value()))
			this.employer.setLogo(params.get(FIELD_NAME.ARTWORK.value()));
		
		if(params.containsKey(FIELD_NAME.COMPANY_TYPE.value()))
			this.employer.setType(params.get(FIELD_NAME.COMPANY_TYPE.value()));
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validate() {
		this.validated = true;
		
	}

	@Override
	public boolean validated() {
		// TODO Auto-generated method stub
		return this.validated;
	}
	
	public Employer getEmployer(){
		return this.employer;
	}

}
