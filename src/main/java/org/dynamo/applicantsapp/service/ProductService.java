package org.dynamo.applicantsapp.service;

import java.util.List;

import org.dynamo.applicantsapp.entity.Product;
import org.dynamo.applicantsapp.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Repository
public class ProductService {
	
    @Autowired
    private ProductRepository productRepository;
    
    @Transactional
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    
	public Product getByCode(String code) {
		return productRepository.findByCode(code);
	}

}
