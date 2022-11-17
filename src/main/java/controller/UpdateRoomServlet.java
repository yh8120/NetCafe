package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.RoomDao;
import domain.Room;

@WebServlet("/updateRoom")
public class UpdateRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String strId = request.getParameter("id");
		Integer id = Integer.parseInt(strId);
		
		try {
			
			RoomDao roomDao = DaoFactory.createRoomDao();
			Room room = roomDao.findById(id);
			
			request.setAttribute("name", room.getRoomName());
			request.setAttribute("id", room.getRoomId());
			request.getRequestDispatcher("/WEB-INF/view/updateRoom.jsp")
					.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String strId = request.getParameter("id");
		Integer id = Integer.parseInt(strId);

		String name = request.getParameter("name");

		request.setAttribute("name", name);

		boolean isError = false;

		if (name.isEmpty()) {
			request.setAttribute("nameError", "名前が未入力です。");
			isError = true;
		}
		if (isError == true) {
			request.getRequestDispatcher("/WEB-INF/view/updateRoom.jsp")
					.forward(request, response);
			return;
		}

		Room room = new Room();
		room.setRoomId(id);
		room.setRoomName(name);
		
		try {
			RoomDao roomDao = DaoFactory.createRoomDao();
			roomDao.update(room);
			
			request.getRequestDispatcher("/WEB-INF/view/updateRoomDone.jsp")
					.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}