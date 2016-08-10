package com.eooz.common.command;

import com.eooz.common.util.RequestWrap;
import com.eooz.common.util.ResponseWrap;
import com.eooz.common.util.UploadSettings;

public interface IUploadCommand{

	public String doWork();

	public IUploadCommand create(RequestWrap request, ResponseWrap response, UploadSettings settings);
}
