package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
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
					+ " FROM customers"
					+ " JOIN customer_classes ON customers.customer_class_id = customer_classes.customer_class_id"
					+ " JOIN id_cards ON customers.card_id = id_cards.card_id"
					+ " JOIN sexes ON customers.sex_id = sexes.sex_id";
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
	public Customer findById(Integer customerId) throws Exception {
		Customer customer = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM customers"
					+ " JOIN customer_classes ON customers.customer_class_id = customer_classes.customer_class_id"
					+ " JOIN id_cards ON customers.card_id = id_cards.card_id"
					+ " JOIN sexes ON customers.sex_id = sexes.sex_id"
					+ " WHERE customer_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, customerId, Types.INTEGER);
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
			String sql = "INSERT INTO customers"
					+ " (customer_id,"
					+ " customer_class_id,"
					+ " last_name,"
					+ " first_name,"
					+ " last_kana,"
					+ " first_kana,"
					+ " sex_id,"
					+ " card_id,"
					+ " card_number,"
					+ " birthday,"
					+ " zipcode_post,"
					+ " zipcode_city,"
					+ " address_state,"
					+ " address_city,"
					+ " address_street,"
					+ " address_room,"
					+ " memo,"
					+ " phone_number_a = ?,"
					+ " phone_number_b = ?,"
					+ " phone_number_c = ?,"
					+ " email_username,"
					+ " email_domain,"
					+ " customer_regestered,"
					+ " customer_updated)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),NOW())";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setObject(1, customer.getCustomerId(), Types.INTEGER);
			stmt.setObject(2, customer.getCustomerClassId(), Types.INTEGER);
			stmt.setString(3, customer.getLastName());
			stmt.setString(4, customer.getFirstName());
			stmt.setString(5, customer.getLastKana());
			stmt.setString(6, customer.getFirstKana());
			stmt.setObject(7, customer.getSexId(), Types.INTEGER);
			stmt.setObject(8, customer.getCardId(), Types.INTEGER);
			stmt.setString(9, customer.getCardNumber());
			stmt.setObject(10, customer.getBirthday(),Types.DATE);
			stmt.setString(11, customer.getZipcodePost());
			stmt.setString(12, customer.getZipcodeCity());
			stmt.setString(13, customer.getAddressState());
			stmt.setString(14, customer.getAddressCity());
			stmt.setString(15, customer.getAddressStreet());
			stmt.setString(16, customer.getAddressRoom());
			stmt.setString(17, customer.getMemo());
			stmt.setString(18, customer.getPhoneNumberA());
			stmt.setString(19, customer.getPhoneNumberB());
			stmt.setString(20, customer.getPhoneNumberC());
			stmt.setString(21, customer.geteMailUserName());
			stmt.setString(22, customer.geteMailDomain());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(Customer customer) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE customers"
					+ " customer_class_id = ?,"
					+ " last_name = ?,"
					+ " first_name = ?,"
					+ " last_kana = ?,"
					+ " first_kana = ?,"
					+ " sex_id = ?,"
					+ " card_id = ?,"
					+ " card_number = ?,"
					+ " birthday = ?,"
					+ " zipcode_post = ?,"
					+ " zipcode_city = ?,"
					+ " address_state = ?,"
					+ " address_city = ?,"
					+ " address_street = ?,"
					+ " address_room = ?,"
					+ " memo = ?,"
					+ " phone_number_a = ?,"
					+ " phone_number_b = ?,"
					+ " phone_number_c = ?,"
					+ " email_username = ?,"
					+ " email_domain = ?,"
					+ " customer_updated = NOW()"
					+ " WHERE customer_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, customer.getCustomerClassId(), Types.INTEGER);
			stmt.setString(2, customer.getLastName());
			stmt.setString(3, customer.getFirstName());
			stmt.setString(4, customer.getLastKana());
			stmt.setString(5, customer.getFirstKana());
			stmt.setObject(6, customer.getSexId(), Types.INTEGER);
			stmt.setObject(7, customer.getCardId(), Types.INTEGER);
			stmt.setString(8, customer.getCardNumber());
			stmt.setObject(9, customer.getBirthday(),Types.DATE);
			stmt.setString(10, customer.getZipcodePost());
			stmt.setString(11, customer.getZipcodeCity());
			stmt.setString(12, customer.getAddressState());
			stmt.setString(13, customer.getAddressCity());
			stmt.setString(14, customer.getAddressStreet());
			stmt.setString(15, customer.getAddressRoom());
			stmt.setString(16, customer.getMemo());
			stmt.setString(17, customer.getPhoneNumberA());
			stmt.setString(18, customer.getPhoneNumberB());
			stmt.setString(19, customer.getPhoneNumberC());
			stmt.setString(20, customer.geteMailUserName());
			stmt.setString(21, customer.geteMailDomain());
			stmt.setObject(22, customer.getCustomerId(), Types.INTEGER);
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
		customer.setLastName(rs.getString("last_name"));
		customer.setFirstName(rs.getString("first_name"));
		customer.setLastKana(rs.getString("last_kana"));
		customer.setFirstKana(rs.getString("first_kana"));
		customer.setSexId((Integer) rs.getObject("sex_id"));
		customer.setSexName(rs.getString("sex_name"));
		customer.setCardId((Integer) rs.getObject("card_id"));
		customer.setCardName(rs.getString("card_name"));
		customer.setCanCopyNumber(rs.getBoolean("can_copy_number"));
		customer.setCardNumber(rs.getString("card_number"));
		customer.setBirthday(rs.getTimestamp("birthday"));
		customer.setZipcodePost(rs.getString("zipcode_post"));
		customer.setZipcodeCity(rs.getString("zipcode_city"));
		customer.setAddressState(rs.getString("address_state"));
		customer.setAddressCity(rs.getString("address_city"));
		customer.setAddressStreet(rs.getString("address_street"));
		customer.setAddressRoom(rs.getString("address_room"));
		customer.setMemo(rs.getString("memo"));
		customer.setPhoneNumberA(rs.getString("phone_number_a"));
		customer.setPhoneNumberB(rs.getString("phone_number_b"));
		customer.setPhoneNumberC(rs.getString("phone_number_c"));
		customer.seteMailUserName(rs.getString("email_username"));
		customer.seteMailDomain(rs.getString("email_domain"));
		customer.setRegestered(rs.getTimestamp("regestered"));
		customer.setUpdated(rs.getTimestamp("updated"));
		return customer;

	}

}
