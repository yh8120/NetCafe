package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.RoomDao;
import dao.RoomTypeDao;
import domain.Room;
import domain.RoomType;

/**
 * Servlet implementation class AddItemServlet
 */
@WebServlet("/addRoom")
public class AddRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			RoomTypeDao roomTypeDao = DaoFactory.createRoomTypeDao();
			List<RoomType> roomTypeList = roomTypeDao.findAll();
			request.setAttribute("roomTypeList", roomTypeList);
			
			RoomDao roomDao = DaoFactory.createRoomDao();
			List<Room> roomList = roomDao.findAll();
			request.setAttribute("roomList", roomList);
			
			request.getRequestDispatcher("/WEB-INF/view/addRoom.jsp").forward(request, response);

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
			
			String name = request.getParameter("name");
			
			
			request.setAttribute("name", name);
			
			boolean isError = false;
			if (name.isEmpty()) {
				request.setAttribute("nameError", "品名が未入力です。");
				isError = true;
			}
			if (name.length() > 50) {
				request.setAttribute("nameError", "50文字以下で入力してください。");
				isError = true;
			}   
			
			
			if (isError) {
				request.getRequestDispatcher("/WEB-INF/view/addRoom.jsp")
				.forward(request, response);
				return;
			}

			Room room = new Room();
			room.setRoomTypeName(name);

			RoomDao roomDao = DaoFactory.createRoomDao();
			roomDao.insert(room);
			request.getRequestDispatcher("/WEB-INF/view/addRoomDone.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
