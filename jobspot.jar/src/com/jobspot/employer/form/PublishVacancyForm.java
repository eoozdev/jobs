package com.jobspot.employer.form;

import java.util.Map;

import com.eooz.common.form.Form;
import com.eooz.common.util.ValidationUtil;

public class PublishVacancyForm implements Form {

	private String artwork;
	private String title;
	private String jobcategory;
	private String town;
	private String basic;
	private String duration;
	
	private boolean validArtwork;
	private boolean validTitle;
	private boolean validJobcategory;
	private boolean validTown;
	private boolean validBasic;
	private boolean validDuration;
	private boolean validated;


	public PublishVacancyForm(Map<String, String> params) {
		this.artwork = params.get(FIELD_NAME.ARTWORK.value());
		this.title = params.get(FIELD_NAME.ARTWORK.value());
		this.jobcategory = params.get(FIELD_NAME.JOB_CATEGORY.value());
		this.basic = params.get(FIELD_NAME.BASIC.value());
		this.town = params.get(FIELD_NAME.TOWN.value());
		this.duration = params.get(FIELD_NAME.DURATION.value());
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validate() {
		
		validated = true;
		validArtwork = true;
		validTitle = true;
		validJobcategory = true;
		validTown = true;
		validBasic = true;
		validDuration = true;
		
		if(!ValidationUtil.exists(this.artwork)){
			validArtwork = false;
			validated = false;
		}
		
		if(!ValidationUtil.exists(this.duration)){
			validDuration = false;
			validated = false;
		}
		
		if(!ValidationUtil.exists(this.title)){
			validTitle = false;
			validated = false;
		}
		
		if(!ValidationUtil.exists(this.jobcategory)){
			validJobcategory = false;
			validated = false;
		}
		
		if(!ValidationUtil.exists(this.town)){
			validTown = false;
			validated = false;
		}
		
		if(!ValidationUtil.exists(this.basic)){
			validBasic = false;
			validated = false;
		}
		
		
	}

	@Override
	public boolean validated() {
		// TODO Auto-generated method stub
		return validated;
	}

	public String title() {
		// TODO Auto-generated method stub
		return title;
	}

	public String jobcategory() {
		// TODO Auto-generated method stub
		return jobcategory;
	}

	public String town() {
		// TODO Auto-generated method stub
		return town;
	}

	public String basic() {
		// TODO Auto-generated method stub
		return basic;
	}

	public String duration() {
		// TODO Auto-generated method stub
		return duration;
	}

	public String artwork() {
		// TODO Auto-generated method stub
		return artwork;
	}

}
