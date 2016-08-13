package com.jobspot.jobseeker;

import com.eooz.common.command.GetCommandFactory;
import com.jobspot.common.Command;

public class JobseekerServletInit {

	public void init(){
		
		GetCommandFactory.register(
				Command.VIEW_MY_PROFILE.code(), 
				new CmdGetJobseekerProfile()
		);
		
	}
}
