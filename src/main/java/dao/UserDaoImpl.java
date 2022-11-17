package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.mindrot.jbcrypt.BCrypt;

import domain.User;

public class UserDaoImpl implements UserDao {

	private DataSource ds;

	public UserDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<User> findAll() throws Exception {
		List<User> userList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM users"
					+ " JOIN user_classes"
					+ " ON users.user_class_id = user_classes.user_class_id";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				userList.add(mapToUser(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return userList;
	}

	@Override
	public User findById(Integer id) throws Exception {
		User user = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM users"
					+ " JOIN user_classes"
					+ " ON users.user_class_id = user_classes.user_class_id"
					+ " WHERE id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, id, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user = mapToUser(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return user;
	}

	@Override
	public void insert(User user) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO users"
					+ " (login_id, login_pass, name,user_class_id)"
					+ " VALUES ( ?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getLoginId());
			stmt.setString(2, user.getLoginPass());
			stmt.setString(3, user.getName());
			stmt.setObject(4, user.getUserClassId(),Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(User user) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE users"
					+ " SET login_id = ?, login_pass = ?, name = ?,user_class_id= ?"
					+ " WHERE id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getLoginId());
			stmt.setString(2, user.getLoginPass());
			stmt.setString(3, user.getName());
			stmt.setObject(4, user.getUserClassId(), Types.INTEGER);
			stmt.setObject(5, user.getId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void delete(User user) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM users"
					+ " WHERE id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, user.getId(),Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public User findByLoginIdAndLoginPass(String loginId, String loginPass) throws Exception {
		User user = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM users"
					+ " JOIN user_classes"
					+ " ON users.user_class_id = user_classes.user_class_id"
					+ " WHERE login_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, loginId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				if (BCrypt.checkpw(loginPass, rs.getString("login_pass"))) {
					user = mapToUser(rs);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return user;
	}
	
	private User mapToUser(ResultSet rs) throws Exception {
		User user = new User(); 
		 user.setId((Integer) rs.getObject("id")); 
		 user.setLoginId(rs.getString("login_id"));
		 user.setLoginPass(rs.getString("login_pass"));
		 user.setName(rs.getString("name"));
		 user.setUserClassId(Integer.parseInt(rs.getString("user_class_id")));
		 user.setUserClassName(rs.getString("user_class_name"));
		 return user; 
		 
	}

}
