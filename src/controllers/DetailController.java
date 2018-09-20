package controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.BoardDao;

@WebServlet("/detail.do")
public class DetailController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = "";
			if(req.getParameter("no") !=null) {
				no = req.getParameter("no");
			}else {
				no = (String)req.getAttribute("ino");
			}
		
		Number num = Integer.parseInt(no);
		BoardDao bdao = new BoardDao();
			
		Map issue = bdao.getIssue(num);
		List opinion = bdao.getOpinionByINO(num);
		
		req.setAttribute("issue", issue);
		req.setAttribute("opinion", opinion);
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/detail.jsp");
		rd.forward(req, resp);
	}
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
