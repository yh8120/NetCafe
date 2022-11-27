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
			String strCustomerClassId = request.getParameter("customerClassId");
			String lastName = request.getParameter("lastName");
			String firstName = request.getParameter("firstName");
			String lastKana = request.getParameter("lastKana");
			String firstKana = request.getParameter("firstKana");
			String strSexId = request.getParameter("sexId");
			String strCardId = request.getParameter("cardId");
			String cardNumber = request.getParameter("cardNumber");
			String strBirthday = request.getParameter("birthday");
			String zipcodePost = request.getParameter("zipcodePost");
			String zipcodeCity = request.getParameter("zipcodeCity");
			String addressState = request.getParameter("addressState");
			String addressCity = request.getParameter("addressCity");
			String addressStreet = request.getParameter("addressStreet");
			String addressRoom = request.getParameter("addressRoom");
			String memo = request.getParameter("memo");
			String phoneNumberA = request.getParameter("phoneNumberA");
			String phoneNumberB = request.getParameter("phoneNumberB");
			String phoneNumberC = request.getParameter("phoneNumberC");
			String eMailUserName = request.getParameter("eMailUserName");
			String eMailDomain = request.getParameter("eMailDomain");

			request.setAttribute("customerId", strCustomerId);
			request.setAttribute("strCustomerClassId", strCustomerClassId);
			request.setAttribute("lastName", lastName);
			request.setAttribute("firstName", firstName);
			request.setAttribute("lastName", lastName);
			request.setAttribute("firstName", firstName);
			request.setAttribute("lastKana", lastKana);
			request.setAttribute("firstKana", firstKana);
			request.setAttribute("strSexId", strSexId);
			request.setAttribute("strCardId", strCardId);
			request.setAttribute("cardNumber", cardNumber);
			request.setAttribute("birthday", strBirthday);
			request.setAttribute("strZipcodePost", zipcodePost);
			request.setAttribute("strZipcodeCity", zipcodeCity);
			request.setAttribute("addressState", addressState);
			request.setAttribute("addressCity", addressCity);
			request.setAttribute("addressStreet", addressStreet);
			request.setAttribute("addressRoom", addressRoom);
			request.setAttribute("memo", memo);
			request.setAttribute("phoneNumberA", phoneNumberA);
			request.setAttribute("phoneNumberB", phoneNumberB);
			request.setAttribute("phoneNumberC", phoneNumberC);
			request.setAttribute("eMailUserName", eMailUserName);
			request.setAttribute("eMailDomain", eMailDomain);
			Integer customerId = 0;
			Integer customerClassId = 0;
			Integer sexId = 0;
			Integer cardId = 0;
			Date birthday = null;
			IdCard idCard = null;

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

			//          会員クラスID
			if (strCustomerClassId == null) {
				request.setAttribute("customerClassIdError", "会員クラスが未入力です");
				isError = true;
			} else {
				try {
					customerClassId = Integer.parseInt(strCustomerClassId);
					if (customerClassId < 0) {
						request.setAttribute("customerClassIdError", "会員クラスが不正です。");
						isError = true;
					}
				} catch (NumberFormatException e) {
					request.setAttribute("customerClassIdError", "会員クラスが不正です。");
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

			//          性別ID
			if (strSexId == null) {
				request.setAttribute("sexIdError", "性別が未入力です");
				isError = true;
			} else {
				try {
					sexId = Integer.parseInt(strSexId);
					if (sexId < 0) {
						request.setAttribute("sexIdError", "性別が不正です。");
						isError = true;
					}
				} catch (NumberFormatException e) {
					request.setAttribute("sexIdError", "性別が不正です。");
					isError = true;
				}
			}

			//          身分証関係
			if (strCardId == null) {
				request.setAttribute("cardIdError", "カード種別が未入力です");
				isError = true;
			} else {
				try {
					cardId = Integer.parseInt(strCardId);
					if (cardId < 0) {
						request.setAttribute("cardIdError", "カード種別が不正です。");
						isError = true;

						//身分証番号の入力可否の判定のためにidCardインスタンスが必要なの
						idCard = IdCardDao.findById(cardId);
						if (!idCard.getCanCopyNumber() && cardNumber.isEmpty()) {
							request.setAttribute("cardNumberError", "身分証番号が未入力です");
							isError = true;
						}

					}
				} catch (NumberFormatException e) {
					request.setAttribute("cardIdError", "カード種別が不正です。");
					isError = true;
				}
			}

			//			生年月日
			if (!strBirthday.isEmpty()) {
				try {
					SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
					birthday = sdFormat.parse(strBirthday);
				} catch (ParseException e) {
					request.setAttribute("birthdayError", "生年月日が不正です。");
					isError = true;
				}

			} else {
				request.setAttribute("birthdayError", "生年月日が未入力です。");
				isError = true;
			}

			//			郵便番号
			Pattern ptnZipPost = Pattern
					.compile("^[0-9]{3}");
			Matcher mtcZipPost = ptnZipPost.matcher(zipcodePost);

			if (!mtcZipPost.matches()) {
				request.setAttribute("zipcodePostError", "郵便番号が不正です。");
				isError = true;
			}

			Pattern ptnZipCity = Pattern
					.compile("^[0-9]{4}");
			Matcher mtcZipCity = ptnZipCity.matcher(zipcodeCity);

			if (!mtcZipCity.matches()) {
				request.setAttribute("zipcodeCityError", "郵便番号が不正です。");
				isError = true;
			}
			//           住所
			if (addressState.isEmpty()) {
				request.setAttribute("addressStateError", "都道府県が未入力です。");
				isError = true;
			}
			if (addressState.length() > 45) {
				request.setAttribute("addressStateError", "45文字以下で入力してください。");
				isError = true;
			}
			if (addressCity.isEmpty()) {
				request.setAttribute("addressCityError", "市区町村が未入力です。");
				isError = true;
			}
			if (addressCity.length() > 45) {
				request.setAttribute("addressCityError", "45文字以下で入力してください。");
				isError = true;
			}
			if (addressStreet.isEmpty()) {
				request.setAttribute("addressStreetError", "番地が未入力です。");
				isError = true;
			}
			if (addressStreet.length() > 45) {
				request.setAttribute("addressStreetError", "45文字以下で入力してください。");
				isError = true;
			}

			//			電話番号
			Pattern ptnPhoneA = Pattern
					.compile("[0-9+]");
			Matcher mtcPhoneA = ptnPhoneA.matcher(phoneNumberA);

			Pattern ptnPhone = Pattern
					.compile("[0-9]");
			Matcher mtcPhoneB = ptnPhone.matcher(phoneNumberB);
			Matcher mtcPhoneC = ptnPhone.matcher(phoneNumberC);

			if (!mtcPhoneA.matches() || !mtcPhoneB.matches() || !mtcPhoneC.matches()) {
				request.setAttribute("phoneNumberError", "電話番号が不正です。");
				isError = true;
			}

			//			メールアドレス
			Pattern ptnEMailUser = Pattern
					.compile("^[a-zA-Z0-9_.+-]");
			Matcher mtcEMailUser = ptnEMailUser.matcher(eMailUserName);

			if (!mtcEMailUser.matches()) {
				request.setAttribute("eMailUserNameError", "メールアドレスが不正です。");
				isError = true;
			}

			Pattern ptnEMailDomain = Pattern
					.compile("^([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,}$");
			Matcher mtcEMailDomain = ptnEMailDomain.matcher(eMailDomain);

			if (!mtcEMailDomain.matches()) {
				request.setAttribute("eMailDomainError", "ドメイン名が不正です。");
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
			customer.setLastName(lastName);
			customer.setFirstName(firstName);
			customer.setLastKana(lastKana);
			customer.setFirstKana(firstKana);
			customer.setSexId(sexId);
			customer.setCardId(cardId);
			customer.setCardNumber(cardNumber);
			customer.setBirthday(birthday);
			customer.setZipcodePost(zipcodePost);
			customer.setZipcodeCity(zipcodeCity);
			customer.setAddressState(addressState);
			customer.setAddressCity(addressCity);
			customer.setAddressStreet(addressStreet);
			customer.setAddressRoom(addressRoom);
			customer.setMemo(memo);
			customer.setPhoneNumberA(phoneNumberA);
			customer.setPhoneNumberB(phoneNumberB);
			customer.setPhoneNumberC(phoneNumberC);
			customer.seteMailUserName(eMailUserName);
			customer.seteMailDomain(eMailDomain);

			CustomerDao customerDao = DaoFactory.createCustomerDao();
			customerDao.insert(customer);
			request.getRequestDispatcher("/WEB-INF/view/addCustomerDone.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
