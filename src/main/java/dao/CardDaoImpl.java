package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Card;

public class CardDaoImpl implements CardDao {
	private DataSource ds;

	public CardDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Card> findAll() throws Exception {
		List<Card> cardList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM cards";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				cardList.add(mapToCard(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return cardList;
	}

	@Override
	public Card findById(Integer id) throws Exception {
		Card card = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM cads"
					+ " WHERE card_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, id, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				card = mapToCard(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return card;
	}

	@Override
	public void insert(Card card) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO cads"
					+ " (card_id,card_name)"
					+ " VALUES (?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, card.getCardId(),Types.INTEGER);
			stmt.setString(2, card.getCardName());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(Card card) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE cads"
					+ " SET card_id = ?, card_name = ?"
					+ " WHERE card_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, card.getCardId(), Types.INTEGER);
			stmt.setString(2, card.getCardName());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void delete(Card card) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM cads"
					+ " Where card_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, card.getCardId(),Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	private Card mapToCard(ResultSet rs) throws Exception {
		Card card = new Card();
		card.setCardId((Integer) rs.getObject("card_id"));
		card.setCardName(rs.getString("card_name"));

		return card;

	}

}
