package paran.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import paran.join.JoinDBBean;

public class LoginProcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginProcServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String forwardUrl = "";

		JoinDBBean manager = new JoinDBBean();
		int check = manager.userCheck(id, passwd);
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		String sessionID = (String)session.getAttribute("id");
		if (check == 1) {
			if ("admin".equals(id)) {
				forwardUrl = "/page_manage.jsp";
			} else {
				forwardUrl = "/page_user.jsp";
			}
			// request.getSession().setAttribute("id",id);
		} else if (check == 0) {
			request.setAttribute("id", sessionID);
			request.setAttribute("loginSuccess", "pwd_false");
			forwardUrl = "/loginForm.jsp";
		} else {
			request.setAttribute("loginSuccess", "id_false");
			forwardUrl = "/loginForm.jsp";
		}

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(forwardUrl);
		rd.forward(request, response);

		
		/*
		 * include는 하나의 페이지에서 두번째 페이지로 경로가 이동할 때 이동한 페이지의 실행이 끝나면 다시 원래의 페이지로
		 * 돌아온다. forward는 두번째 페이지가 실행이 끝나고 넘어오지 않는다. include는 여러 jsp에서 공통으로
		 * 사용하고자 할때 사용 include 메소드는 현재 페이지에서 다른 페이지를 호출한다고 생각하면되고 forward 메소드는
		 * 아예 페이지가 이동한다.
		 */
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
