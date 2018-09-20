package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.BoardDao;

@WebServlet("/comment.do")
public class OpinionController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		String select = req.getParameter("select");
		String comment = req.getParameter("comment");
		Number choice = Integer.parseInt(select);
		String talker = (String) session.getAttribute("userId");
		String ino = req.getParameter("ino");
		Number inoToNumber = Integer.parseInt(ino);				
		
		/*
		 * opinion_seq.nextval, #{choice}, #{ment} , #{talker}, #{ino}
		 * */
		BoardDao bdao = new BoardDao();
		Map opinion = new HashMap();
			opinion.put("choice", choice);
			opinion.put("ment", comment);
			opinion.put("talker", talker);
			opinion.put("ino", inoToNumber);
		bdao.addOpinion(opinion);
		
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(true);
	}
		
}
