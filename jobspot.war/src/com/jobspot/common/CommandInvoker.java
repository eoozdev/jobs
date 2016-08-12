package com.jobspot.common;

import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.command.CmdFactoryService;
import com.eooz.common.util.RequestWrap;
import com.eooz.common.util.ResponseWrap;

public class CommandInvoker {

	private RequestWrap request;
	private ResponseWrap response;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public CommandInvoker(RequestWrap request, ResponseWrap response){
		this.request = request;
		this.response = response;
	}
	
	public void invoke(){
		
	try{
			
			//this block figures out what the user wants
			String cc = request.getParameter("cc");
			Command command = Command.fromString(cc);
			
			//get an instance of a command type factory.
			String json = CmdFactoryService.getFactory(command.type())
					//off the command type factory get a command
					.getCommand(command.code(), request,response)
						//execute that command by calling 'doWork'. 
						//it returns a JSON complaint string
						.doWork();
			
			//this block writes the JSON response back to the client
			PrintWriter writer = response.getWriter();
			writer.write(json);
			writer.flush();
			
			
		}
		
		catch(Exception e){
			logger.error("--> invoke(): error invoking command"+e);
		}
		
	}
}
