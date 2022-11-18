package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Sex;

public class SexDaoImpl implements SexDao {
	private DataSource ds;

	public SexDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Sex> findAll() throws Exception {
		List<Sex> sexList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM sexes";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				sexList.add(mapToSex(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return sexList;
	}

	@Override
	public Sex findById(Integer id) throws Exception {
		Sex sex = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM sexes"
					+ " WHERE sex_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, id, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				sex = mapToSex(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return sex;
	}

	@Override
	public void insert(Sex sex) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO sexes"
					+ " (sex_id,sex_name)"
					+ " VALUES (?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, sex.getSexId(),Types.INTEGER);
			stmt.setString(2, sex.getSexName());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(Sex sex) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE sexes"
					+ " SET sex_id = ?, sex_name = ?"
					+ " WHERE sex_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, sex.getSexId(), Types.INTEGER);
			stmt.setString(2, sex.getSexName());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void delete(Sex sex) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM sexes"
					+ " Where sex_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, sex.getSexId(),Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	private Sex mapToSex(ResultSet rs) throws Exception {
		Sex sex = new Sex();
		sex.setSexId((Integer) rs.getObject("sex_id"));
		sex.setSexName(rs.getString("sex_name"));

		return sex;

	}

}
