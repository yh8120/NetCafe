package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.ProductDao;
import domain.Product;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Integer productId = Integer.parseInt(request.getParameter("productId"));
		
		try {
			
			ProductDao productDao = DaoFactory.createProductDao();
			Product product =new Product();
			product.setProductId(productId);
			productDao.delete(product);
			response.sendRedirect("listProduct");
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}