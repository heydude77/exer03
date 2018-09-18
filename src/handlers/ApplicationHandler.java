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
		 * 	매개변수로 잡혀있는 ServletContextEvent 를 통해서 
		 *  application 을 접근할 수 있다. 
		 */
		ServletContext ctx = sce.getServletContext(); 
		// JSP에서 사용되는 application 이라는 이름의 객체.
	
		ctx.setRequestCharacterEncoding("UTF-8");
		
	
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed..");
		long end = System.currentTimeMillis();
		System.out.println("running time.." +(end-begin) +" ms");
	}
}
