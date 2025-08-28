package com.aurionpro.config;

import com.aurionpro.Dao.TransactionDao;
import com.aurionpro.Dao.UserDao;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@WebListener
public class AppInitializer implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			// 1. Lookup the JNDI DataSource defined in context.xml
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/bankDB");

			// 2. Pass the DataSource to your DAOs
			UserDao userDao = new UserDao(ds);
			TransactionDao transactionDao = new TransactionDao(ds);

			// 3. Store DAOs in ServletContext for global access
			ServletContext servletContext = sce.getServletContext();
			servletContext.setAttribute("userDao", userDao);
			servletContext.setAttribute("transactionDao", transactionDao);

		} catch (NamingException e) {
			throw new RuntimeException("Failed to initialize DataSource", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// cleanup if needed
	}
}
