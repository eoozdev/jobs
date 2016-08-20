package com.jobspot.jobseeker;

import java.util.Map;

import com.eooz.common.form.Form;
import com.eooz.common.util.ValidationUtil;
import com.jobspot.common.FIELD_NAME;
import com.jobspot.dto.Jobseeker;

public class JobseekerProfile implements Form{

	Jobseeker jobseeker;
	boolean validated = false;
	boolean validFirstName =false;
	boolean validLastName=false;
	boolean validDOB=false;
	
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

	private void validFirstName(boolean status) {
		this.validFirstName = status;
			
		}
		
	public boolean validFirstName(){
		return validFirstName;
		}
	
	private void validLastName(boolean status) {
		this.validLastName = status;
			
		}
		
	public boolean validLastName(){
		return validLastName;
		}
	
	private void validDOB(boolean status) {
		this.validDOB = status;
			
		}
		
	public boolean validDOB(){
		return validDOB;
		}
	
	@Override
	public void validate() {
		validated = true;
		
		
		if ( ValidationUtil.exists(this.jobseeker.getFirstname()) &&(ValidationUtil.length(">", 3))) {

			validFirstName(true);

		} else {
			validated = false;
			validFirstName(false);
		}
		if ( ValidationUtil.exists(this.jobseeker.getLastname()) &&(ValidationUtil.length(">", 3))) {

			validLastName(true);

		} else {
			validated = false;
			validLastName(false);
		}
		if ( ValidationUtil.exists(this.jobseeker.getDob()) && (ValidationUtil.isDate(this.jobseeker.getDob()))) {

			validDOB(true);

		} else {
			validated = false;
			validDOB(false);
		}
		
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
