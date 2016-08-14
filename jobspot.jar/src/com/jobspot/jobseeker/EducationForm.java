package com.jobspot.jobseeker;

import java.util.Map;

import com.eooz.common.form.Form;
import com.jobspot.common.FIELD_NAME;
import com.jobspot.dto.Education;

public class EducationForm implements Form {

	private Education education;
	private boolean validated;
	
	public EducationForm(Map<String, String> params) {
		
		education = new Education();
		
		if(params.containsKey(FIELD_NAME.EDU_PERIOD.value()))
			education.setPeriod(params.get(FIELD_NAME.EDU_PERIOD.value()));
		
		if(params.containsKey(FIELD_NAME.EDU_QUALIFICATION.value()))
			education.setQualification(params.get(FIELD_NAME.EDU_QUALIFICATION.value()));
		
		if(params.containsKey(FIELD_NAME.EDU_QUALIFICATION_CODE.value()))
			education.setQualificationCode(params.get(FIELD_NAME.EDU_QUALIFICATION_CODE.value()));
	}

	@Override
	public void clear() {
		this.education = new Education();
	}

	@Override
	public void validate() {
		validated = true;
	}

	@Override
	public boolean validated() {
		return validated;
	}

	public Education getEducation() {
		return this.education;
	}

}
