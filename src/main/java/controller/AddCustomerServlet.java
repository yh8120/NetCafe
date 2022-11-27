package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.CustomCharacterEscapes;
import dao.CustomerClassDao;
import dao.CustomerDao;
import dao.DaoFactory;
import dao.IdCardDao;
import dao.SexDao;
import domain.Customer;
import domain.CustomerClass;
import domain.IdCard;
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

			IdCardDao IdCardDao = DaoFactory.createIdCardDao();
			List<IdCard> idCardList = IdCardDao.findAll();
			request.setAttribute("idCardList", idCardList);

			SexDao sexDao = DaoFactory.createSexDao();
			List<Sex> sexList = sexDao.findAll();
			request.setAttribute("sexList", sexList);

			request.getRequestDispatcher("/WEB-INF/view/addCustomer.jsp").forward(request, response);

		} catch (Exception e) {
			throw new ServletException();
		}

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BufferedReader buffer = new BufferedReader(request.getReader());
		String reqJson = buffer.readLine();
		System.out.println(reqJson);

		ObjectMapper mapper = new ObjectMapper();
		try {
			Customer customerId = mapper.readValue(reqJson, Customer.class);
			CustomerDao customerDao = DaoFactory.createCustomerDao();
			Customer customer = customerDao.findById(customerId.getCustomerId());

			mapper.getFactory().setCharacterEscapes(new CustomCharacterEscapes());

			String customerJson = mapper.writeValueAsString(customer);
			response.getWriter().write(customerJson);
		} catch (JsonProcessingException e) {
			throw new ServletException();
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

			IdCardDao IdCardDao = DaoFactory.createIdCardDao();
			List<IdCard> idCardList = IdCardDao.findAll();
			request.setAttribute("idCardList", idCardList);

			SexDao sexDao = DaoFactory.createSexDao();
			List<Sex> sexList = sexDao.findAll();
			request.setAttribute("sexList", sexList);

			String strCustomerId = request.getParameter("customerId");
			Integer customerClassId = Integer.parseInt(request.getParameter("customerClassId"));
			String lastName = request.getParameter("lastName");
			String firstName = request.getParameter("firstName");
			String lastKana = request.getParameter("lastKana");
			String firstKana = request.getParameter("firstKana");
			Integer sexId = Integer.parseInt(request.getParameter("sexId"));
			Integer cardId = Integer.parseInt(request.getParameter("cardId"));
			String customerCardNumber = request.getParameter("customerCardNumber");
			String strCustomerBirthday = request.getParameter("customerBirthday");
			String customerZipcode = request.getParameter("customerZipcode");
			String customerAddress = request.getParameter("customerAddress");
			String customerMemo = request.getParameter("customerMemo");
			String customerPhone = request.getParameter("customerPhone");
			String customerMail = request.getParameter("customerMail");

			request.setAttribute("customerId", strCustomerId);
			request.setAttribute("customerClassId", customerClassId);
			request.setAttribute("customerName", customerName);
			request.setAttribute("customerSexId", customerSexId);
			request.setAttribute("customerCardId", customerCardId);
			request.setAttribute("customerCardNumber", customerCardNumber);
			request.setAttribute("customerBirthday", strCustomerBirthday);
			request.setAttribute("customerZipcode", customerZipcode);
			request.setAttribute("customerAddress", customerAddress);
			request.setAttribute("customerMemo", customerMemo);
			request.setAttribute("customerPhone", customerPhone);
			request.setAttribute("customerMail", customerMail);
			Integer customerId = 0;
			Date customerBirthday = null;

			boolean isError = false;

			//バリデーション

			//			会員番号
			if (strCustomerId.isEmpty()) {
				request.setAttribute("customerIdError", "会員番号が未入力です");
				isError = true;
			} else {
				try {
					customerId = Integer.parseInt(strCustomerId);
					if (customerId <= 0) {
						request.setAttribute("customerIdError", "会員番号が不正です。");
						isError = true;
					}
				} catch (NumberFormatException e) {
					request.setAttribute("customerIdError", "会員番号が不正です。");
					isError = true;
				}
			}

			//			会員名
			if (lastName.isEmpty()) {
				request.setAttribute("lastNameError", "苗字が未入力です。");
				isError = true;
			}
			if (lastName.length() > 45) {
				request.setAttribute("lastNameError", "45文字以下で入力してください。");
				isError = true;
			}
			if (firstName.isEmpty()) {
				request.setAttribute("firstNameError", "苗字が未入力です。");
				isError = true;
			}
			if (firstName.length() > 45) {
				request.setAttribute("firstNameError", "45文字以下で入力してください。");
				isError = true;
			}
			//          会員かな
			if (lastKana.isEmpty()) {
				request.setAttribute("lastKanaError", "苗字が未入力です。");
				isError = true;
			}
			if (lastKana.length() > 45) {
				request.setAttribute("lastKanaError", "45文字以下で入力してください。");
				isError = true;
			}
			if (firstKana.isEmpty()) {
				request.setAttribute("firstKanaError", "苗字が未入力です。");
				isError = true;
			}
			if (firstKana.length() > 45) {
				request.setAttribute("firstKanaError", "45文字以下で入力してください。");
				isError = true;
			}

			//			身分証番号
			if (customerCardNumber.isEmpty()) {
				request.setAttribute("customerCardNumberError", "身分証番号が未入力です");
				isError = true;
			}

			//			生年月日
			if (!strCustomerBirthday.isEmpty()) {
				try {
					SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
					customerBirthday = sdFormat.parse(strCustomerBirthday);
					System.out.println(customerBirthday);
				} catch (ParseException e) {
					request.setAttribute("customerBirthdayError", "生年月日が不正です。");
					isError = true;
				}

			} else {
				request.setAttribute("customerBirthdayError", "生年月日が未入力です。");
				isError = true;
			}

			//			郵便番号
			Pattern patternZipcode = Pattern
					.compile("^[0-9]{3}-[0-9]{4}$");
			Matcher matcherZipcode = patternZipcode.matcher(customerZipcode);

			if (!matcherZipcode.matches()) {
				request.setAttribute("customerZipcodeError", "郵便番号が不正です。");
				isError = true;
			}
			//           住所
			if (customerAddress.isEmpty()) {
				request.setAttribute("customerAddressError", "住所が未入力です。");
				isError = true;
			}
			if (customerAddress.length() > 45) {
				request.setAttribute("customerAddressError", "45文字以下で入力してください。");
				isError = true;
			}

			//			メールアドレス
			Pattern patternMail = Pattern
					.compile("^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,}$");
			Matcher matcherMail = patternMail.matcher(customerMail);

			if (!matcherMail.matches()) {
				request.setAttribute("customerMailError", "メールアドレスが不正です。");
				isError = true;
			}

			//			電話番号
			Pattern patternPhone = Pattern
					.compile("[0-9+]+");
			Matcher matcherPhone = patternPhone.matcher(customerPhone);

			if (!matcherPhone.matches()) {
				request.setAttribute("customerPhoneError", "数字と＋のみ入力が可能です。");
				isError = true;
			}

			//バリデーションここまで

			if (isError) {
				request.getRequestDispatcher("/WEB-INF/view/addCustomer.jsp")
						.forward(request, response);
				return;
			}

			Customer customer = new Customer();
			customer.setCustomerId(customerId);
			customer.setCustomerClassId(customerClassId);
			customer.setCustomerName(customerName);
			customer.setCustomerSexId(customerSexId);
			customer.setCustomerCardId(customerCardId);
			customer.setCustomerCardNumber(customerCardNumber);
			customer.setCustomerBirthday(customerBirthday);
			customer.setCustomerZipcode(customerZipcode);
			customer.setCustomerAddress(customerAddress);
			customer.setCustomerMemo(customerMemo);
			customer.setCustomerPhone(customerPhone);
			customer.setCustomerMail(customerMail);

			CustomerDao customerDao = DaoFactory.createCustomerDao();
			customerDao.insert(customer);
			request.getRequestDispatcher("/WEB-INF/view/addCustomerDone.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
