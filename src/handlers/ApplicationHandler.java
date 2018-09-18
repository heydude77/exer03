package handlers;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ApplicationHandler implements ServletContextListener {
	long begin;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.print("contextInitialized..");
		begin = System.currentTimeMillis();
		/*
		 * 	�Ű������� �����ִ� ServletContextEvent �� ���ؼ� 
		 *  application �� ������ �� �ִ�. 
		 */
		ServletContext ctx = sce.getServletContext(); 
		// JSP���� ���Ǵ� application �̶�� �̸��� ��ü.
	
		ctx.setRequestCharacterEncoding("UTF-8");
		
	
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed..");
		long end = System.currentTimeMillis();
		System.out.println("running time.." +(end-begin) +" ms");
	}
}
