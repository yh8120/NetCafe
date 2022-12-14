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

@WebServlet("/cleaning")
public class CleaningServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer roomId = Integer.parseInt(request.getParameter("roomId"));
			Integer cleaningId = Integer.parseInt(request.getParameter("cleaningId"));
			
			RoomDao roomDao = DaoFactory.createRoomDao();
			Room room = new Room();
			
			room.setRoomId(roomId);
			room.setCleaningStatus(cleaningId);
			
			roomDao.cleaning(room);
			
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect("manager");

		} catch (Exception e) {
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect("manager");
		}

	}

}