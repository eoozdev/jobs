package com.jobspot.jobseeker;

import java.util.Map;

import com.eooz.common.form.Form;
import com.eooz.common.util.ValidationUtil;
import com.jobspot.common.FIELD_NAME;
import com.jobspot.dto.Language;

public class LanguageForm implements Form{

	private Language language;
	private boolean validated;
	private boolean validName = false;
	private boolean validProficiency = false;
	
	public LanguageForm(Map<String, String> params) {
		
		language = new Language();
		
		if(params.containsKey(FIELD_NAME.LANG_NAME.value()))
			language.setName(params.get(FIELD_NAME.LANG_NAME.value()));
		
		if(params.containsKey(FIELD_NAME.LANG_PROFICIENCY.value()))
			language.setProficiency(params.get(FIELD_NAME.LANG_PROFICIENCY.value()));
		
	}

	@Override
	public void clear() {
		this.language = new Language();
	}

	private void validName(boolean status) {
		this.validName = status;
			
		}
		
	public boolean validName(){
		return validName;
		}
		
	private void validProficiency(boolean status) {
		this.validProficiency = status;
			
		}
		
	public boolean validProficiency(){
		return validProficiency;
		}
	
	
	@Override
	public void validate() {
		validated = true;
		
		
		if ( ValidationUtil.exists(this.language.getName())) {

			validName(true);

		} else {
			validated = false;
			validName(false);
		}
		
		if ( ValidationUtil.exists(this.language.getProficiency())) {

			validProficiency(true);

		} else {
			validated = false;
			validProficiency(false);
		}
		
	}

	@Override
	public boolean validated() {
		return validated;
	}

	public Language getLanguage() {
		return this.language;
	}

}
