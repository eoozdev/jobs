package com.eooz.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class RequestWrap {
	
	
	private FileItem item;
	private HttpServletRequest request;
	private Map<String, String> parameters;
	
	
	public RequestWrap(HttpServletRequest request){
		this.request = request;
	}
	
	public String getParameter(String key){
		return this.request.getParameter(key);
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> getParameterMap() {
		
		try{
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);


			List<FileItem>items = upload.parseRequest(request);
			parameters = new HashMap<String, String>();
			Iterator<FileItem> iter = items.iterator();
			
			while (iter.hasNext()) {
			    FileItem item = iter.next();

			    if (item.isFormField()) {
			        parameters.put(item.getFieldName(), item.getString());
			    } else {
			        this.item = item;
			    }
			}
			
		}
		
		catch(Exception e){
			
		}
		
		return parameters;
		
	}

	public FileItem getItem() {
		return item;
	}
	
}