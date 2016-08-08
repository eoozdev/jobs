package com.jobspot.employer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.command.CmdFactoryService;
import com.eooz.common.util.RequestWrap;
import com.eooz.common.util.ResponseWrap;
import com.jobspot.common.Command;

@WebServlet("/do.vacancy")
public class Vacancy extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(Vacancy.class);
	
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		
		//A few objects need to be initialized in order
		//for the SERVLET to operate
		new VacancyServletInitializer().init();
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		try{
			
			//this block figures out what the user wants
			String cc = request.getParameter("cc");
			Command command = Command.fromString(cc);
			
			//get an instance of a command type factory.
			String json = CmdFactoryService.getFactory(command.type())
					//off the command type factory get a command
					.getCommand(command.code(), new RequestWrap(request), new ResponseWrap(response))
						//execute that command by calling 'doWork'. 
						//it returns a JSON complaint string
						.doWork();
			
			//this block writes the JSON response back to the client
			PrintWriter writer = response.getWriter();
			writer.write(json);
			writer.flush();
			
			
		}
		
		catch(Exception e){
			logger.error("--> doPost(): root level exception");
		}
	}
	
	/*
	 @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}
	
}
