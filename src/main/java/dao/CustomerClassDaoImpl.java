package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.CustomerClass;

public class CustomerClassDaoImpl implements CustomerClassDao {
	private DataSource ds;

	public CustomerClassDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<CustomerClass> findAll() throws Exception {
		List<CustomerClass> customerClassList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM customer_classes";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerClassList.add(mapToCustomerClass(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return customerClassList;
	}

	@Override
	public CustomerClass findById(Integer id) throws Exception {
		CustomerClass customerClass = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM customer_classes"
					+ " WHERE customer_class_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, id, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				customerClass = mapToCustomerClass(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return customerClass;
	}

	@Override
	public void insert(CustomerClass customerClass) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO customer_classes"
					+ " (customer_class_id,customer_class_name)"
					+ " VALUES (?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, customerClass.getCustomerClassId(),Types.INTEGER);
			stmt.setString(2, customerClass.getCustomerClassName());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(CustomerClass customerClass) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE customer_classes"
					+ " SET customer_class_id = ?, customer_class_name = ?"
					+ " WHERE customer_class_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, customerClass.getCustomerClassId(), Types.INTEGER);
			stmt.setString(2, customerClass.getCustomerClassName());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void delete(CustomerClass customerClass) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM customer_classes"
					+ " Where customer_class_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, customerClass.getCustomerClassId(),Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	private CustomerClass mapToCustomerClass(ResultSet rs) throws Exception {
		CustomerClass customerClass = new CustomerClass();
		customerClass.setCustomerClassId((Integer) rs.getObject("customer_class_id"));
		customerClass.setCustomerClassName(rs.getString("customer_class_name"));

		return customerClass;

	}

}
