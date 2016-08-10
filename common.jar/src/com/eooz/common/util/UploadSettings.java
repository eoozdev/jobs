package com.eooz.common.util;

import java.util.Map;

public interface UploadSettings {

	
	public void update(Map<String, String> params);

	public String getFileUploadLocation();
	public void setFileUploadLocation(String location);
	
	public int uploadLimit();
	
	
	
}
