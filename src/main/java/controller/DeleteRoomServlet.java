package controller;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

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
		}catch (SQLIntegrityConstraintViolationException e) {
			response.sendRedirect("listRoom?message=%E6%95%B4%E5%90%88%E6%80%A7%E7%A2%BA%E4%BF%9D%E3%81%AE%E3%81%9F%E3%82%81%E5%89%8A%E9%99%A4%E3%81%A7%E3%81%8D%E3%81%BE%E3%81%9B%E3%82%93");
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}