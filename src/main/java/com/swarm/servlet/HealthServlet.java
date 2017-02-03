package com.swarm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swarm.util.HelloUtil;
/**
 * 
 * @author bhawani.singh
 *
 */
@WebServlet("/healthz")
public class HealthServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {	
		System.out.println("\n\n@@@@@@@@   healthz service     @@@@@@@@\n\n");
		if(new HelloUtil().generateTimeout()){
			resp.setStatus(resp.SC_GATEWAY_TIMEOUT);				
		} else {
			resp.setStatus(resp.SC_OK);
		}
	}		
}
