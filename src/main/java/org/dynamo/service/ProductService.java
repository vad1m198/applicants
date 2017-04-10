package org.dynamo.service;

import java.util.List;

import org.dynamo.entity.Product;

public interface ProductService {

	List<Product> getAllProducts();
	Product getProductByCode(String code);
}
