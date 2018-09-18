package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.MybatisDao;

@WebServlet("/signup_check.do")
public class SignUpCheckController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String inputId = req.getParameter("id");
		MybatisDao mdao = new MybatisDao();
		resp.setContentType("application/ajax;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(mdao.getId(inputId));
	}
	
}
