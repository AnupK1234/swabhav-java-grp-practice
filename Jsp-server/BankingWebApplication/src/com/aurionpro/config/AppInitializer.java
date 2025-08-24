package com.aurionpro.config;

import com.aurionpro.Dao.TransactionDao;
import com.aurionpro.Dao.UserDao;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        ctx.setAttribute("userDao", new UserDao());
        ctx.setAttribute("transactionDao", new TransactionDao());
    }
}
