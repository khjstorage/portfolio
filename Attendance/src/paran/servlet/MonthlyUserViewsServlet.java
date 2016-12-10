package paran.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paran.attendance.AttendanceDBBean;

public class MonthlyUserViewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MonthlyUserViewsServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getSession().getAttribute("id");
		request.setAttribute("month", request.getParameter("month"));
		
		AttendanceDBBean manager = new AttendanceDBBean();
		
//		System.out.println(1111);
		
		String[] arr = manager.monthMylist(id, request.getParameter("month"));
		request.setAttribute("name", arr[0]);
		request.setAttribute("att_cnt", arr[1]);
		request.setAttribute("early_cnt", arr[2]);
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/monthlyUserInfoPage.jsp");
		rd.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
