package com.swarm.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {	
		System.out.println("In HelloServlet doGet()");
		resp.getWriter().write("Hello World !!!");
	}
	
	public static void main(String[] args) {
		System.out.println("HelloServlet.main()");
	}
}