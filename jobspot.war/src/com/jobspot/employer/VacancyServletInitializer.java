package com.jobspot.employer;

import com.eooz.common.command.GetCommandFactory;
import com.eooz.common.command.PostCommandFactory;
import com.jobspot.common.Command;

public class VacancyServletInitializer {


	/**
	 * registers factories and commands for this servlet.
	 * **/
	public void init(){
		

		GetCommandFactory.register(
				Command.PUBLISH_VACANCY_VIEW.code(), 
				new CmdViewPublishVacancy()
		);
		
		
		PostCommandFactory.register(
				Command.PUBLISH_VACANCY.code(), 
				new CmdPublishVacancy());
		
		
		
		
	}
}
