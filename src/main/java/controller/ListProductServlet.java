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




@WebServlet("/listProduct")
public class ListProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ProductDao productDao = DaoFactory.createProductDao();
			List<Product> productList = productDao.findAll();
			request.setAttribute("productList", productList);
			request.getRequestDispatcher("/WEB-INF/view/listProduct.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
