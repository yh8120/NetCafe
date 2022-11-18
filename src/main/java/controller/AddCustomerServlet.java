package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CardDao;
import dao.CustomerClassDao;
import dao.CustomerDao;
import dao.DaoFactory;
import dao.SexDao;
import domain.Card;
import domain.Customer;
import domain.CustomerClass;
import domain.Sex;

@WebServlet("/addCustomer")
public class AddCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			CustomerClassDao customerClassDao = DaoFactory.createCustomerClassDao();
			List<CustomerClass> customerClassList = customerClassDao.findAll();
			request.setAttribute("customerClassList", customerClassList);
			
			CardDao cardDao = DaoFactory.createCardDao();
			List<Card> cardList = cardDao.findAll();
			request.setAttribute("cardList", cardList);
			
			SexDao sexDao = DaoFactory.createSexDao();
			List<Sex> sexList = sexDao.findAll();
			request.setAttribute("sexList", sexList);
			
			request.getRequestDispatcher("/WEB-INF/view/addCustomer.jsp").forward(request, response);

		} catch (Exception e) {
			throw new ServletException();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		try {
			CustomerClassDao customerClassDao = DaoFactory.createCustomerClassDao();
			List<CustomerClass> customerClassList = customerClassDao.findAll();
			request.setAttribute("customerClassList", customerClassList);
			
			CardDao cardDao = DaoFactory.createCardDao();
			List<Card> cardList = cardDao.findAll();
			request.setAttribute("cardList", cardList);
			
			SexDao sexDao = DaoFactory.createSexDao();
			List<Sex> sexList = sexDao.findAll();
			request.setAttribute("sexList", sexList);
			
			String customerName = request.getParameter("customerName");
			Integer customerClassId =Integer.parseInt(request.getParameter("customerClassId"));
			
			request.setAttribute("customerName", customerName);
			request.setAttribute("customerClassId", customerClassId);
			
			boolean isError = false;
			if (customerName.isEmpty()) {
				request.setAttribute("customerNameError", "名前が未入力です。");
				isError = true;
			}
			if (customerName.length() > 20) {
				request.setAttribute("customerNameError", "20文字以下で入力してください。");
				isError = true;
			}
			
			
			if (isError) {
				request.getRequestDispatcher("/WEB-INF/view/addCustomer.jsp")
				.forward(request, response);
				return;
			}

			Customer customer = new Customer();
			customer.setCustomerName(customerName);
			customer.setCustomerClassId(customerClassId);

			CustomerDao customerDao = DaoFactory.createCustomerDao();
			customerDao.insert(customer);
			request.getRequestDispatcher("/WEB-INF/view/addCustomerDone.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
