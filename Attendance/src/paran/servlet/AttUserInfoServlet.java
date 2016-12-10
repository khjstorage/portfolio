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
import paran.attendance.AttendanceDataBean;


//@WebServlet("/RetrieveAttInfoServlet")
public class AttUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AttUserInfoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String)request.getSession().getAttribute("id"); //유저 자기꺼 로그인한거
		List<AttendanceDataBean> resultList = new ArrayList<AttendanceDataBean>();

	    AttendanceDBBean manager = new AttendanceDBBean();
		manager.lookupAtt(id, resultList);
		request.setAttribute("resultList", resultList);
	
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/attUserInfoForm.jsp");
		rd.forward(request, response);			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
