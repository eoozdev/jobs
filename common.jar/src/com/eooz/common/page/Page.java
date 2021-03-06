package com.eooz.common.page;

import java.util.Collection;
import java.util.Map;

import com.eooz.common.form.Form;

public class Page implements IPage {

	private boolean allowDirectAccess = false;
	private String message;
	private String href;
	private Form form;
	
	private Map<String, Collection<Object>> collectionOne;
	private Map<String, Collection<Object>> collectionTwo;
	private Object instance;
	
	@Override
	public Form getForm() {
		return form;
	}

	@Override
	public void setForm(Form f) {
		this.form = f;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void setMessage(String err) {
		this.message = err;
	}

	@Override
	public String getHref() {
		return href;
	}

	@Override
	public void setHref(String href) {
		this.href = href;
	}

	@Override
	public Map<String, Collection<Object>> getCollectionOne() {
		return collectionOne;
	}

	@Override
	public void setCollectionOne(String key, Collection<Object> list, Map<String, Collection<Object>> map) {
		map.put(key, list);
		this.collectionOne = map;
	}

	@Override
	public Map<String, Collection<Object>> getCollectionTwo() {
		return collectionTwo;
	}

	@Override
	public void setCollectionTwo(String key, Collection<Object> list, Map<String, Collection<Object>> map) {
		map.put(key, list);
		this.collectionTwo = map;
	}

	@Override
	public void setHrefToLogin() {
		href = "/do.login";
	}

	@Override
	public void allowDirectaccess(boolean value) {
		allowDirectAccess = value;
	}

	@Override
	public void setInstance(Object v) {
		this. instance = v;
	}



	


}
