package controller;

import java.io.IOException;

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
			Integer customerId = Integer.parseInt(request.getParameter("customerId"));

			CustomerDao customerDao = DaoFactory.createCustomerDao();
			Customer customer = customerDao.findById(customerId);

			request.setAttribute("roomId", roomId);
			request.setAttribute("customer", customer);

			request.getRequestDispatcher("/WEB-INF/view/checkOut.jsp").forward(request, response);

		} catch (Exception e) {
			throw new ServletException(e);
//			HttpServletResponse res = (HttpServletResponse) response;
//			res.sendRedirect("manager");
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
