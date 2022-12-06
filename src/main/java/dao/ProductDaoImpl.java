package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Product;

public class ProductDaoImpl implements ProductDao {
	private DataSource ds;

	public ProductDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Product> findAll() throws Exception {
		List<Product> productList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM master_products"
					+ " LEFT OUTER JOIN product_types ON master_products.product_type = product_types.product_type_id"
					+ " LEFT OUTER JOIN master_tax_types ON product_types.tax_type = master_tax_types.tax_type_id"
					+ " LEFT OUTER JOIN master_tax_rates ON product_types.tax_type = master_tax_rates.tax_type"
					+ " WHERE master_tax_rates.tax_start <= NOW() AND master_tax_rates.tax_end > NOW()";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				productList.add(mapToProduct(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return productList;
	}
	
	@Override
	public List<Product> findShoppingList() throws Exception {
		List<Product> productList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM master_products"
					+ " LEFT OUTER JOIN product_types ON master_products.product_type = product_types.product_type_id"
					+ " LEFT OUTER JOIN master_tax_types ON product_types.tax_type = master_tax_types.tax_type_id"
					+ " LEFT OUTER JOIN master_tax_rates ON product_types.tax_type = master_tax_rates.tax_type"
					+ " WHERE master_tax_rates.tax_start <= NOW() AND master_tax_rates.tax_end > NOW()"
					+ " ORDER BY master_products.product_type ASC";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				productList.add(mapToProduct(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return productList;
	}

	@Override
	public Product findById(Integer productId) throws Exception {
		Product product = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM master_products"
					+ " LEFT OUTER JOIN product_types ON master_products.product_type = product_types.product_type_id"
					+ " LEFT OUTER JOIN master_tax_types ON product_types.tax_type = master_tax_types.tax_type_id"
					+ " LEFT OUTER JOIN master_tax_rates ON product_types.tax_type = master_tax_rates.tax_type"
					+ " WHERE master_tax_rates.tax_start <= NOW() AND master_tax_rates.tax_end > NOW()"
					+ " AND product_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, productId, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				product = mapToProduct(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return product;
	}

	@Override
	public void insert(Product product) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO products"
					+ " (product_id,product_name,product_type)"
					+ " VALUES (?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, product.getProductId(),Types.INTEGER);
			stmt.setString(2, product.getProductName());
			stmt.setObject(2, product.getProductTypeId(),Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(Product product) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE productes"
					+ " SET product_id = ?, product_name = ?,product_name = ?"
					+ " WHERE product_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, product.getProductId(), Types.INTEGER);
			stmt.setString(2, product.getProductName());
			stmt.setObject(3, product.getProductTypeId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void delete(Product product) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM productes"
					+ " Where product_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, product.getProductId(),Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	private Product mapToProduct(ResultSet rs) throws Exception {
		Product product = new Product();
		product.setProductId((Integer) rs.getObject("product_id"));
		product.setProductName(rs.getString("product_name"));
		product.setProductPrice((Integer) rs.getObject("product_price"));
		product.setProductTypeId((Integer) rs.getObject("product_type_id"));
		product.setProductTypeName(rs.getString("product_type_name"));
		product.setTaxTypeId((Integer) rs.getObject("tax_type_id"));
		product.setTaxTypeName(rs.getString("tax_type_name"));
		product.setTaxRate((Double) rs.getObject("tax_rate"));
		
		return product;

	}

	
}
