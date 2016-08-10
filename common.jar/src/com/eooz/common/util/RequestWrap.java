package com.eooz.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestWrap {
	
	
	private FileItem item;
	private HttpServletRequest request;
	private Map<String, String> parameters;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public RequestWrap(HttpServletRequest request){
		this.request = request;
	}
	
	public String getParameter(String key){
		return this.request.getParameter(key);
	}

	private Iterator<FileItem> parameterSetup() throws FileUploadException {
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		

		@SuppressWarnings("unchecked")
		List<FileItem>items = upload.parseRequest(request);
		parameters = new HashMap<String, String>();
		return items.iterator();
		
	}


	public Map<String, String> getParameterMap() {
		
		try{
			
			Iterator<FileItem> iter = parameterSetup();
			
			while (iter.hasNext()) {
			    FileItem item = iter.next();

			    if (item.isFormField())
			        parameters.put(item.getFieldName(), item.getString());
			    else
			    	this.item = item;
			    
			}
			
		}
		
		catch(Exception e){
			logger.error("-->getParameterMap(): "+e);
		}
		
		return parameters;
		
	}


	public FileItem getFileitem() {
		try{
			
			Iterator<FileItem> iter = parameterSetup();
			
			while (iter.hasNext()) {
			    FileItem item = iter.next();

			    if (!item.isFormField())
			        return this.item = item;
			    
			}
			
		}
		
		catch(Exception e){
			logger.error("-->getFileitem(): "+e);
		}
		return this.item;
	}

	
	public FileItem getItem() {
		return item;
	}
}