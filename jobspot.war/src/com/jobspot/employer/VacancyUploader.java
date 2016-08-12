package com.jobspot.employer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eooz.common.command.CmdUpload;
import com.eooz.common.command.IUploadCommand;
import com.eooz.common.util.RequestWrap;
import com.eooz.common.util.UploadSettings;
import com.jobspot.util.VacancyUploadSettings;

/**
 * Servlet implementation class FileUploader
 */
@WebServlet("/do.vupload")
public class VacancyUploader extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VacancyUploader() {
        super();
        // TODO Auto-generated constructor stub
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
			
			UploadSettings settings = new VacancyUploadSettings();
			IUploadCommand command  = new CmdUpload(new RequestWrap(request), settings);
			String info = command.doWork();
			
			PrintWriter writer = response.getWriter();
			writer.write(info);
			writer.flush();
		}
		
		catch(Exception e){
			logger.error("--> doPost(): ERR"+e);
			
		}
		
		
	}

}
