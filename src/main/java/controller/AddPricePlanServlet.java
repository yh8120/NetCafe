package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerClassDao;
import dao.DaoFactory;
import dao.RoomDao;
import dao.RoomTypeDao;
import dao.SexDao;
import domain.CustomerClass;
import domain.Room;
import domain.RoomType;
import domain.Sex;

@WebServlet("/addPricePlan")
public class AddPricePlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			SexDao sexDao = DaoFactory.createSexDao();
			List<Sex> sexList = sexDao.findAll();
			RoomTypeDao roomTypeDao = DaoFactory.createRoomTypeDao();
			List<RoomType> roomTypeList = roomTypeDao.findAll();
			CustomerClassDao customerClassDao = DaoFactory.createCustomerClassDao();
			List<CustomerClass> customerClassList = customerClassDao.findAll();
			
			request.setAttribute("sexList", sexList);
			request.setAttribute("roomTypeList", roomTypeList);
			request.setAttribute("customerClassList", customerClassList);
			
			request.getRequestDispatcher("/WEB-INF/view/addPricePlan.jsp").forward(request, response);

		} catch (Exception e) {
			throw new ServletException();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		try {
			RoomTypeDao roomTypeDao = DaoFactory.createRoomTypeDao();
			List<RoomType> roomTypeList = roomTypeDao.findAll();
			request.setAttribute("roomTypeList", roomTypeList);
			
			String roomName = request.getParameter("roomName");
			Integer roomTypeId =Integer.parseInt(request.getParameter("roomTypeId"));
			
			request.setAttribute("roomName", roomName);
			request.setAttribute("roomTypeId", roomTypeId);
			
			boolean isError = false;
			if (roomName.isEmpty()) {
				request.setAttribute("roomNameError", "名前が未入力です。");
				isError = true;
			}
			if (roomName.length() > 20) {
				request.setAttribute("roomNameError", "20文字以下で入力してください。");
				isError = true;
			}
			
			
			if (isError) {
				request.getRequestDispatcher("/WEB-INF/view/addRoom.jsp")
				.forward(request, response);
				return;
			}

			Room room = new Room();
			room.setRoomName(roomName);
			room.setRoomTypeId(roomTypeId);

			RoomDao roomDao = DaoFactory.createRoomDao();
			roomDao.insert(room);
			request.getRequestDispatcher("/WEB-INF/view/addRoomDone.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
