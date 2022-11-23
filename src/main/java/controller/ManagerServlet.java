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

@WebServlet("/manager")
public class ManagerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			RoomDao roomDao = DaoFactory.createRoomDao();
			List<Room> roomList = roomDao.makeManager();
			request.setAttribute("roomList", roomList);

		} catch (Exception e) {
			throw new ServletException(e);
		}

		request.getRequestDispatcher("WEB-INF/view/manager.jsp").forward(request, response);
	}
}
