package com.jobspot.common;

import com.eooz.common.command.GetCommandFactory;
import com.eooz.common.command.PostCommandFactory;
import com.eooz.common.command.PutCommandFactory;
import com.jobspot.dublin.CmdApplyToVacancy;
import com.jobspot.dublin.CmdGetJobCategories;
import com.jobspot.dublin.CmdGetTowns;
import com.jobspot.dublin.CmdSearchForVacancy;
import com.jobspot.dublin.CmdShowSearchEngine;
import com.jobspot.dublin.CmdViewVacancy;
import com.jobspot.employer.CmdGetEmployerProfile;
import com.jobspot.employer.CmdGetVacancyApplications;
import com.jobspot.employer.CmdPublishVacancy;
import com.jobspot.employer.CmdShowVacanciesPg;
import com.jobspot.employer.CmdUpdateEmployerProfile;
import com.jobspot.employer.CmdUpdateVacancy;
import com.jobspot.employer.CmdViewApplication;
import com.jobspot.employer.CmdViewPublishVacancy;
import com.jobspot.employer.CmdViewUpdateVacancy;
import com.jobspot.jobseeker.CmdAddEducation;
import com.jobspot.jobseeker.CmdAddLanguage;
import com.jobspot.jobseeker.CmdAddWorkExperience;
import com.jobspot.jobseeker.CmdGetJobseekerProfile;
import com.jobspot.jobseeker.CmdUpdateEducation;
import com.jobspot.jobseeker.CmdUpdateJobseeker;
import com.jobspot.jobseeker.CmdUpdateLanguage;
import com.jobspot.jobseeker.CmdUpdateWorkExperience;

public class CommandInitializer {

	public void init() {

		// register employer commands
		GetCommandFactory.register(Command.EMPLOYER_PROFILE.code(), new CmdGetEmployerProfile());

		PostCommandFactory.register(Command.UPDATE_EMPLOYER_PROFILE.code(), new CmdUpdateEmployerProfile());
	

		// register vacancy commands
		GetCommandFactory.register(Command.EMPLOYER_VACANCY_PG.code(), new CmdShowVacanciesPg());

		GetCommandFactory.register(Command.PUBLISH_VACANCY_VIEW.code(), new CmdViewPublishVacancy());

		PostCommandFactory.register(Command.PUBLISH_VACANCY.code(), new CmdPublishVacancy());

		GetCommandFactory.register(Command.UPDATE_VACANCY_VIEW.code(), new CmdViewUpdateVacancy());

		PutCommandFactory.register(Command.UPDATE_VACANCY.code(), new CmdUpdateVacancy());
		
		GetCommandFactory.register(Command.GET_VACANCY_APPLICATIONS.code(), new CmdGetVacancyApplications());
		
		GetCommandFactory.register(Command.VIEW_APPLICATION.code(), new CmdViewApplication());
		

		// register jobseeker commands

		GetCommandFactory.register(Command.VIEW_MY_PROFILE.code(), new CmdGetJobseekerProfile());

		PutCommandFactory.register(Command.UPDATE_JOBSEEKER.code(), new CmdUpdateJobseeker());

		PutCommandFactory.register(Command.UPDATE_JS_WORKEXPERIENCE.code(), new CmdUpdateWorkExperience());

		PutCommandFactory.register(Command.UPDATE_JS_LANGUAGE.code(), new CmdUpdateLanguage());

		PutCommandFactory.register(Command.UPDATE_JS_EDUCATION.code(), new CmdUpdateEducation());

		PostCommandFactory.register(Command.CREATE_JS_EDUCATION.code(), new CmdAddEducation());

		PostCommandFactory.register(Command.CREATE_JS_LANGUAGE.code(), new CmdAddLanguage());

		PostCommandFactory.register(Command.CREATE_JS_WORKEXPERIENCE.code(), new CmdAddWorkExperience());

		// register public commands
		GetCommandFactory.register(Command.SEARCH_FOR_VACANCY.code(), new CmdSearchForVacancy());

		GetCommandFactory.register(Command.VIEW_VACANCY.code(), new CmdViewVacancy());

		GetCommandFactory.register(Command.APPLY_TO_VACANCY.code(), new CmdApplyToVacancy());

		GetCommandFactory.register(Command.GET_TOWNS.code(), new CmdGetTowns());
		
		GetCommandFactory.register(Command.GET_JOBCATEGORIES.code(), new CmdGetJobCategories());

	}

}
