package com.jobspot.jobseeker;

import com.eooz.common.form.Form;
import com.jobspot.dto.Jobseeker;

public class JobseekerProfile implements Form{

	Jobseeker jobseeker;
	boolean validated = false;
	
	public JobseekerProfile(Jobseeker js) {
		jobseeker = js;
	}

	@Override
	public void clear() {
		this.jobseeker = new Jobseeker();
	}

	@Override
	public void validate() {
		validated = true;
	}

	@Override
	public boolean validated() {
		// TODO Auto-generated method stub
		return validated;
	}



}
