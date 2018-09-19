package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.MybatisDao;

@WebServlet("/session.do")
public class SessionController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath()+"/login.do");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		
		Map map = new HashMap();
			map.put("inputId", id);
			map.put("inputPw", pass);
		MybatisDao mdao = new MybatisDao();			
		if(mdao.loginCheck(map)) {
			HttpSession session = req.getSession();
			session.setAttribute("auth", true);
			session.setAttribute("userId", id);
			resp.sendRedirect(req.getContextPath()+ "/index.do");
		}else {
			req.setAttribute("loginError", true);  
			
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
			rd.forward(req, resp);
		}
	}
	
	
}
