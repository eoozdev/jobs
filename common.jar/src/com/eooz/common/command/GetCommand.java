// 20160730 SAM created GetCommand
package com.eooz.common.command;

import com.eooz.common.page.IPage;
import com.eooz.common.util.RequestWrap;

public interface GetCommand extends ICommand {
	
	public IPage getParameters(RequestWrap request);
	
	
}