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

import models.BoardDao;

@WebServlet("/new.do")
public class NewTrendController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/new.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		String cate = req.getParameter("cate");
		String content = req.getParameter("content");
		String agree = req.getParameter("agree");
		String disagree = req.getParameter("disagree");
		String writer = (String) session.getAttribute("userId"); 
		
		Map map = new HashMap();
			map.put("cate", cate);
			map.put("content", content);
			map.put("agree", agree);
			map.put("disagree", disagree);
			map.put("writer", writer);
		
		BoardDao bdao = new BoardDao();
			int r = bdao.addIssue(map);
		
		if (r==1) {
			req.setAttribute("success", true);
		}  
		resp.sendRedirect(req.getContextPath()+"/trend.do");
	}
}
