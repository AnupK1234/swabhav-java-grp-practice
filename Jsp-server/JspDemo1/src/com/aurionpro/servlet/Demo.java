package com.aurionpro.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/demo")
public class Demo extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Demo() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] fruits = {"Kiwi","Mango","Apple","Pineapple"};
		
		req.setAttribute("myFruits", fruits);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/Fruits.jsp");
		
		dispatcher.forward(req, resp);
		
	}
}
