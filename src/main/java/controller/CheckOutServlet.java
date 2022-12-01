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
import javax.servlet.http.HttpSession;

import dao.CustomerDao;
import dao.DaoFactory;
import dao.RoomDao;
import dao.UserDao;
import domain.Customer;
import domain.Room;
import domain.User;

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

			BigDecimal currentMS = new BigDecimal(now.getTime() - startTime.getTime());
			BigDecimal basicPrice = new BigDecimal(500);
			BigDecimal basicMS = new BigDecimal(3600000);
			BigDecimal addPrice = new BigDecimal(100);
			BigDecimal addMS = new BigDecimal(600000);
			BigDecimal tax = new BigDecimal(0.1);
			BigDecimal currentHour = currentMS.divide(BigDecimal.valueOf(3600000), RoundingMode.DOWN);
			BigDecimal currentHourMS = currentHour.multiply(BigDecimal.valueOf(3600000));
			BigDecimal currentMin = (currentMS.subtract(currentHourMS)).divide(BigDecimal.valueOf(60000),
					RoundingMode.DOWN);
			BigDecimal currentHourMinMS = currentHourMS.add(currentMin.multiply(BigDecimal.valueOf(60000)));
			BigDecimal currentSec = (currentMS.subtract(currentHourMinMS)).divide(BigDecimal.valueOf(1000),
					RoundingMode.DOWN);

			// 超過時間（＝利用時間－基本時間）
			// (利用時間が基本時間以下の場合超過時間は0)
			BigDecimal excMS = currentMS.subtract(basicMS);
			if (excMS.compareTo(BigDecimal.ZERO) == -1) {
				excMS = BigDecimal.ZERO;
			}
			System.out.println(excMS);
			// 追加料金発生回数（＝超過時間÷追加料金時間(切り上げ））
			BigDecimal timesOfAdded = excMS.divide(addMS, RoundingMode.UP);
			System.out.println(timesOfAdded);
			// 追加料金（＝追加料金発生時間×追加料金）
			BigDecimal excPrice = (timesOfAdded).multiply(addPrice);
			System.out.println(excPrice);
			// 小計（＝基本料金＋(追加料金)）
			BigDecimal subtotal = basicPrice.add(excPrice);
			System.out.println(subtotal);
			// 内消費税（＝小計×税率(四捨五入)）
			BigDecimal taxFee = subtotal.multiply(tax);
			taxFee = taxFee.setScale(0, RoundingMode.HALF_UP);
			// 合計（＝小計＋内消費税）
			BigDecimal currentPrice = subtotal.add(taxFee);
			System.out.println(currentPrice);

			room.setStayingTime(currentMS.longValue());
			room.setSubtotal(subtotal.intValue());
			room.setCurrentPrice(currentPrice.intValue());

			roomdao.preCheckOut(room);

			request.setAttribute("room", room);
			request.setAttribute("customer", customer);
			request.setAttribute("currentHour", currentHour);
			request.setAttribute("currentMin", currentMin);
			request.setAttribute("currentSec", currentSec);
			request.setAttribute("tax", tax);
			request.setAttribute("taxFee", taxFee);

			request.getRequestDispatcher("/WEB-INF/view/checkOut.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
			//			HttpServletResponse res = (HttpServletResponse) response;
			//			res.sendRedirect("manager");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			User user= (User) session.getAttribute("user");
			
			Integer roomId = Integer.parseInt(request.getParameter("roomId"));
			RoomDao roomdao = DaoFactory.createRoomDao();
			Room room = roomdao.findById(roomId);
			
			
			UserDao userDao = DaoFactory.createUserDao();
			
			roomdao.checkOut(room);
			
			request.getRequestDispatcher("/WEB-INF/view/checkOutDone.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
