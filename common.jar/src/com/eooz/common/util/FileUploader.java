package com.eooz.common.util;

import org.apache.commons.fileupload.FileItem;

public class FileUploader {

	private FileItem fileToBeUploaded;
	private String uploadLocation;
	
	public FileUploader(FileItem fileToBeUploaded, String uploadLocation) {
		this.fileToBeUploaded = fileToBeUploaded;
		this.uploadLocation = uploadLocation;
	}

	public String upload() {
		return null;
	}

}
