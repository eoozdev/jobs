package com.jobspot.jobseeker;

import com.eooz.common.command.GetCommandFactory;
import com.eooz.common.command.PostCommandFactory;
import com.eooz.common.command.PutCommandFactory;
import com.jobspot.common.Command;

public class JobseekerServletInit {

	public void init(){
		
		GetCommandFactory.register(
				Command.VIEW_MY_PROFILE.code(), 
				new CmdGetJobseekerProfile()
		);

		PutCommandFactory.register(
				Command.UPDATE_JOBSEEKER.code(), 
				new CmdUpdateJobseeker()
		);
		
		PutCommandFactory.register(
				Command.UPDATE_JS_WORKEXPERIENCE.code(), 
				new CmdUpdateWorkExperience()
		);
		
		
		PutCommandFactory.register(
				Command.UPDATE_JS_LANGUAGE.code(), 
				new CmdUpdateLanguage()
		);
		
		
		PutCommandFactory.register(
				Command.UPDATE_JS_EDUCATION.code(), 
				new CmdUpdateEducation()
		);
		
		PostCommandFactory.register(
				Command.CREATE_JS_EDUCATION.code(), 
				new CmdAddEducation()
		);
		
		PostCommandFactory.register(
				Command.CREATE_JS_LANGUAGE.code(), 
				new CmdAddLanguage()
		);
		
		PostCommandFactory.register(
				Command.CREATE_JS_WORKEXPERIENCE.code(), 
				new CmdAddWorkExperience()
		);
		
		
	}
}
