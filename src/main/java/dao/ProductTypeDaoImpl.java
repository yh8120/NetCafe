package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.ProductType;

public class ProductTypeDaoImpl implements ProductTypeDao {
	private DataSource ds;

	public ProductTypeDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<ProductType> findAll() throws Exception {
		List<ProductType> productTypeList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM product_types";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				productTypeList.add(mapToProductType(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return productTypeList;
	}

	@Override
	public ProductType findById(Integer id) throws Exception {
		ProductType productType = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM product_types"
					+ " WHERE product_type_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, id, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				productType = mapToProductType(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return productType;
	}

	@Override
	public void insert(ProductType productType) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO product_types"
					+ " (product_type_id,product_type_name)"
					+ " VALUES (?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, productType.getProductTypeId(),Types.INTEGER);
			stmt.setString(2, productType.getProductTypeName());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(ProductType productType) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE product_types"
					+ " SET product_type_id = ?, product_type_name = ?"
					+ " WHERE product_type_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, productType.getProductTypeId(), Types.INTEGER);
			stmt.setString(2, productType.getProductTypeName());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void delete(ProductType productType) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM product_types"
					+ " Where product_type_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, productType.getProductTypeId(),Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	private ProductType mapToProductType(ResultSet rs) throws Exception {
		ProductType productType = new ProductType();
		productType.setProductTypeId((Integer) rs.getObject("product_type_id"));
		productType.setProductTypeName(rs.getString("product_type_name"));
		productType.setTaxType((Integer)rs.getObject("tax_type"));

		return productType;

	}

}
