package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import dao.DaoFactory;
import dao.RoomStatusDao;
import domain.Customer;
import domain.RoomStatus;

@WebServlet("/checkIn")
public class CheckInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer roomId = Integer.parseInt(request.getParameter("roomId"));
			
			request.setAttribute("roomId", roomId);
			
			request.getRequestDispatcher("/WEB-INF/view/checkIn.jsp").forward(request, response);

		} catch (Exception e) {
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect("manager");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		try {
			Integer roomId = Integer.parseInt(request.getParameter("roomId"));
			String strCustomerId = request.getParameter("customerId");
			

			request.setAttribute("roomId", roomId);
			request.setAttribute("customerId", strCustomerId);
			
			Integer customerId = 0;
			
			boolean isError = false;
			
			//会員番号のバリデーション
			if (strCustomerId.isEmpty()) {
				request.setAttribute("customerIdError", "会員番号が未入力です");
				isError = true;
			} else {
				try {
					customerId = Integer.parseInt(strCustomerId);
					if (customerId <= 0) {
						request.setAttribute("customerIdError", "会員番号が不正です。");
						isError = true;
					}
				} catch (NumberFormatException e) {
					request.setAttribute("customerIdError", "会員番号が不正です。");
					isError = true;
				}
			}
			
			//会員検索
			CustomerDao customerDao = DaoFactory.createCustomerDao();
			Customer customer = customerDao.findById(customerId);
			if(customer==null) {
				request.setAttribute("customerIdError", "会員情報がありません。");
				isError = true;
			}
			
			
			if (isError) {
				request.getRequestDispatcher("/WEB-INF/view/checkIn.jsp")
				.forward(request, response);
				return;
			}
			
			//TODO 同じ会員番号での入室を防ぐよう修正
			
			RoomStatus roomStatus = new RoomStatus();
			roomStatus.setRoomId(roomId);
			roomStatus.setCustomerId(customerId);

			RoomStatusDao roomStatusDao = DaoFactory.createRoomStatusDao();
			roomStatusDao.insert(roomStatus);
			
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect("manager");
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
