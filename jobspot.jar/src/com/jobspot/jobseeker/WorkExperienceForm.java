package com.jobspot.jobseeker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.eooz.common.form.Form;
import com.eooz.common.util.ValidationUtil;
import com.jobspot.common.FIELD_NAME;
import com.jobspot.dto.WorkExperience;


public class WorkExperienceForm implements Form{

	private WorkExperience workExperience;
	private boolean validated;
	private boolean validPeriod = false;
	private boolean validPosition = false;
	private boolean validWorkedAt = false;
	
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

	private void validPeriod(boolean status) {
		this.validPeriod = status;
			
		}
		
	public boolean validPeriod(){
		return validPeriod;
		}
	
	private void validPosition(boolean status) {
		this.validPosition = status;
			
		}
		
	public boolean validPosition(){
		return validPosition;
		}
	
	private void validWorkedAt(boolean status) {
		this.validWorkedAt = status;
			
		}
		
	public boolean validWorkedAt(){
		return validWorkedAt;
		}
	
	
	@Override
	public void validate() {
		validated = true;
		
		
		String dateRange = this.workExperience.getPeriod();
		String[] datesRange = dateRange.split("_");
		String datevalue1 = datesRange[0]; // yyyymmdd
		String datevalue2 = datesRange[1]; // yyyymmdd
		SimpleDateFormat  format = new SimpleDateFormat ("yyyymmdd");
		
		try {
			
			Date date1 = format.parse(datevalue1);
			Date date2 = format.parse(datevalue2);
			
			if (ValidationUtil.isValidDateRange(date1, date2) && (ValidationUtil.length(">", 1))) {
				validPeriod(true);

			} else {
				validated = false;
				validPeriod(false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		if ( ValidationUtil.exists(this.workExperience.getPosition())) {

			validPosition(true);

		} else {
			validated = false;
			validPosition(false);
		}
		
		if ( ValidationUtil.exists(this.workExperience.getWorkedAt())) {

			validWorkedAt(true);

		} else {
			validated = false;
			validWorkedAt(false);
		}
		
	}

	@Override
	public boolean validated() {
	return validated;
	}

	public WorkExperience getWorkExperience() {
		return this.workExperience;
	}

}
