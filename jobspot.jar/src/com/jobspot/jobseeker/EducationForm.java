package com.jobspot.jobseeker;

import java.util.Map;

import com.eooz.common.form.Form;
import com.jobspot.common.FIELD_NAME;
import com.jobspot.dto.Education;
import com.eooz.common.util.ValidationUtil;
import java.text.SimpleDateFormat;
import java.util.Date;




public class EducationForm implements Form {
	private boolean validPeriod = false;
	private boolean validQualification = false;
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

	private void validPeriod(boolean status) {
		this.validPeriod = status;
			
		}
		
	public boolean validPeriod(){
		return validPeriod;
		}
		
	private void validQualification(boolean status) {
		this.validQualification = status;
			
		}
		
	public boolean validQualification(){
		return validQualification;
		}

	
	
	@Override
	public void validate(){
		validated = true;
		
		String dateRange = this.education.getPeriod();
		String[] datesRange = dateRange.split("_");
		String datevalue1 = datesRange[0]; // yyyymmdd
		String datevalue2 = datesRange[1]; // yyyymmdd
		SimpleDateFormat  format = new SimpleDateFormat ("yyyymmdd");
		
		try {
			
			Date date1 = format.parse(datevalue1);
			Date date2 = format.parse(datevalue2);
			
			if (ValidationUtil.isValidDateRange(date1, date2) && (ValidationUtil.exists(this.education.getPeriod()))) {
				validPeriod(true);

			} else {
				validated = false;
				validPeriod(false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
		if(ValidationUtil.exists(this.education.getQualification()) && ValidationUtil.length(">", 6)){
			validQualification(true);
			
		}
		
		else{
			validated = false;
			validQualification(false);
		}	
			
		
	}

	@Override
	public boolean validated() {
		return validated;
	}

	public Education getEducation() {
		return this.education;
	}

}
