package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.ProductDao;
import domain.Product;

@WebServlet("/shopping")
public class ShoppingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer roomId = Integer.parseInt(request.getParameter("roomId"));
			ProductDao productDao = DaoFactory.createProductDao();
			List<Product> productList = productDao.findShoppingList();
			request.setAttribute("roomId", roomId);
			request.setAttribute("productList", productList);

		} catch (Exception e) {
			throw new ServletException(e);
		}

		request.getRequestDispatcher("WEB-INF/view/shopping.jsp").forward(request, response);
	}
	
	
}
