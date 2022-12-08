package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.SalesData;

public class SalesDataDaoImpl implements SalesDataDao {
	private DataSource ds;

	public SalesDataDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<SalesData> findAll() throws Exception {
		List<SalesData> salesDataList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM sales_datas"
					+ " JOIN master_products ON sales_datas.product_id = master_product.product_id";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				salesDataList.add(mapToSalesData(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return salesDataList;
	}

	@Override
	public List<SalesData> findByReceiptId(Integer receiptId) throws Exception {
		List<SalesData> salesDataList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM sales_datas"
					+ " JOIN master_products ON sales_datas.product_id = master_product.product_id"
					+ " WHERE receipt_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, receiptId, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				salesDataList.add(mapToSalesData(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return salesDataList;
	}

	@Override
	public SalesData findById(Integer salesDataId) throws Exception {
		SalesData salesData = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM sales_datas"
					+ " JOIN master_products ON sales_datas.product_id = master_product.product_id"
					+ " WHERE sales_data_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, salesDataId, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				salesData = mapToSalesData(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return salesData;
	}

	@Override
	public void insert(SalesData salesData) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO sales_datas"
					+ " (receipt_id,product_id,number_product)"
					+ " VALUES (?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, salesData.getReceiptId(), Types.INTEGER);
			stmt.setObject(2, salesData.getProductId(), Types.INTEGER);
			stmt.setObject(3, salesData.getProductUnit(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(SalesData salesData) throws Exception {
		
	}

	@Override
	public void delete(SalesData salesData) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM sales_datas"
					+ " Where sales_data_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, salesData.getSalesDataId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	private SalesData mapToSalesData(ResultSet rs) throws Exception {
		SalesData salesData = new SalesData();
		salesData.setSalesDataId((Integer) rs.getObject("sales_data_id"));
		salesData.setReceiptId((Integer) rs.getObject("receipt_id"));
		salesData.setProductId((Integer) rs.getObject("product_id"));
		salesData.setProductName(rs.getString("product_name"));
		salesData.setProductPrice((Integer) rs.getObject("product_price"));
		salesData.setProductUnit((Integer) rs.getObject("number_product"));
		salesData.setDiscount((Integer) rs.getObject("discount"));

		return salesData;
	}

}
