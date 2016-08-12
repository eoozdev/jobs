package com.jobspot.jobseeker;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.util.RequestWrap;
import com.eooz.common.util.ResponseWrap;
import com.jobspot.common.CommandInvoker;

/**
 * Servlet implementation class Jobseeker
 */
@WebServlet("/do.jobseeker")
public class Jobseeker extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
      /**
     * @see HttpServlet#HttpServlet()
     */
    public Jobseeker() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException{
    	super.init(config);
    	new JobseekerServletInit().init();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try{
			
			new CommandInvoker(new RequestWrap(request), new ResponseWrap(response))
				.invoke();
			
		}
		
		catch(Exception e){
			logger.error("--> doPost(): servlet level exception: "+e);
		}
	}

}
