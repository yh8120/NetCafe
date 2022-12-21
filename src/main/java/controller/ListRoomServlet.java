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
import domain.Room;




@WebServlet("/listRoom")
public class ListRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String message =request.getParameter("message");
			request.setAttribute("message", message);
			
			RoomDao roomDao = DaoFactory.createRoomDao();
			List<Room> roomList = roomDao.findAll();
			request.setAttribute("roomList", roomList);
			request.getRequestDispatcher("/WEB-INF/view/listRoom.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
