package org.dynamo.dao;

import java.util.List;
import org.dynamo.entity.Product;

public interface ProductDao {
	
	List<Product> getAllProducts();
	Product getProductByCode(String code);

}
