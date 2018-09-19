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
		/*
		Map<Number, Integer> totalOpinion = new HashMap();
		for (int i=0; i<issueList.size(); i++) {
			Number number = (Number)issueList.get(i).get("NO");
			List<Map> li = bdao.getAllOpinionByNo(String.valueOf(number));
			int opinionTotal = li.size();
			totalOpinion.put(number, opinionTotal);
		}
		 */
		
		req.setAttribute("issueList",issueList);
		//req.setAttribute("opinionTotalByIssueNo",totalOpinion);
		
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/trend.jsp");
		rd.forward(req, resp);
	}
		
}
	