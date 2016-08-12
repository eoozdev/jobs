
package com.eooz.common.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseWrap {
	
	
	private HttpServletResponse response;
	
	
	public ResponseWrap(HttpServletResponse response){
		this.response = response;
	}


	public PrintWriter getWriter() throws IOException {		
		return this.response.getWriter();	
	}
	

	
}