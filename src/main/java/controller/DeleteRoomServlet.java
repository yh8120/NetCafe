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

@WebServlet("/deleteRoom")
public class DeleteRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Integer roomId = Integer.parseInt(request.getParameter("roomId"));
		
		try {
			
			RoomDao roomDao = DaoFactory.createRoomDao();
			Room room = roomDao.findById(roomId);
			
			request.setAttribute("room", room);
			request.getRequestDispatcher("/WEB-INF/view/deleteRoom.jsp")
					.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Integer roomId = Integer.parseInt(request.getParameter("roomId"));
		
		Room room = new Room();
		room.setRoomId(roomId);
		try {
			
			RoomDao roomDao = DaoFactory.createRoomDao();
			roomDao.delete(room);
			
			request.getRequestDispatcher("/WEB-INF/view/deleteRoomDone.jsp")
					.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}