package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import dao.PricePlanDao;
import dao.RoomDao;
import dao.RoomStatusDao;
import dao.ShopDao;
import dao.ShoppingCartDao;
import domain.PricePlan;
import domain.ReceiptData;
import domain.Room;
import domain.RoomUsedData;
import domain.Shop;
import domain.ShoppingCart;
import domain.TimeDisplay;
import domain.User;

@WebServlet("/checkOut")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer roomId = Integer.parseInt(request.getParameter("roomId"));
			RoomDao roomdao = DaoFactory.createRoomDao();
			Room room = roomdao.findById(roomId);

			// 買い物関係
			ShoppingCartDao shoppingCartDao = DaoFactory.createShoppingCartDao();
			List<ShoppingCart> shoppingCartList = shoppingCartDao.findByRoomId(roomId);
			Integer shoppingPrice = 0;
			Integer shoppingTax = 0;
			for (ShoppingCart shoppingCart : shoppingCartList) {
				shoppingPrice = shoppingPrice + shoppingCart.getTotalPrice();
				shoppingTax = shoppingTax + shoppingCart.getInnerTax();
			}

			Date startTime = room.getStartTime();
			SimpleDateFormat fmt = new SimpleDateFormat("Hmm");
			String strStartTime = fmt.format(startTime);
			Integer intStartTime = Integer.parseInt(strStartTime);

			//時計表示用
			Date checkOutTime = new Date();
			BigDecimal stayTime = new BigDecimal(checkOutTime.getTime() - startTime.getTime());
			String timeDisplay = TimeDisplay.timeDisplay(stayTime.longValue());

			//料金計算用
			PricePlanDao pricePlanDao = DaoFactory.createPricePlanDao();
			List<PricePlan> pricePlanList = pricePlanDao.findByStartTime(intStartTime);
			RoomUsedData roomUsedData = null;

			for (PricePlan plisePlan : pricePlanList) {
				RoomUsedData calcUsedData = planToUsedData(stayTime, plisePlan);
				if (roomUsedData == null) {
					roomUsedData = calcUsedData;
				}
				if (roomUsedData.getRoomPrice() > calcUsedData.getRoomPrice()) {
					roomUsedData = calcUsedData;
				}
			}

			Integer sumPrice = roomUsedData.getRoomPrice() + shoppingPrice;
			Integer sumTax = roomUsedData.getRoomTax() + shoppingTax;
			roomUsedData.setRoomId(roomId);
			roomUsedData.setRoomName(room.getRoomName());
			roomUsedData.setCustomerId(room.getCustomerId());
			roomUsedData.setCustomerName(room.getCustomerName());
			roomUsedData.setStartTime(startTime);
			roomUsedData.setCheckOutTime(checkOutTime);
			roomUsedData.setStayTime(stayTime.longValue());
			roomUsedData.setStartTime(startTime);

			ReceiptData receiptData = new ReceiptData();
			receiptData.setSumPrice(sumPrice);
			receiptData.setSumTax(sumTax);

			request.getSession().setAttribute("roomUsedData", roomUsedData);
			request.getSession().setAttribute("receiptData", receiptData);
			request.getSession().setAttribute("shoppingCartList", shoppingCartList);
			request.getSession().setAttribute("shoppingPrice", shoppingPrice);
			request.getSession().setAttribute("shoppingTax", shoppingTax);
			request.getSession().setAttribute("timeDisplay", timeDisplay);

			request.getRequestDispatcher("/WEB-INF/view/checkOut.jsp").forward(request, response);

		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			RoomUsedData roomUsedData = (RoomUsedData) session.getAttribute("roomUsedData");
			ReceiptData receiptData = (ReceiptData) session.getAttribute("receiptData");
			List<ShoppingCart> shoppingCartList = (List<ShoppingCart>) session.getAttribute("shoppingCartList");

			String strPayment = request.getParameter("payment");
			Integer roomId = Integer.parseInt(request.getParameter("roomId"));

			RoomDao roomdao = DaoFactory.createRoomDao();
			Room room = roomdao.findById(roomId);

			// バリデーション
			Integer sumPrice = receiptData.getSumPrice();
			Integer payment = 0;
			boolean isError = false;

			if (strPayment.isEmpty()) {
				request.setAttribute("paymentError", "預り金が未入力です");
				isError = true;
			} else {
				try {
					payment = Integer.parseInt(strPayment);
					if (payment <= 0) {
						request.setAttribute("paymentError", "預り金が不正です。");
						isError = true;
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute("paymentError", "預り金が不正です。");
					isError = true;
				}
			}
			if (sumPrice > payment) {
				request.setAttribute("paymentError", "預り金が足りません。");
				isError = true;
			}

			if (isError) {
				request.getRequestDispatcher("/WEB-INF/view/checkOut.jsp")
						.forward(request, response);
				return;
			}

			//　レシートデータ作成
			User user = (User) session.getAttribute("user");
			Integer shopId = user.getShopId();
			ShopDao shopDao = DaoFactory.createShopDao();
			Shop shop = shopDao.findById(user.getShopId());
			Date printedTime = new Date();

			receiptData.setShopId(shopId);
			receiptData.setUserId(user.getUserId());
			receiptData.setSumPrice(sumPrice);
			receiptData.setPayment(payment);
			receiptData.setPrintedTime(printedTime);
			Integer changeMoney = payment - sumPrice;
			receiptData.setChangeMoney(changeMoney);

			//レシート登録諸々のトランザクション処理

			RoomStatusDao roomStatus = DaoFactory.createRoomStatusDao();
			Integer receiptId = roomStatus.checkOut(room, roomUsedData, receiptData, shoppingCartList);
			receiptData.setReceiptId(receiptId);

			BigDecimal staytime = new BigDecimal(roomUsedData.getStayTime());
			String timeDisplay = TimeDisplay.timeDisplay(staytime.longValue());

			request.setAttribute("timeDisplay", timeDisplay);
			request.setAttribute("roomUsedData", roomUsedData);
			request.setAttribute("receiptData", receiptData);
			request.setAttribute("shoppingCartList", shoppingCartList);
			request.setAttribute("shop", shop);
			request.getSession().removeAttribute("roomUsedData");
			request.getSession().removeAttribute("receiptData");
			request.getSession().removeAttribute("shoppingCartList");
			request.getSession().removeAttribute("shoppingPrice");
			request.getSession().removeAttribute("shoppingTax");
			request.getSession().removeAttribute("timeDisplay");

			request.getRequestDispatcher("/WEB-INF/view/checkOutDone.jsp").forward(request, response);

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private RoomUsedData planToUsedData(BigDecimal stayTime, PricePlan pricePlan) {
		//料金計算用
		BigDecimal basicPrice = new BigDecimal(pricePlan.getBasicPrice());
		BigDecimal basicMS = new BigDecimal(pricePlan.getBasicTime());
		BigDecimal addPrice = new BigDecimal(pricePlan.getAddPrice());
		BigDecimal addMS = new BigDecimal(pricePlan.getAddTime());
		BigDecimal tax = new BigDecimal(pricePlan.getTaxRate());

		// 超過時間（＝利用時間－基本時間）
		// (利用時間が基本時間以下の場合超過時間は0)
		BigDecimal excMS = stayTime.subtract(basicMS);
		if (excMS.compareTo(BigDecimal.ZERO) == -1) {
			excMS = BigDecimal.ZERO;
		}
		// 追加料金発生回数（＝超過時間÷追加料金時間(切り上げ））
		BigDecimal timesOfAdded = excMS.divide(addMS, RoundingMode.UP);
		// 追加料金（＝追加料金発生時間×追加料金）
		BigDecimal excPrice = (timesOfAdded).multiply(addPrice);

		// 小計（＝基本料金＋(追加料金)）
		BigDecimal subtotal = basicPrice.add(excPrice);
		// 内消費税（＝小計×税率(四捨五入)）
		BigDecimal innerTax = subtotal.multiply(tax);
		innerTax = innerTax.setScale(0, RoundingMode.HALF_UP);
		// 合計（＝小計＋内消費税）
		BigDecimal sumPrice = subtotal.add(innerTax);

		RoomUsedData roomUsedData = new RoomUsedData();

		roomUsedData.setTaxType(pricePlan.getTaxTypeId());
		roomUsedData.setPlanId(pricePlan.getPlanId());
		roomUsedData.setPlanName(pricePlan.getPlanName());
		roomUsedData.setRoomTax(innerTax.intValue());
		roomUsedData.setRoomPrice(sumPrice.intValue());

		return roomUsedData;
	}

}
