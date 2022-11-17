package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.UserClass;

public class UserClassDaoImpl implements UserClassDao {
	private DataSource ds;

	public UserClassDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<UserClass> findAll() throws Exception {
		List<UserClass> userClassList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT"
					+ " user_classes.user_class_id, user_classes.user_class_name"
					+ " FROM user_classes";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				userClassList.add(mapToUserClass(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return userClassList;
	}

	@Override
	public UserClass findById(Integer id) throws Exception {
		UserClass userClass = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM user_classes"
					+ " WHERE user_class_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, id, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				userClass = mapToUserClass(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return userClass;
	}

	@Override
	public void insert(UserClass userClass) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO user_classes"
					+ " (user_class_id,user_class_name)"
					+ " VALUES (?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, userClass.getUserClassId(),Types.INTEGER);
			stmt.setString(2, userClass.getUserClassName());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(UserClass userClass) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE userClasss"
					+ " SET user_class_id = ?, user_class_name = ?"
					+ " WHERE user_class_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, userClass.getUserClassId(), Types.INTEGER);
			stmt.setString(2, userClass.getUserClassName());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void delete(UserClass userClass) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM userClasss"
					+ " Where user_class_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, userClass.getUserClassId(),Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	private UserClass mapToUserClass(ResultSet rs) throws Exception {
		UserClass userClass = new UserClass();
		userClass.setUserClassId((Integer) rs.getObject("user_class_id"));
		userClass.setUserClassName(rs.getString("user_class_name"));

		return userClass;

	}

}
