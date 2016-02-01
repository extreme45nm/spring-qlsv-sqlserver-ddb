package edu.java.spring.controller;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import edu.java.spring.dao.ChuyenKhoaDAO;
import edu.java.spring.dao.GiangVienDAO;
import edu.java.spring.dao.KhoaDAO;
import edu.java.spring.dao.LopHocDAO;
import edu.java.spring.dao.MonHocDAO;
import edu.java.spring.dao.SinhVienDAO;
import edu.java.spring.dao.SinhVienMonHocDAO;

public class CustomContextLoaderListener extends ContextLoaderListener{
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("\n\n Spring-MVC application inited\n\n");
		super.contextInitialized(event);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("\n\n Spring-MVC application destroyed\n\n");
		getCurrentWebApplicationContext().getBean(SinhVienDAO.class).shutdown();
		getCurrentWebApplicationContext().getBean(MonHocDAO.class).shutdown();
		getCurrentWebApplicationContext().getBean(SinhVienMonHocDAO.class).shutdown();
		getCurrentWebApplicationContext().getBean(LopHocDAO.class).shutdown();
		getCurrentWebApplicationContext().getBean(GiangVienDAO.class).shutdown();
		getCurrentWebApplicationContext().getBean(KhoaDAO.class).shutdown();
		getCurrentWebApplicationContext().getBean(ChuyenKhoaDAO.class).shutdown();
		super.contextDestroyed(event);
	}
}
