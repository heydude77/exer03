package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import models.BoardDao;

@WebServlet("/refresh.do")
public class RefreshController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ino 값을 parameter 넘겨줬을때 그 ino로 등록된 opinion들 뽑아서
				// JSON으로 응답보내게 이 컨트롤러의 get을 구현 
				Number ino = Integer.parseInt(req.getParameter("ino"));
				BoardDao bdao = new BoardDao();
				List<Map> li = bdao.getOpinionByINO(ino);
				Gson gson = new Gson();
				String json = gson.toJson(li);
				resp.setContentType("application/json;charset=utf-8");
				PrintWriter out = resp.getWriter();
				out.println(json);
	}
}
