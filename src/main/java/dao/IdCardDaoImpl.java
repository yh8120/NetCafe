package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.IdCard;

public class IdCardDaoImpl implements IdCardDao {
	private DataSource ds;

	public IdCardDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<IdCard> findAll() throws Exception {
		List<IdCard> idCardList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM id_cards";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				idCardList.add(mapToCard(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return idCardList;
	}

	@Override
	public IdCard findById(Integer cardId) throws Exception {
		IdCard idCard = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM id_cards"
					+ " WHERE card_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, cardId, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				idCard = mapToCard(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return idCard;
	}

	@Override
	public void insert(IdCard idCard) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO id_cadrs"
					+ " (card_id,card_name)"
					+ " VALUES (?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, idCard.getCardId(),Types.INTEGER);
			stmt.setString(2, idCard.getCardName());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(IdCard idCard) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE id_cadrs"
					+ " SET card_id = ?, card_name = ?"
					+ " WHERE card_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, idCard.getCardId(), Types.INTEGER);
			stmt.setString(2, idCard.getCardName());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void delete(IdCard idCard) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM id_cards"
					+ " Where card_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, idCard.getCardId(),Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	private IdCard mapToCard(ResultSet rs) throws Exception {
		IdCard idCard = new IdCard();
		idCard.setCardId((Integer) rs.getObject("card_id"));
		idCard.setCardName(rs.getString("card_name"));

		return idCard;

	}

}
