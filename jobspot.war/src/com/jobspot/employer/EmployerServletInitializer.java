package com.jobspot.employer;

import com.eooz.common.command.GetCommandFactory;
import com.jobspot.common.Command;

public class EmployerServletInitializer {

	public void init() {
		
		GetCommandFactory.register(
				Command.EMPLOYER_PROFILE.code(),
				new CmdGetEmployerProfile()
		);
	}

}
