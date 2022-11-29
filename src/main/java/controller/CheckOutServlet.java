package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import dao.DaoFactory;
import dao.RoomDao;
import domain.Customer;
import domain.Room;

@WebServlet("/checkOut")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer roomId = Integer.parseInt(request.getParameter("roomId"));
			RoomDao roomdao = DaoFactory.createRoomDao();
			Room room = roomdao.findById(roomId);

			Integer customerId = room.getCustomerId();
			Date startTime = room.getStarted();

			CustomerDao customerDao = DaoFactory.createCustomerDao();
			Customer customer = customerDao.findById(customerId);
			Date now = new Date();

			//			BigDecimal b2= new BigDecimal(60*1000);
			//			BigDecimal currentTime = b1.divide(b2,RoundingMode.HALF_UP);
			//			BigDecimal baisicChargeTime = new BigDecimal(10);
			//			BigDecimal countCharges = currentTime.divide(baisicChargeTime,RoundingMode.DOWN);

			BigDecimal bigCurrentMS = new BigDecimal(now.getTime() - startTime.getTime());
			BigDecimal basicPrice = new BigDecimal(500);
			BigDecimal basicMS = new BigDecimal(3600000);
			BigDecimal addPrice = new BigDecimal(100);
			BigDecimal addMS = new BigDecimal(600000);
			BigDecimal bigTax = new BigDecimal(1.1);
			BigDecimal bigSubtotal = null;
			BigDecimal bigCurrentPrice = null;

			if (bigCurrentMS.compareTo(basicMS)==-1) {
				bigCurrentPrice = bigTax.multiply(basicPrice); 
			}else {
				BigDecimal currentTime = bigCurrentMS.subtract(basicMS);
				System.out.println(currentTime);
				BigDecimal timesOfAdded = (currentTime).divide(addMS,RoundingMode.DOWN);
				System.out.println(timesOfAdded);
				bigSubtotal = (timesOfAdded).multiply(addPrice);
				System.out.println(bigSubtotal);
				bigCurrentPrice = bigSubtotal.multiply(bigTax);
				//四捨五入
				bigCurrentPrice = bigCurrentPrice.setScale(0, RoundingMode.HALF_UP);
				System.out.println(bigCurrentPrice);
			}
			
			Double currentPrice = bigCurrentPrice.doubleValue();
			Double subtotal = bigSubtotal.doubleValue();
			Integer tax = ((bigTax.subtract(new BigDecimal(1))).multiply(new BigDecimal(100))).intValue();
			Double currentTime = bigCurrentMS.doubleValue()/60000;
			
			request.setAttribute("roomId", roomId);
			request.setAttribute("customer", customer);
			request.setAttribute("currentTime", currentTime);
			request.setAttribute("subtotal", subtotal);
			request.setAttribute("tax", bigTax);
			request.setAttribute("currentPrice", currentPrice);

			request.getRequestDispatcher("/WEB-INF/view/checkOut.jsp").forward(request, response);

		} catch (Exception e) {
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect("manager");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer roomId = Integer.parseInt(request.getParameter("roomId"));

		Room room = new Room();
		room.setRoomId(roomId);
		try {

			RoomDao roomDao = DaoFactory.createRoomDao();
			roomDao.checkOut(room);

			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect("manager");
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
