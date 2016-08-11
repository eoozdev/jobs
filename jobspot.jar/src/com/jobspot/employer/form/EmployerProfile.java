package com.jobspot.employer.form;

import com.eooz.common.form.Form;
import com.jobspot.dto.Employer;

public class EmployerProfile implements Form {

	Employer employer;
	
	public EmployerProfile(Employer e) {
		this.employer = e;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validated() {
		// TODO Auto-generated method stub
		return false;
	}

}
