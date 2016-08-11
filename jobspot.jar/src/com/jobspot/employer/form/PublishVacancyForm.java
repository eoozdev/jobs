package com.jobspot.employer.form;

import java.util.HashMap;
import java.util.Map;

import com.eooz.common.form.Form;
import com.eooz.common.util.SYSTEM_MESAGE;
import com.eooz.common.util.ValidationUtil;
import com.jobspot.dto.Vacancy;

public class PublishVacancyForm implements Form {

	
	private boolean validArtwork = true;
	private boolean validTitle = true;
	private boolean validJobcategory = true;
	private boolean validTown = true;
	private boolean validBasic = true;
	private boolean validDuration = true;
	private boolean validated = true;
	
	private Map<String, String> errors;
	private String startdate;
	private String enddate;
	private String employer;
	private boolean validEmployer = true;
	private boolean validEndDate = true;
	private boolean validStartDate = true;
	private Vacancy vacancy;


	public PublishVacancyForm(Map<String, String> params, Vacancy v) {
		
		vacancy = v;
		
		if(params.containsKey(FIELD_NAME.ARTWORK.value()))
			vacancy.setArtwork(params.get(FIELD_NAME.ARTWORK.value()));
		
		if(params.containsKey(FIELD_NAME.TITLE.value()))
			vacancy.setTitle(params.get(FIELD_NAME.TITLE.value()));
				
		if(params.containsKey(FIELD_NAME.JOB_CATEGORY.value()))
		vacancy.setJobcategory(params.get(FIELD_NAME.JOB_CATEGORY.value()));
		
		if(params.containsKey(FIELD_NAME.BASIC.value()))
		vacancy.setBasic(params.get(FIELD_NAME.BASIC.value()));
		
		if(params.containsKey(FIELD_NAME.TOWN.value()))
		vacancy.setTown(params.get(FIELD_NAME.TOWN.value()));
		
		if(params.containsKey(FIELD_NAME.START_DATE.value()))
		vacancy.setDisplayfrom(params.get(FIELD_NAME.START_DATE.value()));
		
		if(params.containsKey(FIELD_NAME.END_DATE.value()))
		vacancy.setDisplayto(params.get(FIELD_NAME.END_DATE.value()));
		
		if(params.containsKey(FIELD_NAME.EMPLOYER.value()))
		vacancy.setEmployer( params.get(FIELD_NAME.EMPLOYER.value()));
		
		if(params.containsKey(FIELD_NAME.VACANCY.value()))
			vacancy.setCode(params.get(FIELD_NAME.VACANCY.value()));
		
	}

	public PublishVacancyForm(Vacancy vacancy) {
		this.vacancy = vacancy;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validate() {
	
		
		errors = new HashMap<String, String>();
		
		if(!ValidationUtil.exists(vacancy.getArtwork())){
			validArtwork = false;
			validated = false;
			errors.put(FIELD_NAME.ARTWORK.value(), SYSTEM_MESAGE.NOT_KEYED_IN.value());
		}		
		
		if(!ValidationUtil.exists(vacancy.getTitle())){
			validTitle = false;
			validated = false;
			errors.put(FIELD_NAME.TITLE.value(), SYSTEM_MESAGE.NOT_KEYED_IN.value());
		}
		
		if(!ValidationUtil.exists(vacancy.getJobcategory())){
			validJobcategory = false;
			validated = false;
			errors.put(FIELD_NAME.JOB_CATEGORY.value(), SYSTEM_MESAGE.NOT_KEYED_IN.value());
		}
		
		if(!ValidationUtil.exists(vacancy.getTown())){
			validTown = false;
			validated = false;
			errors.put(FIELD_NAME.TOWN.value(), SYSTEM_MESAGE.NOT_KEYED_IN.value());
		}
		
		if(!ValidationUtil.exists(vacancy.getBasic())){
			validBasic = false;
			validated = false;
			errors.put(FIELD_NAME.BASIC.value(), SYSTEM_MESAGE.NOT_KEYED_IN.value());
		}
		
		if(!ValidationUtil.isNumeric(vacancy.getBasic())){
			validBasic = false;
			validated = false;
			errors.put(FIELD_NAME.BASIC.value(), SYSTEM_MESAGE.NOT_NUMERIC.value());
			
		}
		
		if(!ValidationUtil.exists(vacancy.getEmployer())){
			validEmployer = false;
			validated = false;
			errors.put(FIELD_NAME.EMPLOYER.value(), SYSTEM_MESAGE.NOT_KEYED_IN.value());
		}

		if(!ValidationUtil.exists(vacancy.getDisplayfrom())){
			validStartDate = false;
			validated = false;
			errors.put(FIELD_NAME.START_DATE.value(), SYSTEM_MESAGE.NOT_KEYED_IN.value());
		}

		if(!ValidationUtil.exists(vacancy.getDisplayto())){
			validEndDate = false;
			validated = false;
			errors.put(FIELD_NAME.END_DATE.value(), SYSTEM_MESAGE.NOT_KEYED_IN.value());
		}
		
		if(!ValidationUtil.isDate(vacancy.getDisplayfrom())){
			validStartDate = false;
			validated = false;
			errors.put(FIELD_NAME.START_DATE.value(), SYSTEM_MESAGE.NOT_VALID_DATE.value());
		}
		
		if(!ValidationUtil.isDate(vacancy.getDisplayto())){
			validEndDate = false;
			validated = false;
			errors.put(FIELD_NAME.END_DATE.value(), SYSTEM_MESAGE.NOT_VALID_DATE.value());
		}
	}

	@Override
	public boolean validated() {
		// TODO Auto-generated method stub
		return validated;
	}

	public String title() {
		// TODO Auto-generated method stub
		return vacancy.getTitle();
	}

	public String jobcategory() {
		// TODO Auto-generated method stub
		return vacancy.getJobcategory();
	}

	public String town() {
		// TODO Auto-generated method stub
		return vacancy.getTown();
	}

	public String basic() {
		// TODO Auto-generated method stub
		return vacancy.getBasic();
	}



	public String artwork() {
		// TODO Auto-generated method stub
		return vacancy.getArtwork();
	}

	public String employer() {
		return vacancy.getEmployer();
	}

	public String enddate() {
		// TODO Auto-generated method stub
		return vacancy.getDisplayto();
	}

	public String startdate() {
		// TODO Auto-generated method stub
		return vacancy.getDisplayfrom();
	}

	public void employer(String employer) {
		vacancy.setEmployer(employer);;
	}

	public String vacancyCode() {
		return vacancy.getCode();
	}

}
