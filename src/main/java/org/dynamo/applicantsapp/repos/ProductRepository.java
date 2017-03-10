package org.dynamo.applicantsapp.repos;

import org.dynamo.applicantsapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByCode(String code);
}
