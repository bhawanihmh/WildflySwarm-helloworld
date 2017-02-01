package com.swarm.servlet;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/healthz")
public class HealthServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {	
		System.out.println("\n\n@@@@@@@@   health service     @@@@@@@@\n\n");

		InetAddress ip;
		try {

			ip = InetAddress.getLocalHost();
			System.out.println("Current IP address : " + ip.getHostAddress());
			boolean isEven = false;
			Random rand = new Random();
			isEven = (rand.nextInt(100) % 2) == 0 ? true : false ;
			System.out.println(" isEven = " + isEven);
			
			if(isEven) {
				try {
					System.out.println("\n\n@@@@@@@@@@@@   Before Sleep currentTimeMillis = " + System.currentTimeMillis());
					Thread.sleep(5000);					
					System.out.println("\n\n@@@@@@@@@@@@   After Sleep currentTimeMillis = " + System.currentTimeMillis());
					System.out.println("\n\n@@@@@@@@@@@@   Set status gateway timeout     @@@@@@@@@@@@\n");
					resp.setStatus(resp.SC_GATEWAY_TIMEOUT);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			} else {
				resp.setStatus(resp.SC_OK);				
			}
			

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}		
	}	
}