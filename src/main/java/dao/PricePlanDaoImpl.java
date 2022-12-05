package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.PricePlan;

public class PricePlanDaoImpl implements PricePlanDao {
	private DataSource ds;

	public PricePlanDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<PricePlan> findAll() throws Exception {
		List<PricePlan> pricePlanList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM price_plans"
					+ " LEFT OUTER JOIN price_plan_scopes ON price_plans.plan_scope = price_plan_scopes.scope_id AS scope_id"
					+ " LEFT OUTER JOIN master_tax_types ON price_plans.tax_type = master_tax_types.tax_type_id AS tax_type_id"
					+ " LEFT OUTER JOIN master_tax_rates ON price_plans.tax_type = master_tax_rates.tax_type_id AS tax_type_id";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				pricePlanList.add(mapToPricePlan(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return pricePlanList;
	}
	
	@Override
	public List<PricePlan> findByNow(Time time) throws Exception {
		List<PricePlan> pricePlanList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM price_plans"
					+ " LEFT OUTER JOIN price_plan_scopes ON price_plans.plan_scope = price_plan_scopes.scope_id"
					+ " LEFT OUTER JOIN master_tax_types ON price_plans.tax_type = master_tax_types.tax_type_id"
					+ " LEFT OUTER JOIN master_tax_rates ON price_plans.tax_type = master_tax_rates.tax_type"
					+ " WHERE price_plans.start_time <= ? AND price_plans.end_time > ?"
					+ " AND price_plans.plan_start <= NOW() AND price_plans.plan_end > NOW()"
					+ " AND master_tax_rates.tax_start <= NOW() AND master_tax_rates.tax_end > NOW()";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setTime(1, time);
			stmt.setTime(2, time);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				pricePlanList.add(mapToPricePlan(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return pricePlanList;
	}

	@Override
	public PricePlan findById(Integer pricePlanId) throws Exception {
		PricePlan pricePlan = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM pricePlans"
					+ " JOIN pricePlan_classes ON pricePlans.pricePlan_class_id = pricePlan_classes.pricePlan_class_id"
					+ " JOIN id_cards ON pricePlans.card_id = id_cards.card_id"
					+ " JOIN sexes ON pricePlans.sex_id = sexes.sex_id"
					+ " WHERE pricePlan_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, pricePlanId, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				pricePlan = mapToPricePlan(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return pricePlan;
	}

	@Override
	public void insert(PricePlan pricePlan) throws Exception {
//		try (Connection con = ds.getConnection()) {
//			String sql = "INSERT INTO pricePlans"
//					+ " (pricePlan_id,"
//					+ " pricePlan_class_id,"
//					+ " last_name,"
//					+ " first_name,"
//					+ " last_kana,"
//					+ " first_kana,"
//					+ " sex_id,"
//					+ " card_id,"
//					+ " card_number,"
//					+ " birthday,"
//					+ " zipcode_post,"
//					+ " zipcode_city,"
//					+ " address_state,"
//					+ " address_city,"
//					+ " address_street,"
//					+ " address_room,"
//					+ " memo,"
//					+ " phone_number_a,"
//					+ " phone_number_b,"
//					+ " phone_number_c,"
//					+ " email_username,"
//					+ " email_domain,"
//					+ " regestered,"
//					+ " updated)"
//					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),NOW())";
//			PreparedStatement stmt = con.prepareStatement(sql);
//
//			stmt.setObject(1, pricePlan.getPricePlanId(), Types.INTEGER);
//			stmt.setObject(2, pricePlan.getPricePlanClassId(), Types.INTEGER);
//			stmt.setString(3, pricePlan.getLastName());
//			stmt.setString(4, pricePlan.getFirstName());
//			stmt.setString(5, pricePlan.getLastKana());
//			stmt.setString(6, pricePlan.getFirstKana());
//			stmt.setObject(7, pricePlan.getSexId(), Types.INTEGER);
//			stmt.setObject(8, pricePlan.getCardId(), Types.INTEGER);
//			stmt.setString(9, pricePlan.getCardNumber());
//			stmt.setObject(10, pricePlan.getBirthday(), Types.DATE);
//			stmt.setString(11, pricePlan.getZipcodePost());
//			stmt.setString(12, pricePlan.getZipcodeCity());
//			stmt.setString(13, pricePlan.getAddressState());
//			stmt.setString(14, pricePlan.getAddressCity());
//			stmt.setString(15, pricePlan.getAddressStreet());
//			stmt.setString(16, pricePlan.getAddressRoom());
//			stmt.setString(17, pricePlan.getMemo());
//			stmt.setString(18, pricePlan.getPhoneNumberA());
//			stmt.setString(19, pricePlan.getPhoneNumberB());
//			stmt.setString(20, pricePlan.getPhoneNumberC());
//			stmt.setString(21, pricePlan.geteMailUserName());
//			stmt.setString(22, pricePlan.geteMailDomain());
//			stmt.executeUpdate();
//		} catch (Exception e) {
//			throw e;
//		}

	}
	
	

	@Override
	public void update(PricePlan pricePlan) throws Exception {
//		try (Connection con = ds.getConnection()) {
//			String sql = "UPDATE pricePlans"
//					+ " SET pricePlan_class_id = ?,"
//					+ " last_name = ?,"
//					+ " first_name = ?,"
//					+ " last_kana = ?,"
//					+ " first_kana = ?,"
//					+ " sex_id = ?,"
//					+ " card_id = ?,"
//					+ " card_number = ?,"
//					+ " birthday = ?,"
//					+ " zipcode_post = ?,"
//					+ " zipcode_city = ?,"
//					+ " address_state = ?,"
//					+ " address_city = ?,"
//					+ " address_street = ?,"
//					+ " address_room = ?,"
//					+ " memo = ?,"
//					+ " phone_number_a = ?,"
//					+ " phone_number_b = ?,"
//					+ " phone_number_c = ?,"
//					+ " email_username = ?,"
//					+ " email_domain = ?,"
//					+ " updated = NOW()"
//					+ " WHERE pricePlan_id = ?";
//			PreparedStatement stmt = con.prepareStatement(sql);
//			stmt.setObject(1, pricePlan.getPricePlanClassId(), Types.INTEGER);
//			stmt.setString(2, pricePlan.getLastName());
//			stmt.setString(3, pricePlan.getFirstName());
//			stmt.setString(4, pricePlan.getLastKana());
//			stmt.setString(5, pricePlan.getFirstKana());
//			stmt.setObject(6, pricePlan.getSexId(), Types.INTEGER);
//			stmt.setObject(7, pricePlan.getCardId(), Types.INTEGER);
//			stmt.setString(8, pricePlan.getCardNumber());
//			stmt.setObject(9, pricePlan.getBirthday(), Types.DATE);
//			stmt.setString(10, pricePlan.getZipcodePost());
//			stmt.setString(11, pricePlan.getZipcodeCity());
//			stmt.setString(12, pricePlan.getAddressState());
//			stmt.setString(13, pricePlan.getAddressCity());
//			stmt.setString(14, pricePlan.getAddressStreet());
//			stmt.setString(15, pricePlan.getAddressRoom());
//			stmt.setString(16, pricePlan.getMemo());
//			stmt.setString(17, pricePlan.getPhoneNumberA());
//			stmt.setString(18, pricePlan.getPhoneNumberB());
//			stmt.setString(19, pricePlan.getPhoneNumberC());
//			stmt.setString(20, pricePlan.geteMailUserName());
//			stmt.setString(21, pricePlan.geteMailDomain());
//			stmt.setObject(22, pricePlan.getPricePlanId(), Types.INTEGER);
//			System.out.println(stmt);
//			stmt.executeUpdate();
//		} catch (Exception e) {
//			throw e;
//		}

	}

	@Override
	public void delete(PricePlan pricePlan) throws Exception {
//		try (Connection con = ds.getConnection()) {
//			String sql = "DELETE FROM pricePlans"
//					+ " Where pricePlan_id = ?";
//			PreparedStatement stmt = con.prepareStatement(sql);
//			stmt.setObject(1, pricePlan.getPricePlanId(), Types.INTEGER);
//			stmt.executeUpdate();
//		} catch (Exception e) {
//			throw e;
//		}

	}

	private PricePlan mapToPricePlan(ResultSet rs) throws Exception {
		
		PricePlan pricePlan = new PricePlan();
		pricePlan.setPlanId((Integer) rs.getObject("plan_id"));
		pricePlan.setPlanStart(rs.getTimestamp("plan_start"));
		pricePlan.setPlanEnd(rs.getTimestamp("plan_end"));
		pricePlan.setStartTime(rs.getString("start_time"));
		pricePlan.setEndTime(rs.getString("end_time"));
		pricePlan.setBasicPrice((Integer) rs.getObject("basic_price"));
		pricePlan.setBasicTime((Integer) rs.getObject("basic_time"));
		pricePlan.setAddPrice((Integer) rs.getObject("add_price"));
		pricePlan.setAddTime((Integer) rs.getObject("add_time"));
//		pricePlan.setPlanScope(rs.getString("card_name"));
		pricePlan.setTaxTypeId((Integer) rs.getObject("tax_type_id"));
		pricePlan.setTaxTypeName(rs.getString("tax_type_name"));
		pricePlan.setTaxRate((Double) rs.getObject("tax_rate"));
		
		
		return pricePlan;

	}

	

}
