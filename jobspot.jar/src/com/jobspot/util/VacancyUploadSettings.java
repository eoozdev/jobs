package com.jobspot.util;

import java.util.Map;

import com.eooz.common.util.UploadSettings;
import com.jobspot.common.FIELD_NAME;

public class VacancyUploadSettings implements UploadSettings {

	private String location = "D:/LEE/JOBSPOT/ctx/uploads.war/employer/";
	
	@Override
	public String getFileUploadLocation() {
		// TODO Auto-generated method stub
		return location;
	}

	@Override
	public void setFileUploadLocation(String location) {
		this.location = location;
	}

	@Override
	public void update(Map<String, String> params) {

		String employerCode = "DEF";
		
		if(params.containsKey(FIELD_NAME.EMPLOYER.value()))
			employerCode = params.get(FIELD_NAME.EMPLOYER.value());
		
		setFileUploadLocation(location+employerCode+"/");

	}

	@Override
	public int uploadLimit() {
		return 750000; //BYTES
	}

}
