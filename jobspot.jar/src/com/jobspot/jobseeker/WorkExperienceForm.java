package com.jobspot.jobseeker;

import java.util.Map;

import com.eooz.common.form.Form;
import com.jobspot.common.FIELD_NAME;
import com.jobspot.dto.WorkExperience;

public class WorkExperienceForm implements Form{

	private WorkExperience workExperience;
	private boolean validated;
	
	public WorkExperienceForm(Map<String, String> params) {
		workExperience = new WorkExperience();
		
		if(params.containsKey(FIELD_NAME.WORK_PERIOD.value()))
			workExperience.setPeriod(params.get(FIELD_NAME.WORK_PERIOD.value()));
		
		if(params.containsKey(FIELD_NAME.WORK_POSITION.value()))
			workExperience.setPosition(params.get(FIELD_NAME.WORK_POSITION.value()));
		
		if(params.containsKey(FIELD_NAME.WORK_WORKEDAT.value()))
			workExperience.setWorkedAt(params.get(FIELD_NAME.WORK_WORKEDAT.value()));
	}

	@Override
	public void clear() {
		this.workExperience = new WorkExperience();
	}

	@Override
	public void validate() {
		validated = true;
	}

	@Override
	public boolean validated() {
	return validated;
	}

	public WorkExperience getWorkExperience() {
		return this.workExperience;
	}

}
