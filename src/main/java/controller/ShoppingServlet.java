package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
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

		} catch (Exception e) {
			throw new ServletException(e);
		}

		request.getRequestDispatcher("WEB-INF/view/shopping.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer roomId = Integer.parseInt(request.getParameter("roomId"));
		BufferedReader buffer = new BufferedReader(request.getReader());
		String cartJSON = buffer.readLine();
		System.out.println(cartJSON);

		ObjectMapper mapper = new ObjectMapper();
		try {
			ShoppingCart[] cartArray = mapper.readValue(cartJSON, ShoppingCart[].class);
			ShoppingCartDao shoppingCartDao = DaoFactory.createShoppingCartDao();
			List<ShoppingCart> newCartArray=new ArrayList<>();
			Integer sumPrice=0;
			for (ShoppingCart cart : cartArray) {
				cart.setRoomId(roomId);
				Integer totalPrice = cart.getProductPrice()*cart.getProductUnit();
				cart.setTotalPrice(totalPrice);
				shoppingCartDao.insert(cart);
				newCartArray.add(cart);
				sumPrice=sumPrice+cart.getTotalPrice();
			}
			request.setAttribute("cartArray", newCartArray);
			request.setAttribute("sumPrice", sumPrice);
			
			request.getRequestDispatcher("WEB-INF/view/shoppingDone.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}

}
