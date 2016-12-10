package paran.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paran.attendance.AttendanceDBBean;
import paran.attendance.MonthlyDataBean;

public class MonthlyAdminViewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MonthlyAdminViewsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("month", request.getParameter("month"));
		List<MonthlyDataBean> resultList = new ArrayList<MonthlyDataBean>();
		
		AttendanceDBBean manager = new AttendanceDBBean();

		manager.monthAllList(request.getParameter("month"), resultList);
		request.setAttribute("resultList", resultList);	
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/monthlyAdminInfoPage.jsp");
		rd.forward(request, response);
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}


