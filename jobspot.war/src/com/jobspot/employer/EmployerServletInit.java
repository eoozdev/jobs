package com.jobspot.employer;

import com.eooz.common.command.GetCommandFactory;
import com.eooz.common.command.PostCommandFactory;
import com.jobspot.common.Command;

public class EmployerServletInit {

	public void init() {
		
		GetCommandFactory.register(
				Command.EMPLOYER_PROFILE.code(),
				new CmdGetEmployerProfile()
		);
		
		PostCommandFactory.register(Command.UPDATE_EMPLOYER_PROFILE.code(), new CmdUpdateEmployerProfile());
	}

}
