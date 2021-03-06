package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.BoardDao;

@WebServlet("/trend.do")
public class TrendController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		BoardDao bdao = new BoardDao();
		List<Map> issueList = bdao.getAllIssue();
		List<Map> opinion = bdao.getAllOpinion();
	
		req.setAttribute("opinion", opinion);
		req.setAttribute("issueList",issueList);
		//req.setAttribute("opinionTotalByIssueNo",totalOpinion);
		
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/trend.jsp");
		rd.forward(req, resp);
	}
		
}
	