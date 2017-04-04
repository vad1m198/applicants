package org.dynamo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dynamo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	public List<Product> getAllProducts() {
		Map<String, Object> params = new HashMap<String, Object>();		
		String sql = "SELECT * FROM products";
        List<Product> result = namedParameterJdbcTemplate.query(sql, params, new ProductMapper());        
        return result;		
	}
	
	public Product getProductByCode(String code) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		String sql = "SELECT * FROM products WHERE code=:code";
		Product result = namedParameterJdbcTemplate.queryForObject(sql, params, new ProductMapper());
		return result;
	}
	
	 private static final class ProductMapper implements RowMapper<Product> {
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
			product.setCode(rs.getString("code"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getBigDecimal("price"));
			return product;
		}
	 }

	

}
