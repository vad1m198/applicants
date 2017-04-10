package org.dynamo.service;

import java.util.List;

import org.dynamo.dao.ProductDao;
import org.dynamo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productdao;

	@Override
	public List<Product> getAllProducts() {
		return productdao.getAllProducts();
	}

	@Override
	public Product getProductByCode(String code) {
		return productdao.getProductByCode(code);
	}

}
