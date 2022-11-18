package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import domain.Customer;

public class CustomerDaoImpl implements CustomerDao {
	private DataSource ds;

	public CustomerDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Customer> findAll() throws Exception {
		List<Customer> customerList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " sexes.sex_name as customer_sex_name"
					+ " cards.card_name as customer_card_name"
					+ " FROM customers"
					+ " JOIN customer_classes ON customers.customer_class_id = customer_classes.customer_class_id"
					+ " JOIN cards ON customers.customer_card_id = cards.card_id"
					+ " JOIN sexes ON customers.customer_sex_id = sexes.sex_id";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customerList.add(mapToCustomer(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return customerList;
	}

	@Override
	public Customer findById(Integer id) throws Exception {
		Customer customer = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " sexes.sex_name as customer_sex_name"
					+ " cards.card_name as customer_card_name"
					+ " FROM customers"
					+ " JOIN customer_classes ON customers.customer_class_id = customer_classes.customer_class_id"
					+ " JOIN cards ON customers.customer_card_id = cards.card_id"
					+ " JOIN sexes ON customers.customer_sex_id = sexes.sex_id"
					+ " WHERE customer_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, id, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				customer = mapToCustomer(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return customer;
	}

	@Override
	public void insert(Customer customer) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO customers (customer_id, customer_class_id, customer_name, customer_sex_id, customer_card_id, customer_card_number, customer_birthday, customer_zipcode, customer_address, customer_memo, customer_phone, customer_mail, customer_regestered, customer_updated) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,NOW(),NOW())";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setObject(1, customer.getCustomerId(), Types.INTEGER);
			stmt.setObject(2, customer.getCustomerClassId(), Types.INTEGER);
			stmt.setString(3, customer.getCustomerName());
			stmt.setObject(4, customer.getCustomerSexId(), Types.INTEGER);
			stmt.setObject(5, customer.getCustomerCardId(), Types.INTEGER);
			stmt.setString(6, customer.getCustomerCardNumber());
			stmt.setObject(7, customer.getCustomerBirthday(),Types.DATE);
			stmt.setObject(8, customer.getCustomerZipcode(), Types.INTEGER);
			stmt.setString(9, customer.getCustomerAddress());
			stmt.setString(10, customer.getCustomerMemo());
			stmt.setString(11, customer.getCustomerPhone());
			stmt.setString(12, customer.getCustomerMail());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(Customer customer) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE customers"
					+ " (SET customer_class_id=?)"
					+ " (customer_name=?,customer_sex_id=?)"
					+ " (customer_card_id=?,customer_card_number=?)"
					+ " (customer_birthday=?,customer_zipcode=?)"
					+ " (customer_address=?,customer_memo=?)"
					+ " (customer_phone=?,customer_mail=?)"
					+ " (customer_regetered=?,customer_updated=NOW())"
					+ " WHERE customer_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, customer.getCustomerClassId(), Types.INTEGER);
			stmt.setString(2, customer.getCustomerName());
			stmt.setObject(3, customer.getCustomerSexId(), Types.INTEGER);
			stmt.setObject(4, customer.getCustomerCardId(), Types.INTEGER);
			stmt.setString(5, customer.getCustomerCardNumber());
			stmt.setObject(6, customer.getCustomerBirthday(),Types.DATE);
			stmt.setObject(7, customer.getCustomerZipcode(), Types.INTEGER);
			stmt.setString(8, customer.getCustomerAddress());
			stmt.setString(9, customer.getCustomerMemo());
			stmt.setString(10, customer.getCustomerPhone());
			stmt.setString(11, customer.getCustomerMail());
			stmt.setObject(12, customer.getCustomerBirthday(),Types.DATE);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void delete(Customer customer) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM customers"
					+ " Where customer_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, customer.getCustomerId(),Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	private Customer mapToCustomer(ResultSet rs) throws Exception {
		Customer customer = new Customer();
		customer.setCustomerId((Integer) rs.getObject("customer_id"));
		customer.setCustomerClassId((Integer) rs.getObject("customer_class_id"));
		customer.setCustomerName(rs.getString("customer_name"));
		customer.setCustomerSexId((Integer) rs.getObject("customer_sex_id"));
		customer.setCustomerCardId((Integer) rs.getObject("customer_card_id"));
		customer.setCustomerCardNumber(rs.getString("customer_card_number"));
		customer.setCustomerBirthday((Date) rs.getObject("customer_birthday"));
		customer.setCustomerZipcode((Integer) rs.getObject("customer_zipcode"));
		customer.setCustomerAddress(rs.getString("customer_address"));
		customer.setCustomerMemo(rs.getString("customer_memo"));
		customer.setCustomerPhone(rs.getString("customer_phone"));
		customer.setCustomerMail(rs.getString("customer_mail"));
		customer.setCustomerRegestered((Date) rs.getObject("customer_regestered"));
		customer.setCustomerUpdated((Date) rs.getObject("customer_updated"));
		customer.setCustomerClassName(rs.getString("customer_class_name"));
		customer.setCustomerSexName(rs.getString("customer_sex_name"));
		customer.setCustomerCardName(rs.getString("customer_card_name"));
		return customer;

	}

}
