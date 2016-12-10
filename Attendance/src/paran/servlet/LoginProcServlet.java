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
		 * include�� �ϳ��� ���������� �ι�° �������� ��ΰ� �̵��� �� �̵��� �������� ������ ������ �ٽ� ������ ��������
		 * ���ƿ´�. forward�� �ι�° �������� ������ ������ �Ѿ���� �ʴ´�. include�� ���� jsp���� ��������
		 * ����ϰ��� �Ҷ� ��� include �޼ҵ�� ���� ���������� �ٸ� �������� ȣ���Ѵٰ� �����ϸ�ǰ� forward �޼ҵ��
		 * �ƿ� �������� �̵��Ѵ�.
		 */
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
