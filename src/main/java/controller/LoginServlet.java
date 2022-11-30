package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.UserDao;
import domain.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isError = false;
		
		String loginId = request.getParameter("loginId");
		request.setAttribute("loginId", loginId);
		if (loginId.isEmpty()) {
			request.setAttribute("loginIdError", "ログインIDが未入力です。");
			isError = true;
		}
		
		
		String loginPass = request.getParameter("loginPass");
		request.setAttribute("loginPass", loginPass);
		if (loginPass.isEmpty()) {
			request.setAttribute("loginPassError", "パスワードが未入力です。");
			isError = true;
		}
		
		if (isError == true) {
			request.getRequestDispatcher("/WEB-INF/view/login.jsp")
					.forward(request, response);
			return;
		}
		
		try {
			
			UserDao userDao = DaoFactory.createUserDao();

			User user = userDao.findByLoginIdAndLoginPass(loginId, loginPass);
			
			Integer storeId =123;
			
			if(user!=null) {
				request.getSession().setAttribute("loginId", user.getLoginId());
				request.getSession().setAttribute("storeId", storeId);
				response.sendRedirect("index");
			}else {
				request.setAttribute("error", true);
				request.getRequestDispatcher("WEB-INF/view/login.jsp") .forward(request, response);
			}

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
