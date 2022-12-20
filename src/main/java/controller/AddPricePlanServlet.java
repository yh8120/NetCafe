package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerClassDao;
import dao.DaoFactory;
import dao.PricePlanDao;
import dao.RoomTypeDao;
import dao.SexDao;
import domain.CustomerClass;
import domain.PricePlan;
import domain.RoomType;
import domain.Sex;

@WebServlet("/addPricePlan")
public class AddPricePlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			SexDao sexDao = DaoFactory.createSexDao();
			List<Sex> sexList = sexDao.findAll();
			RoomTypeDao roomTypeDao = DaoFactory.createRoomTypeDao();
			List<RoomType> roomTypeList = roomTypeDao.findAll();
			CustomerClassDao customerClassDao = DaoFactory.createCustomerClassDao();
			List<CustomerClass> customerClassList = customerClassDao.findAll();
			
			request.setAttribute("sexList", sexList);
			request.setAttribute("roomTypeList", roomTypeList);
			request.setAttribute("customerClassList", customerClassList);
			
			request.getRequestDispatcher("/WEB-INF/view/addPricePlan.jsp").forward(request, response);

		} catch (Exception e) {
			throw new ServletException();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		try {
			SexDao sexDao = DaoFactory.createSexDao();
			List<Sex> sexList = sexDao.findAll();
			RoomTypeDao roomTypeDao = DaoFactory.createRoomTypeDao();
			List<RoomType> roomTypeList = roomTypeDao.findAll();
			CustomerClassDao customerClassDao = DaoFactory.createCustomerClassDao();
			List<CustomerClass> customerClassList = customerClassDao.findAll();
			request.setAttribute("sexList", sexList);
			request.setAttribute("roomTypeList", roomTypeList);
			request.setAttribute("customerClassList", customerClassList);
			
			
			String planName = request.getParameter("planName");
			String strPlanStart = request.getParameter("planStart");
			String strPlanEnd = request.getParameter("planEnd");
			String strStartTimeHour = request.getParameter("startTimeHour");
			String strStartTimeMinute = request.getParameter("startTimeMinute");
			String strEndTimeHour = request.getParameter("endTimeHour");
			String strEndTimeMinute = request.getParameter("endTimeMinute");
			String strBasicPrice = request.getParameter("basicPrice");
			String strBasicTimeHour = request.getParameter("basicTimeHour");
			String strBasicTimeMinute = request.getParameter("basicTimeMinute");
			String strAddPrice = request.getParameter("addPrice");
			String strAddTimeHour = request.getParameter("addTimeHour");
			String strAddTimeMinute = request.getParameter("addTimeMinute");
			String strScopeSetting = request.getParameter("scopeSetting");
			String strScopeRoomType = request.getParameter("scopeRoomType");
			String strScopeSex = request.getParameter("scopeSex");
			String strScopeCustomerClass = request.getParameter("scopeCustomerClass");
			String strMinAge =request.getParameter("minAge");
			String strMaxAge =request.getParameter("maxAge");
			String strForMonday = request.getParameter("forMonday");
			String strForTuesday = request.getParameter("forTuesday");
			String strForWednesday = request.getParameter("forWednesday");
			String strForThursday = request.getParameter("forThursday");
			String strForFriday = request.getParameter("forFriday");
			String strForSaturday = request.getParameter("forSaturday");
			String strForSunday = request.getParameter("forSunday");
			String strForHolidays = request.getParameter("forHolidays");
			
			request.setAttribute("planName",planName);
			request.setAttribute("planStart",strPlanStart);
			request.setAttribute("planEnd",strPlanEnd);
			request.setAttribute("startTimeHour",strStartTimeHour);
			request.setAttribute("startTimeMinute",strStartTimeMinute);
			request.setAttribute("endTimeHour",strEndTimeHour);
			request.setAttribute("endTimeMinute",strEndTimeMinute);
			request.setAttribute("basicPrice",strBasicPrice);
			request.setAttribute("basicTimeHour",strBasicTimeHour);
			request.setAttribute("basicTimeMinute",strBasicTimeMinute);
			request.setAttribute("addPrice",strAddPrice);
			request.setAttribute("addTimeHour",strAddTimeHour);
			request.setAttribute("addTimeMinute",strAddTimeMinute);
			request.setAttribute("scopeSetting",strScopeSetting);
			request.setAttribute("scopeRoomType",strScopeRoomType);
			request.setAttribute("scopeSex",strScopeSex);
			request.setAttribute("scopeCustomerClass",strScopeCustomerClass);
			request.setAttribute("minAge",strMinAge);
			request.setAttribute("maxAge",strMaxAge);
			request.setAttribute("forMonday",strForMonday);
			request.setAttribute("forTuesday",strForTuesday);
			request.setAttribute("forWednesday",strForWednesday);
			request.setAttribute("forThursday",strForThursday);
			request.setAttribute("forFriday",strForFriday);
			request.setAttribute("forSaturday",strForSaturday);
			request.setAttribute("forSunday",strForSunday);
			request.setAttribute("forHolidays",strForHolidays);
			
			boolean isError = false;
			Date planStart=null;
			Date planEnd=null;
			Integer startTimeHour=0;
			Integer startTimeMinute=0;
			Integer endTimeHour=0;
			Integer endTimeMinute=0;
			Integer basicPrice=0;
			Integer addPrice=0;
			Integer basicTimeHour=0;
			Integer basicTimeMinute=0;
			Integer addTimeHour=0;
			Integer addTimeMinute=0;
			Boolean scopeSetting=false;
			Integer scopeRoomType=0;
			Integer scopeSex=0;
			Integer scopeCustomerClass=0;
			Integer minAge=0;
			Integer maxAge=0;
			Boolean forMonday = false;
			Boolean forTuesday = false;
			Boolean forWednesday = false; 
			Boolean forThursday = false; 
			Boolean forFriday = false; 
			Boolean forSaturday = false; 
			Boolean forSunday = false; 
			Boolean forHolidays = false; 
//プラン名
			if (planName.isEmpty()) {
				request.setAttribute("planNameError", "名前が未入力です。");
				isError = true;
			}
			if (planName.length() > 20) {
				request.setAttribute("planNameError", "20文字以下で入力してください。");
				isError = true;
			}
//プラン適用期間
			if (!strPlanStart.isEmpty()) {
				try {
					SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
					planStart = sdFormat.parse(strPlanStart);
				} catch (ParseException e) {
					e.printStackTrace();
					request.setAttribute("planStartError", "開始日時が不正です。");
					isError = true;
				}
			} else {
				request.setAttribute("planStartError", "開始日時が未入力です。");
				isError = true;
			}
			if (!strPlanEnd.isEmpty()) {
				try {
					SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
					planEnd = sdFormat.parse(strPlanEnd);
				} catch (ParseException e) {
					e.printStackTrace();
					request.setAttribute("planEndError", "終了日時が不正です。");
					isError = true;
				}
			} else {
				request.setAttribute("planEndError", "終了日時が未入力です。");
				isError = true;
			}
//開始時間
			if (!strStartTimeHour.isEmpty()) {
				try {
					startTimeHour = Integer.parseInt(strStartTimeHour);
					if (startTimeHour < 0) {
						request.setAttribute("startTimeError", "開始時間が不正です。");
						isError = true;
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute("startTimeError", "開始時間が不正です。");
					isError = true;
				}
			} 
			if (!strStartTimeMinute.isEmpty()) {
				try {
					startTimeMinute = Integer.parseInt(strStartTimeMinute);
					if (startTimeMinute < 0) {
						request.setAttribute("startTimeError", "開始時間が不正です。");
						isError = true;
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute("startTimeError", "開始時間が不正です。");
					isError = true;
				}
			} 
			if(strStartTimeHour.isEmpty()||strStartTimeMinute.isEmpty()) {
				request.setAttribute("startTimeError", "開始時間が未入力です");
				isError = true;
			}
			Integer startTime= startTimeHour*100+startTimeMinute;
//終了時間
			if (!strEndTimeHour.isEmpty()) {
				try {
					endTimeHour = Integer.parseInt(strEndTimeHour);
					if (endTimeHour < 0) {
						request.setAttribute("endTimeError", "終了時間が不正です。");
						isError = true;
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute("endTimeError", "終了時間が不正です。");
					isError = true;
				}
			} 
			if (!strEndTimeMinute.isEmpty()) {
				try {
					endTimeMinute = Integer.parseInt(strEndTimeMinute);
					if (endTimeMinute < 0) {
						request.setAttribute("endTimeError", "終了時間が不正です。");
						isError = true;
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute("endTimeError", "終了時間が不正です。");
					isError = true;
				}
			} 
			if(strEndTimeHour.isEmpty()||strEndTimeMinute.isEmpty()) {
				request.setAttribute("endTimeError", "終了時間が未入力です");
				isError = true;
			}
			Integer endTime= endTimeHour*100+endTimeMinute;
			
			if((endTime-startTime)>=86400000) {
				request.setAttribute("startTimeError", "24時間未満で設定してください");
				request.setAttribute("endTimeError", "24時間未満で設定してください");
				isError = true;
			}
			
//基本料金
			if (strBasicPrice.isEmpty()) {
				request.setAttribute("basicPriceError", "基本料金が未入力です");
				isError = true;
			} else {
				try {
					basicPrice = Integer.parseInt(strBasicPrice);
					if (basicPrice < 0) {
						request.setAttribute("basicPriceError", "基本料金が不正です。");
						isError = true;
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute("basicPriceError", "基本料金が不正です。");
					isError = true;
				}
			}
			
			if (!strBasicTimeHour.isEmpty()) {
				try {
					basicTimeHour = Integer.parseInt(strBasicTimeHour);
					if (basicTimeHour < 0) {
						request.setAttribute("basicTimeError", "基本時間が不正です。");
						isError = true;
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute("basicTimeError", "基本時間が不正です。");
					isError = true;
				}
			} 
			if (!strBasicTimeMinute.isEmpty()) {
				try {
					basicTimeMinute = Integer.parseInt(strBasicTimeMinute);
					if (basicTimeMinute < 0) {
						request.setAttribute("basicTimeError", "基本時間が不正です。");
						isError = true;
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute("basicTimeError", "基本時間が不正です。");
					isError = true;
				}
			} 
			if(strBasicTimeHour.isEmpty()||strBasicTimeMinute.isEmpty()) {
				request.setAttribute("basicTimeError", "基本時間が未入力です");
				isError = true;
			}
			Integer basicTime= basicTimeHour*3600000+basicTimeMinute*60000;
//追加料金
			if (strAddPrice.isEmpty()) {
				request.setAttribute("addPriceError", "追加料金が未入力です");
				isError = true;
			} else {
				try {
					addPrice = Integer.parseInt(strAddPrice);
					if (addPrice < 0) {
						request.setAttribute("addPriceError", "追加料金が不正です。");
						isError = true;
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute("addPriceError", "追加料金が不正です。");
					isError = true;
				}
			}
			
			if (!strAddTimeHour.isEmpty()) {
				try {
					addTimeHour = Integer.parseInt(strAddTimeHour);
					if (addTimeHour < 0) {
						request.setAttribute("addTimeError", "追加時間が不正です。");
						isError = true;
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute("addTimeError", "追加時間が不正です。");
					isError = true;
				}
			} 
			if (!strAddTimeMinute.isEmpty()) {
				try {
					addTimeMinute = Integer.parseInt(strAddTimeMinute);
					if (addTimeMinute < 0) {
						request.setAttribute("addTimeError", "追加時間が不正です。");
						isError = true;
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute("addTimeError", "追加時間が不正です。");
					isError = true;
				}
			} 
			if(strAddTimeHour.isEmpty()||strAddTimeMinute.isEmpty()) {
				request.setAttribute("addTimeError", "追加時間が未入力です");
				isError = true;
			}
			Integer addTime= addTimeHour*3600000+addTimeMinute*60000;
			
			if(!(strScopeSetting==null)) {
				scopeSetting=true;
				
				if (strScopeRoomType.isEmpty()) {
					request.setAttribute("customerClassIdError", "部屋タイプが未入力です");
					isError = true;
				} else {
					try {
						scopeRoomType = Integer.parseInt(strScopeRoomType);
						if (scopeRoomType < 0) {
							request.setAttribute("scopeRoomTypeError", "部屋タイプが不正です。");
							isError = true;
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
						request.setAttribute("customerClassIdError", "部屋タイプが不正です。");
						isError = true;
					}
				}
				
				if (strScopeSex.isEmpty()) {
					request.setAttribute("customerClassIdError", "性別タイプが未入力です");
					isError = true;
				} else {
					try {
						scopeSex = Integer.parseInt(strScopeSex);
						if (scopeSex < 0) {
							request.setAttribute("scopeSexError", "性別タイプが不正です。");
							isError = true;
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
						request.setAttribute("customerClassIdError", "性別タイプが不正です。");
						isError = true;
					}
				}
				
				if (strScopeCustomerClass.isEmpty()) {
					request.setAttribute("customerClassIdError", "会員クラスが未入力です");
					isError = true;
				} else {
					try {
						scopeCustomerClass = Integer.parseInt(strScopeCustomerClass);
						if (scopeCustomerClass < 0) {
							request.setAttribute("scopeCustomerClassError", "会員クラスが不正です。");
							isError = true;
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
						request.setAttribute("customerClassIdError", "会員クラスが不正です。");
						isError = true;
					}
				}
				
				if (strMinAge.isEmpty()) {
					request.setAttribute("minAgeError", "最低年齢が未入力です");
					isError = true;
				} else {
					try {
						minAge = Integer.parseInt(strMinAge);
						if (minAge < 0) {
							request.setAttribute("minAgeError", "最低年齢が不正です。");
							isError = true;
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
						request.setAttribute("minAgeError", "最低年齢が不正です。");
						isError = true;
					}
				}
				
				if (strMaxAge.isEmpty()) {
					request.setAttribute("maxAgeError", "最高年齢が未入力です");
					isError = true;
				} else {
					try {
						maxAge = Integer.parseInt(strMaxAge);
						if (maxAge < 0) {
							request.setAttribute("maxAgeError", "最高年齢が不正です。");
							isError = true;
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
						request.setAttribute("maxAgeError", "最高年齢が不正です。");
						isError = true;
					}
				}
				
				if(maxAge<minAge) {
					request.setAttribute("minAgeError", "最低年齢が最高年齢より大きいです");
					request.setAttribute("maxAgeError", "年齢の範囲を修正してください。");
					isError = true;
				}
				
				
				if (!(strForMonday==null))forMonday=true;
				if (!(strForTuesday==null))forTuesday=true;
				if (!(strForWednesday==null))forWednesday=true;
				if (!(strForThursday==null))forThursday=true;
				if (!(strForFriday==null))forFriday=true;
				if (!(strForSaturday==null))forSaturday=true;
				if (!(strForSunday==null))forSunday=true;
				if (!(strForHolidays==null))forHolidays=true;
			}
			
			if (isError) {
				request.getRequestDispatcher("/WEB-INF/view/addPricePlan.jsp")
				.forward(request, response);
				return;
			}
			
			PricePlan pricePlan = new PricePlan();
			pricePlan.setPlanName(planName);
			pricePlan.setPlanStart(planStart);
			pricePlan.setPlanEnd(planEnd);
			pricePlan.setStartTime(startTime);
			pricePlan.setEndTime(endTime);
			pricePlan.setBasicPrice(basicPrice);
			pricePlan.setBasicTime(basicTime);
			pricePlan.setAddPrice(addPrice);
			pricePlan.setAddTime(addTime);
			pricePlan.setScopeSetting(scopeSetting);
			pricePlan.setScopeRoomType(scopeRoomType);
			pricePlan.setScopeSex(scopeSex);
			pricePlan.setScopeCustomerClass(scopeCustomerClass);
			pricePlan.setMinAge(minAge);
			pricePlan.setMaxAge(maxAge);
			pricePlan.setForMonday(forMonday);
			pricePlan.setForTuesday(forTuesday);
			pricePlan.setForWednesday(forWednesday);
			pricePlan.setForThursday(forThursday);
			pricePlan.setForFriday(forFriday);
			pricePlan.setForSaturday(forSaturday);
			pricePlan.setForSunday(forSunday);
			pricePlan.setForHolidays(forHolidays);
			
			

			PricePlanDao pricePlanDao = DaoFactory.createPricePlanDao();
			pricePlanDao.insert(pricePlan);
			
			response.sendRedirect("listPricePlan");
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
