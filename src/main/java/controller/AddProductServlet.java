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
import dao.ProductTypeDao;
import domain.Product;
import domain.ProductType;

/**
 * Servlet implementation class AddItemServlet
 */
@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ProductTypeDao productTypeDao = DaoFactory.createProductTypeDao();
			List<ProductType> productTypeList = productTypeDao.findAll();
			request.setAttribute("productTypeList", productTypeList);

			request.getRequestDispatcher("/WEB-INF/view/addProduct.jsp").forward(request, response);

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
			ProductTypeDao productTypeDao = DaoFactory.createProductTypeDao();
			List<ProductType> productTypeList = productTypeDao.findAll();
			request.setAttribute("productTypeList", productTypeList);

			String productName = request.getParameter("productName");
			String strProductPrice = request.getParameter("productPrice");
			String strProductTypeId = request.getParameter("productTypeId");

			request.setAttribute("productName", productName);
			request.setAttribute("productPrice", strProductPrice);
			request.setAttribute("productType", strProductTypeId);

			boolean isError = false;
			Integer productPrice = 0;
			Integer productTypeId = 0;
			
			if (productName.isEmpty()) {
				request.setAttribute("nameError", "名前が未入力です。");
				isError = true;
			}
			if (productName.length() > 20) {
				request.setAttribute("nameError", "20文字以下で入力してください。");
				isError = true;
			}
			
			if (strProductPrice.isEmpty()) {
				request.setAttribute("productPriceError", "商品価格が未入力です");
				isError = true;
			} else {
				try {
					productPrice = Integer.parseInt(strProductPrice);
					if (productPrice < 0) {
						request.setAttribute("productPriceError", "商品価格が不正です。");
						isError = true;
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute("productPriceError", "商品価格が不正です。");
					isError = true;
				}
			}

			if (strProductTypeId.isEmpty()) {
				request.setAttribute("productTypeIdError", "商品価格が未入力です");
				isError = true;
			} else {
				try {
					productTypeId = Integer.parseInt(strProductTypeId);
					if (productTypeId < 0) {
						request.setAttribute("productTypeIdError", "商品価格が不正です。");
						isError = true;
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute("productTypeIdError", "商品価格が不正です。");
					isError = true;
				}
			}

			if (isError) {
				response.sendRedirect("listProduct");
				return;
			}

			Product product = new Product();
			product.setProductName(productName);
			product.setProductPrice(productPrice);
			product.setProductTypeId(productTypeId);

			ProductDao productDao = DaoFactory.createProductDao();
			productDao.insert(product);
			request.getRequestDispatcher("/WEB-INF/view/listProduct.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
