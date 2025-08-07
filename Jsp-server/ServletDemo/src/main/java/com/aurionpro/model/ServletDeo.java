package com.aurionpro.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/rohan")
public class ServletDeo extends HttpServlet {
	 Date date = new Date() ;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
	pw.println("Current date is "+date.toString());
//		pw.println("Current date is "+date.getDate()+" This is month "+date.getMonth()+" This is year "+date.getYear());
	}
    @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		PrintWriter pw = arg1.getWriter();
		pw.println("Hello");
    }
}
