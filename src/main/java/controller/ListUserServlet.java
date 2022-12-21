package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.UserDao;
import domain.User;




@WebServlet("/listUser")
public class ListUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String message =request.getParameter("message");
			request.setAttribute("message", message);
			
			UserDao userDao = DaoFactory.createUserDao();
			List<User> userList = userDao.findAll();
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("/WEB-INF/view/listUser.jsp").forward(request, response);

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
