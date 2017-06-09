package paran.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paran.attendance.AttendanceDBBean;


public class ExitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ExitServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String)request.getSession().getAttribute("id");
		AttendanceDBBean manager = new AttendanceDBBean();
		manager.exitCheck(id);
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/page_user.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
