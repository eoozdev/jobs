package com.jobspot.jobseeker;

import java.util.Map;

import com.eooz.common.form.Form;
import com.jobspot.common.FIELD_NAME;
import com.jobspot.dto.Jobseeker;

public class JobseekerProfile implements Form{

	Jobseeker jobseeker;
	boolean validated = false;
	
	public JobseekerProfile(Jobseeker js) {
		jobseeker = js;
	}

	public JobseekerProfile(Map<String, String> params) {

		if(params.containsKey(FIELD_NAME.JS_FNAME.value()))
			jobseeker.setFirstname(params.get(FIELD_NAME.JS_FNAME.value()));
		
		if(params.containsKey(FIELD_NAME.JS_LNAME.value()))
			jobseeker.setLastname(params.get(FIELD_NAME.JS_LNAME.value()));
		
		if(params.containsKey(FIELD_NAME.JS_DOB.value()))
			jobseeker.setDob(params.get(FIELD_NAME.JS_DOB.value()));

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


	public Jobseeker getJobseeker(){
		return this.jobseeker;
	}

}
