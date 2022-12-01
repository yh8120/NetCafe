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
import dao.ShopDao;
import dao.UserClassDao;
import dao.UserDao;
import domain.Shop;
import domain.User;
import domain.UserClass;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String strUserId = request.getParameter("userId");
		Integer userId = Integer.parseInt(strUserId);

		try {
			UserClassDao userClassDao = DaoFactory.createUserClassDao();
			List<UserClass> userClassList = userClassDao.findAll();
			request.setAttribute("userClassList", userClassList);
			
			ShopDao shopDao = DaoFactory.createShopDao();
			List<Shop> shopList = shopDao.findAll();
			request.setAttribute("shopList", shopList);

			UserDao userDao = DaoFactory.createUserDao();
			User user = userDao.findById(userId);

			request.setAttribute("userName", user.getUserName());
			request.setAttribute("newLoginId", user.getLoginId());
			request.setAttribute("userClassId", user.getUserClassId());
			request.getRequestDispatcher("/WEB-INF/view/updateUser.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			UserClassDao userClassDao = DaoFactory.createUserClassDao();
			List<UserClass> userClassList = userClassDao.findAll();
			request.setAttribute("userClassList", userClassList);
			
			ShopDao shopDao = DaoFactory.createShopDao();
			List<Shop> shopList = shopDao.findAll();
			request.setAttribute("shopList", shopList);

			Integer userId = Integer.parseInt(request.getParameter("userId"));
			String userName = request.getParameter("userName");
			String newLoginId = request.getParameter("newLoginId");
			String loginPass = request.getParameter("loginPass");
			Integer userClassId = Integer.parseInt(request.getParameter("userClassId"));
			Integer shopId = Integer.parseInt(request.getParameter("shopId"));

			
			request.setAttribute("userName", userName);
			request.setAttribute("newLoginId", newLoginId);
			request.setAttribute("loginPass", loginPass);
			request.setAttribute("userclassId", userClassId);
			request.setAttribute("shopId", shopId);

			boolean isError = false;
			if (userName.isEmpty()) {
				request.setAttribute("userNameError", "名前が未入力です。");
				isError = true;
			}
			if (userName.length() > 50) {
				request.setAttribute("userNameError", "20文字以下で入力してください。");
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
			user.setUserId(userId);
			user.setUserName(userName);
			user.setLoginId(newLoginId);
			user.setLoginPass(BCrypt.hashpw(loginPass, BCrypt.gensalt()));
			user.setUserClassId(userClassId);
			user.setShopId(shopId);

			UserDao userDao = DaoFactory.createUserDao();
			userDao.update(user);

			request.getRequestDispatcher("/WEB-INF/view/updateUserDone.jsp")
					.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}