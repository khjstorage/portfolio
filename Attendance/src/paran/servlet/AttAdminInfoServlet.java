package paran.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paran.attendance.AttendanceDBBean;
import paran.attendance.AttendanceDataBean;


public class AttAdminInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AttAdminInfoServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("userId", request.getParameter("userId")); // admin 아이디 검색했을때 input 박스의 name속성이 userId 인거 가져오는거
		List<AttendanceDataBean> resultList = new ArrayList<AttendanceDataBean>();
		
	    AttendanceDBBean manager = new AttendanceDBBean();
	    manager.lookupAtt(request.getParameter("userId"), resultList);
		request.setAttribute("resultList", resultList);
		if(resultList.size()==0){
			popupMsg(request, response);
		}else{
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/attAdminInfoForm.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void popupMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script language='javascript'>");
		out.println("alert('출석정보가 없습니다.');");
		out.println("location.href = 'http://localhost:8080/Attendance/page_manage.jsp';");
		out.println("</script>");
	
	}
}
