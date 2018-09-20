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
		// ino ���� parameter �Ѱ������� �� ino�� ��ϵ� opinion�� �̾Ƽ�
				// JSON���� ���亸���� �� ��Ʈ�ѷ��� get�� ���� 
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
