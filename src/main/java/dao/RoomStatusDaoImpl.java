package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.ReceiptData;
import domain.Room;
import domain.RoomStatus;
import domain.RoomUsedData;
import domain.ShoppingCart;

public class RoomStatusDaoImpl implements RoomStatusDao {
	private DataSource ds;

	public RoomStatusDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<RoomStatus> findAll() throws Exception {
		List<RoomStatus> roomStatusList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM sales_data";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				roomStatusList.add(mapToRoomStatus(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return roomStatusList;
	}

	@Override
	public List<RoomStatus> findbyRoomId(Integer roomId) throws Exception {
		List<RoomStatus> roomStatusList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM room_status"
					+ " WHERE room_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomId, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				roomStatusList.add(mapToRoomStatus(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return roomStatusList;
	}

	@Override
	public RoomStatus findById(Integer roomId) throws Exception {
		RoomStatus roomStatus = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM room_status"
					+ " WHERE room_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomId, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				roomStatus = mapToRoomStatus(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return roomStatus;
	}

	@Override
	public RoomStatus checkCurrentUser(Integer customerId) throws Exception {
		RoomStatus roomStatus = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM room_status"
					+ " WHERE in_use=true AND customer_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, customerId, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				roomStatus = mapToRoomStatus(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return roomStatus;
	}

	@Override
	public void marge(RoomStatus tempReciept) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM room_status"
					+ " WHERE room_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, tempReciept.getRoomId(), Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				update(tempReciept);
			} else {
				insert(tempReciept);
			}
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void insert(RoomStatus roomStatus) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO room_status"
					+ "(room_id,"//1
					+ " customer_id,"//2
					+ " customer_name,"//3
					+ " start_time,"//4
					+ " losst_time,"//5
					+ " room_discount,"//6
					+ " room_uuid,"//7
					+ " in_use)"//8
					+ " VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomStatus.getRoomId(), Types.INTEGER);
			stmt.setObject(2, roomStatus.getCustomerId(), Types.INTEGER);
			stmt.setString(3, roomStatus.getCustomerName());
			stmt.setObject(4, roomStatus.getStartTime(), Types.TIMESTAMP);
			stmt.setObject(5, roomStatus.getLosstTime(), Types.INTEGER);
			stmt.setObject(6, roomStatus.getRoomDiscount(), Types.INTEGER);
			stmt.setString(7, roomStatus.getRoomUuid());
			stmt.setBoolean(8, roomStatus.getInUse());
			stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(RoomStatus roomStatus) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE room_status"
					+ " customer_id,"//1
					+ " customer_name,"//2
					+ " start_time,"//3
					+ " losst_time,"//4
					+ " room_discount,"//5
					+ " room_uuid,"//6
					+ " in_use)"//7
					+ " WHERE room_id=?";//8
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomStatus.getCustomerId(), Types.INTEGER);
			stmt.setString(2, roomStatus.getCustomerName());
			stmt.setObject(3, roomStatus.getStartTime(), Types.TIMESTAMP);
			stmt.setObject(4, roomStatus.getLosstTime(), Types.BIGINT);
			stmt.setString(5, roomStatus.getRoomUuid());
			stmt.setObject(6, roomStatus.getRoomDiscount(), Types.INTEGER);
			stmt.setBoolean(7, roomStatus.getInUse());
			stmt.setObject(8, roomStatus.getRoomId(), Types.INTEGER);
			stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		}
	}


	@Override
	public void checkIn(RoomStatus roomStatus) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO room_status"
					+ " (room_id,customer_id,customer_name,start_time,in_use)"
					+ " VALUES(?,?,?,NOW(),?)"
					+ " ON DUPLICATE KEY UPDATE"
					+ " room_id = VALUES (room_id),"
					+ " customer_id = VALUES (customer_id),"
					+ " customer_name = VALUES (customer_name),"
					+ " start_time = VALUES (start_time),"
					+ " in_use = VALUES (in_use)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomStatus.getRoomId(), Types.INTEGER);
			stmt.setObject(2, roomStatus.getCustomerId(), Types.INTEGER);
			stmt.setString(3, roomStatus.getCustomerName());
			stmt.setBoolean(4, true);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Integer checkOut(Room room, RoomUsedData roomUsedData, ReceiptData receiptData,List<ShoppingCart> shoppingCartList)
			throws Exception {
		Integer autoIncrementKey = null;
		try (Connection con = ds.getConnection()) {
			con.setAutoCommit(false);
			try {
				String sql = "INSERT INTO receipt_data"
						+ "(shop_id,"
						+ " user_id,"
						+ " printed_time,"
						+ " sum_price,"
						+ " sum_tax,"
						+ " payment,"
						+ " change_money)"
						+ " VALUES (?,?,NOW(),?,?,?,?)";
				PreparedStatement stmt = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
				stmt.setObject(1, receiptData.getShopId(), Types.INTEGER);
				stmt.setObject(2, receiptData.getUserId(), Types.INTEGER);
				stmt.setObject(3, receiptData.getSumPrice(), Types.INTEGER);
				stmt.setObject(4, receiptData.getSumTax(), Types.INTEGER);
				stmt.setObject(5, receiptData.getPayment(), Types.INTEGER);
				stmt.setObject(6, receiptData.getChangeMoney(), Types.INTEGER);
				stmt.executeUpdate();

				ResultSet res = stmt.getGeneratedKeys();

				if (res.next()) {
					autoIncrementKey = res.getInt(1);
					System.out.println(autoIncrementKey);

					sql = "INSERT INTO room_used_data"
							+ "(receipt_id,"//1
							+ " room_id,"//2
							+ " customer_id,"//3
							+ " start_time,"//4
							+ " checkout_time,"//5
							+ " stay_time,"//6
							+ " losst_time,"//7
							+ " plan_id,"//8
							+ " room_price,"//9
							+ " room_discount,"//10
							+ " room_total_price,"//11
							+ " room_tax)"//12
							+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
					stmt = con.prepareStatement(sql);
					stmt.setObject(1, autoIncrementKey);
					stmt.setObject(2, roomUsedData.getRoomId(), Types.INTEGER);
					stmt.setObject(3, roomUsedData.getCustomerId(), Types.INTEGER);
					stmt.setObject(4, roomUsedData.getStartTime(), Types.TIMESTAMP);
					stmt.setObject(5, roomUsedData.getCheckOutTime(), Types.TIMESTAMP);
					stmt.setObject(6, roomUsedData.getStayTime(), Types.BIGINT);
					stmt.setObject(7, roomUsedData.getLosstTime(), Types.INTEGER);
					stmt.setObject(8, roomUsedData.getPlanId(), Types.INTEGER);
					stmt.setObject(9, roomUsedData.getRoomPrice(), Types.INTEGER);
					stmt.setObject(10, roomUsedData.getRoomDiscount(), Types.INTEGER);
					stmt.setObject(11, roomUsedData.getRoomTotalPrice(), Types.INTEGER);
					stmt.setObject(12, roomUsedData.getRoomTax(), Types.INTEGER);
					stmt.executeUpdate();
					
					
					
					for (ShoppingCart shoppingCart : shoppingCartList) {
						sql = "INSERT INTO sales_data"
								+ " (receipt_id, "
								+ " sales_time,"
								+ " product_id,"
								+ " product_unit,"
								+ " total_price,"
								+ " discount,"
								+ " inner_tax)"
								+ " VALUES (?,?,?,?,?,?,?)";
						stmt = con.prepareStatement(sql);
						stmt.setObject(1, autoIncrementKey);
						stmt.setObject(2, shoppingCart.getSalesTime(),Types.TIMESTAMP);
						stmt.setObject(3, shoppingCart.getProductId(), Types.INTEGER);
						stmt.setObject(4, shoppingCart.getProductUnit(), Types.INTEGER);
						stmt.setObject(5, shoppingCart.getTotalPrice(), Types.INTEGER);
						stmt.setObject(6, shoppingCart.getDiscount(), Types.INTEGER);
						stmt.setObject(7, shoppingCart.getInnerTax(), Types.INTEGER);
						stmt.executeUpdate();
					}
					
					sql = "DELETE FROM room_status"
							+ " Where room_id = ?";
					stmt = con.prepareStatement(sql);
					stmt.setObject(1, room.getRoomId(), Types.INTEGER);
					stmt.executeUpdate();
					
					sql = "DELETE FROM shopping_carts"
							+ " Where room_id = ?";
					stmt = con.prepareStatement(sql);
					stmt.setObject(1, room.getRoomId(), Types.INTEGER);
					stmt.executeUpdate();
					
					sql = "UPDATE rooms"
							+ " SET cleaning_status = 2"
							+ " WHERE room_id = ?";
					stmt = con.prepareStatement(sql);
					stmt.setObject(1, room.getRoomId(), Types.INTEGER);
					stmt.executeUpdate();

				} else {
					con.rollback();
				}
				
				con.commit();
			} catch (Exception e) {
				con.rollback();
				throw e;
			}

		} catch (Exception e) {
			throw e;
		}
		return autoIncrementKey;

	}

	@Override
	public void delete(RoomStatus roomStatus) throws Exception {
		//		try (Connection con = ds.getConnection()) {
		//			String sql = "DELETE FROM sales_data"
		//					+ " Where receipt_id = ?";
		//			PreparedStatement stmt = con.prepareStatement(sql);
		//			stmt.setObject(1, roomStatus.getReceiptId(), Types.INTEGER);
		//			stmt.executeUpdate();
		//		} catch (Exception e) {
		//			throw e;
		//		}

	}

	private RoomStatus mapToRoomStatus(ResultSet rs) throws Exception {
		RoomStatus roomStatus = new RoomStatus();

		roomStatus.setRoomUsageId((Integer) rs.getObject("room_usage_id"));
		roomStatus.setRoomId((Integer) rs.getObject("room_id"));
		roomStatus.setCustomerId((Integer) rs.getObject("customer_id"));
		roomStatus.setCustomerName(rs.getString("customer_name"));
		roomStatus.setStartTime(rs.getTimestamp("start_time"));
		roomStatus.setLosstTime((Long)rs.getObject("losstTime"));
		roomStatus.setRoomDiscount((Integer) rs.getObject("room_discount"));
		roomStatus.setRoomUuid(rs.getString("room_uuid"));
		roomStatus.setInUse(rs.getBoolean("in_use"));

		return roomStatus;
	}

}
