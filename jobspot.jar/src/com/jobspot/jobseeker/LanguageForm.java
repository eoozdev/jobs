package com.jobspot.jobseeker;

import java.util.Map;

import com.eooz.common.form.Form;
import com.jobspot.common.FIELD_NAME;
import com.jobspot.dto.Language;

public class LanguageForm implements Form{

	private Language language;
	private boolean validated;
	
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

	@Override
	public void validate() {
		validated = true;
	}

	@Override
	public boolean validated() {
		return validated;
	}

	public Language getLanguage() {
		return this.language;
	}

}
