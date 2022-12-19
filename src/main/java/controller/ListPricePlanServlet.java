package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.PricePlanDao;
import domain.PricePlan;




@WebServlet("/listPricePlan")
public class ListPricePlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			PricePlanDao pricePlanDao = DaoFactory.createPricePlanDao();
			List<PricePlan> planListAll = pricePlanDao.findAll();
			List<PricePlan> planListNow = pricePlanDao.findAll();
			request.setAttribute("planListAll", planListAll);
			request.setAttribute("planListNow", planListNow);
			request.getRequestDispatcher("/WEB-INF/view/listPricePlan.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
