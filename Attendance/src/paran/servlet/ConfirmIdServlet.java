package paran.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paran.join.JoinDBBean;

public class ConfirmIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConfirmIdServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		JoinDBBean manager = new JoinDBBean();
		int check = manager.confirmId(id);
		out.println(check);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
