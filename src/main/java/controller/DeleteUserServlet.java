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

/** 
 * Servlet implementation class DeleteUserServlet 
 */
@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/** 
	* @see HttpServlet#doGet(HttpServletRequest request, … 
	*/
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		
		try {
			
			UserDao userDao = DaoFactory.createUserDao();
			User user = userDao.findById(userId);
			
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/view/deleteUser.jsp")
					.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		
		User user = new User();
		user.setUserId(userId);
		try {
			
			UserDao userDao = DaoFactory.createUserDao();
			userDao.delete(user);
			
			request.getRequestDispatcher("/WEB-INF/view/deleteUserDone.jsp")
					.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}