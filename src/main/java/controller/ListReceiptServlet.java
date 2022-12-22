package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.ReceiptDataDao;
import domain.ReceiptData;




@WebServlet("/listReceipt")
public class ListReceiptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String message =request.getParameter("message");
			request.setAttribute("message", message);
			
			ReceiptDataDao receiptDataDao = DaoFactory.createRecieptDataDao();
			List<ReceiptData> receiptDataList = receiptDataDao.findAll();
			request.setAttribute("receiptDataList", receiptDataList);
			request.getRequestDispatcher("/WEB-INF/view/listReceipt.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
