package com.eooz.common.command;

import java.io.File;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.page.IPage;
import com.eooz.common.page.Page;
import com.eooz.common.util.RequestWrap;
import com.eooz.common.util.ResponseWrap;
import com.eooz.common.util.SYSTEM_MESAGE;
import com.eooz.common.util.UploadSettings;

public class CmdUpload extends AbstractCommand implements IUploadCommand {

	private RequestWrap request;
	private UploadSettings settings;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public CmdUpload(RequestWrap request, UploadSettings settings) {
		this.request = request;
		this.settings = settings;
	}

	@Override
	public String doWork() {
		
		IPage page = new Page();
		
		try {
			
			Map<String, String> params = request.getParameterMap();
			FileItem item = request.getItem();
			settings.update(params);
			
			if(item.getSize() > settings.uploadLimit()){
				page.setMessage(SYSTEM_MESAGE.FILE_TOO_LARGE.value());
			}
			
			else{
				
				File f = new File(settings.getFileUploadLocation());
				if(!f.exists())
					f.mkdirs();
				
				String fileLocation = settings.getFileUploadLocation()+item.getName();
				File newFile = new File(fileLocation);
				item.write(newFile);
				
				page.setMessage(SYSTEM_MESAGE.UPLOAD_SUCCESSFUL.value());
				page.setHref(configPath(fileLocation));
				
			}
			
			
		} catch (Exception e) {
			logger.error("--> doWork(): "+e);
			page.setMessage(""+e);
		}
		
		return toJson(page);
	}


	private String configPath(String fileLocation) {
		return (fileLocation.substring(fileLocation.indexOf("uploads.war"))).replace("uploads.war", "uploads");
	}

	@Override
	public IUploadCommand create(RequestWrap request, ResponseWrap response, UploadSettings settings) {
		return new CmdUpload(request, settings);
	}


}
