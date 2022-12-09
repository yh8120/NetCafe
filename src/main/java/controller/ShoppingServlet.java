package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DaoFactory;
import dao.ProductDao;
import dao.ShoppingCartDao;
import domain.Product;
import domain.ShoppingCart;

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
			request.getRequestDispatcher("WEB-INF/view/shopping.jsp").forward(request, response);
		} catch (Exception e) {
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect("manager");
		}

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer roomId = Integer.parseInt(request.getParameter("roomId"));
		String cartJSON = request.getParameter("cartJSON");
		ObjectMapper mapper = new ObjectMapper();
		try {
			ShoppingCart[] cartArray = mapper.readValue(cartJSON, ShoppingCart[].class);
			ShoppingCartDao shoppingCartDao = DaoFactory.createShoppingCartDao();
			Integer sumPrice=0;
			ProductDao productDao = DaoFactory.createProductDao();
			
			for (ShoppingCart shoppingCart : cartArray) {
				Product product = productDao.findById(shoppingCart.getProductId());
				
				shoppingCart.setRoomId(roomId);
				shoppingCart.setProductName(product.getProductName());
				BigDecimal totalPrice = new BigDecimal(product.getProductPrice()*shoppingCart.getProductUnit());
				shoppingCart.setTotalPrice(totalPrice.intValue());
				shoppingCart.setTaxType(product.getTaxTypeId());
				shoppingCart.setTaxName(product.getTaxTypeName());
				BigDecimal taxRatePlus = new BigDecimal(1+product.getTaxRate());
				BigDecimal prePrice = totalPrice.divide(taxRatePlus);
				BigDecimal innerTax = totalPrice.subtract(prePrice);
				innerTax = totalPrice.setScale(0,RoundingMode.DOWN);
				System.out.println(innerTax);
				shoppingCart.setInnerTax(innerTax.intValue());
				
				shoppingCartDao.insert(shoppingCart);
				sumPrice=sumPrice+shoppingCart.getTotalPrice();
			}
			request.setAttribute("cartArray", cartArray);
			request.setAttribute("sumPrice", sumPrice);
			
			request.getRequestDispatcher("WEB-INF/view/shoppingDone.jsp").forward(request, response);

		} catch (Exception e) {
			throw new ServletException();
		}
	}

}
