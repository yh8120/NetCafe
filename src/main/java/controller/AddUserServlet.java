package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import dao.DaoFactory;
import dao.UserClassDao;
import dao.UserDao;
import domain.User;
import domain.UserClass;

/**
 * Servlet implementation class AddItemServlet
 */
@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			UserClassDao userClassDao = DaoFactory.createUserClassDao();
			List<UserClass> userClassList = userClassDao.findAll();
			request.setAttribute("userClassList", userClassList);

			request.getRequestDispatcher("/WEB-INF/view/addUser.jsp").forward(request, response);

		} catch (Exception e) {
			throw new ServletException();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			UserClassDao userClassDao = DaoFactory.createUserClassDao();
			List<UserClass> userClassList = userClassDao.findAll();
			request.setAttribute("userClassList", userClassList);

			String name = request.getParameter("name");
			String newLoginId = request.getParameter("newLoginId");
			String loginPass = request.getParameter("loginPass");
			Integer userClassId = Integer.parseInt(request.getParameter("userClassId"));

			request.setAttribute("name", name);
			request.setAttribute("newLoginId", newLoginId);
			request.setAttribute("loginPass", loginPass);
			request.setAttribute("userClassId", userClassId);

			boolean isError = false;
			if (name.isEmpty()) {
				request.setAttribute("nameError", "名前が未入力です。");
				isError = true;
			}
			if (name.length() > 50) {
				request.setAttribute("nameError", "20文字以下で入力してください。");
				isError = true;
			}
			if (newLoginId.isEmpty()) {
				request.setAttribute("newLoginIdError", "IDが未入力です。");
				isError = true;
			}
			if (newLoginId.length() > 20) {
				request.setAttribute("newLoginIdError", "20文字以下で入力してください。");
				isError = true;
			}
			if (loginPass.isEmpty()) {
				request.setAttribute("loginPassError", "パスワードが未入力です。");
				isError = true;
			}

			if (isError) {
				request.getRequestDispatcher("/WEB-INF/view/addUser.jsp")
						.forward(request, response);
				return;
			}

			User user = new User();
			user.setUserName(name);
			user.setLoginId(newLoginId);
			user.setLoginPass(BCrypt.hashpw(loginPass, BCrypt.gensalt()));
			user.setUserClassId(userClassId);

			UserDao userDao = DaoFactory.createUserDao();
			userDao.insert(user);
			request.getRequestDispatcher("/WEB-INF/view/addUserDone.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
