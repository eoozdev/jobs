// 20160730 SAM created RequestObject
package com.eooz.common.page;

import java.util.Collection;
import java.util.Map;

import com.eooz.common.form.Form;

public interface IPage {
	
	public void allowDirectaccess(boolean value);
	
	public Form getForm();
	public void setForm(Form f);

	public String getMessage();
	public void setMessage(String err);
	
	public String getHref();
	public void setHref(String url);
	

	public Map<String, Collection<Object>> getCollectionOne();
	public void setCollectionOne(String string, Collection<Object> list, Map<String, Collection<Object>> map);
	
	public Map<String, Collection<Object>> getCollectionTwo();
	public void setCollectionTwo(String string, Collection<Object> list,  Map<String, Collection<Object>> map);
	
	public void setHrefToLogin();

	public void setInstance(Object v);
	


	
}