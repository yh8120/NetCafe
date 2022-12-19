package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
					+ " LEFT OUTER JOIN master_tax_types ON price_plans.tax_type = master_tax_types.tax_type_id"
					+ " LEFT OUTER JOIN master_tax_rates ON price_plans.tax_type = master_tax_rates.tax_type";
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
	public List<PricePlan> findByNow() throws Exception {
		List<PricePlan> pricePlanList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM price_plans"
					+ " LEFT OUTER JOIN master_tax_types ON price_plans.tax_type = master_tax_types.tax_type_id"
					+ " LEFT OUTER JOIN master_tax_rates ON price_plans.tax_type = master_tax_rates.tax_type"
					+ " WHERE price_plans.plan_start <= NOW() AND price_plans.plan_end > NOW()"
					+ " AND master_tax_rates.tax_start <= NOW() AND master_tax_rates.tax_end > NOW()";
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
	public List<PricePlan> findByStartTime(Integer startTime) throws Exception {
		List<PricePlan> pricePlanList = new ArrayList<>();
		Integer timesOnDay =2400;
		Integer startTime2 = startTime+timesOnDay;

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM price_plans"
					+ " LEFT OUTER JOIN master_tax_types ON price_plans.tax_type = master_tax_types.tax_type_id"
					+ " LEFT OUTER JOIN master_tax_rates ON price_plans.tax_type = master_tax_rates.tax_type"
					+ " WHERE (price_plans.start_time <= ? AND price_plans.end_time > ?)"
					+ " OR (price_plans.start_time <= ? AND price_plans.end_time > ?)"
					+ " AND price_plans.plan_start <= NOW() AND price_plans.plan_end > NOW()"
					+ " AND master_tax_rates.tax_start <= NOW() AND master_tax_rates.tax_end > NOW()";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, startTime,Types.INTEGER);
			stmt.setObject(2, startTime,Types.INTEGER);
			stmt.setObject(3, startTime2,Types.INTEGER);
			stmt.setObject(4, startTime2,Types.INTEGER);
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
			String sql = "SELECT *"
					+ " FROM price_plans"
					+ " LEFT OUTER JOIN master_tax_types ON price_plans.tax_type = master_tax_types.tax_type_id"
					+ " LEFT OUTER JOIN master_tax_rates ON price_plans.tax_type = master_tax_rates.tax_type"
					+ " WHERE plan_id=?";
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
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO pricePlans"
					+ " (plan_name,"
					+ " plan_start,"
					+ " plan_end,"
					+ " start_time,"
					+ " end_time,"
					+ " basic_price,"
					+ " basic_time,"
					+ " add_price,"
					+ " add_time,"
					+ " tax_type,"
					+ " scope_all,"
					+ " scope_room_type,"
					+ " scope_sex,"
					+ " scope_customer_class,"
					+ " min_age,"
					+ " max_age,"
					+ " for_sunday,"
					+ " for_monday,"
					+ " for_tuesday,"
					+ " for_wednesday,"
					+ " for_thursday,"
					+ " for_friday,"
					+ " for_saturday,"
					+ " for_holidays)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, pricePlan.getPlanName());
			stmt.setObject(2, pricePlan.getPlanStart(), Types.DATE);
			stmt.setObject(3, pricePlan.getPlanEnd(), Types.DATE);
			stmt.setObject(4, pricePlan.getStartTime(), Types.INTEGER);
			stmt.setObject(5, pricePlan.getEndTime(), Types.INTEGER);
			stmt.setObject(6, pricePlan.getBasicPrice(), Types.INTEGER);
			stmt.setObject(7, pricePlan.getBasicTime(), Types.INTEGER);
			stmt.setObject(8, pricePlan.getAddPrice(), Types.INTEGER);
			stmt.setObject(9, pricePlan.getAddTime(), Types.INTEGER);
			stmt.setObject(10, pricePlan.getTaxTypeId(), Types.INTEGER);
			stmt.setBoolean(11, pricePlan.getScopeSetting());
			stmt.setObject(12, pricePlan.getScopeSex(), Types.INTEGER);
			stmt.setObject(13, pricePlan.getScopeCustomerClass(), Types.INTEGER);
			stmt.setObject(14, pricePlan.getMinAge(), Types.INTEGER);
			stmt.setObject(15, pricePlan.getMaxAge(), Types.INTEGER);
			stmt.setBoolean(16, pricePlan.getForSunday());
			stmt.setBoolean(17, pricePlan.getForMonday());
			stmt.setBoolean(18, pricePlan.getForTuesday());
			stmt.setBoolean(19, pricePlan.getForWednesday());
			stmt.setBoolean(20, pricePlan.getForThursday());
			stmt.setBoolean(21, pricePlan.getForFriday());
			stmt.setBoolean(22, pricePlan.getForSaturday());
			stmt.setBoolean(23, pricePlan.getForHolidays());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(PricePlan pricePlan) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE price_plans"
					+ " SET plan_name = ?,"
					+ " plan_start = ?,"
					+ " plan_end = ?,"
					+ " start_time = ?,"
					+ " end_time = ?,"
					+ " basic_price = ?,"
					+ " basic_time = ?,"
					+ " add_price = ?,"
					+ " add_time = ?,"
					+ " tax_type = ?,"
					+ " scope_all = ?,"
					+ " scope_room_type = ?,"
					+ " scope_sex = ?,"
					+ " scope_customer_class = ?,"
					+ " min_age = ?,"
					+ " max_age = ?,"
					+ " for_sunday = ?,"
					+ " for_monday = ?,"
					+ " for_tuesday = ?,"
					+ " for_wednesday = ?,"
					+ " for_thursday = ?,"
					+ " for_friday = ?,"
					+ " for_saturday = ?,"
					+ " for_holidays = ?"
					+ " WHERE plan_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, pricePlan.getPlanName());
			stmt.setObject(2, pricePlan.getPlanStart(), Types.DATE);
			stmt.setObject(3, pricePlan.getPlanEnd(), Types.DATE);
			stmt.setObject(4, pricePlan.getStartTime(), Types.INTEGER);
			stmt.setObject(5, pricePlan.getEndTime(), Types.INTEGER);
			stmt.setObject(6, pricePlan.getBasicPrice(), Types.INTEGER);
			stmt.setObject(7, pricePlan.getBasicTime(), Types.INTEGER);
			stmt.setObject(8, pricePlan.getAddPrice(), Types.INTEGER);
			stmt.setObject(9, pricePlan.getAddTime(), Types.INTEGER);
			stmt.setObject(10, pricePlan.getTaxTypeId(), Types.INTEGER);
			stmt.setBoolean(11, pricePlan.getScopeSetting());
			stmt.setObject(12, pricePlan.getScopeSex(), Types.INTEGER);
			stmt.setObject(13, pricePlan.getScopeCustomerClass(), Types.INTEGER);
			stmt.setObject(14, pricePlan.getMinAge(), Types.INTEGER);
			stmt.setObject(15, pricePlan.getMaxAge(), Types.INTEGER);
			stmt.setBoolean(16, pricePlan.getForSunday());
			stmt.setBoolean(17, pricePlan.getForMonday());
			stmt.setBoolean(18, pricePlan.getForTuesday());
			stmt.setBoolean(19, pricePlan.getForWednesday());
			stmt.setBoolean(20, pricePlan.getForThursday());
			stmt.setBoolean(21, pricePlan.getForFriday());
			stmt.setBoolean(22, pricePlan.getForSaturday());
			stmt.setBoolean(23, pricePlan.getForHolidays());
			stmt.setObject(24, pricePlan.getPlanId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void delete(PricePlan pricePlan) throws Exception {
				try (Connection con = ds.getConnection()) {
					String sql = "DELETE FROM price_plans"
							+ " WHERE plan_id = ?";
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setObject(1, pricePlan.getPlanId(), Types.INTEGER);
					stmt.executeUpdate();
				} catch (Exception e) {
					throw e;
				}

	}

	private PricePlan mapToPricePlan(ResultSet rs) throws Exception {

		PricePlan pricePlan = new PricePlan();
		pricePlan.setPlanId((Integer) rs.getObject("plan_id"));
		pricePlan.setPlanName(rs.getString("plan_name"));
		pricePlan.setPlanStart(rs.getTimestamp("plan_start"));
		pricePlan.setPlanEnd(rs.getTimestamp("plan_end"));
		pricePlan.setStartTime((Integer) rs.getObject("start_time"));
		pricePlan.setEndTime((Integer) rs.getObject("end_time"));
		pricePlan.setBasicPrice((Integer) rs.getObject("basic_price"));
		pricePlan.setBasicTime((Integer) rs.getObject("basic_time"));
		pricePlan.setAddPrice((Integer) rs.getObject("add_price"));
		pricePlan.setAddTime((Integer) rs.getObject("add_time"));
		pricePlan.setTaxTypeId((Integer) rs.getObject("tax_type"));
		pricePlan.setTaxTypeName(rs.getString("tax_type_name"));
		pricePlan.setTaxRate((Double) rs.getObject("tax_rate"));
		pricePlan.setScopeSetting(rs.getBoolean("scope_setting"));
		pricePlan.setScopeRoomType((Integer) rs.getObject("scope_room_type"));
		pricePlan.setScopeSex((Integer) rs.getObject("scope_sex"));
		pricePlan.setScopeCustomerClass((Integer) rs.getObject("scope_customer_class"));
		pricePlan.setMinAge((Integer) rs.getObject("min_age"));
		pricePlan.setMaxAge((Integer) rs.getObject("max_age"));
		pricePlan.setForSunday(rs.getBoolean("for_sunday"));
		pricePlan.setForSunday(rs.getBoolean("for_monday"));
		pricePlan.setForSunday(rs.getBoolean("for_tuesday"));
		pricePlan.setForSunday(rs.getBoolean("for_wednesday"));
		pricePlan.setForSunday(rs.getBoolean("for_thursday"));
		pricePlan.setForSunday(rs.getBoolean("for_friday"));
		pricePlan.setForSunday(rs.getBoolean("for_saturday"));
		pricePlan.setForSunday(rs.getBoolean("for_holidays"));

		return pricePlan;

	}

}
