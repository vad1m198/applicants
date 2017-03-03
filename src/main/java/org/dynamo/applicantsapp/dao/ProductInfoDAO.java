package org.dynamo.applicantsapp.dao;

import java.util.List;

import org.dynamo.applicantsapp.entity.Product;
import org.dynamo.applicantsapp.model.ProductInfo;
import org.dynamo.applicantsapp.model.UserInfo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProductInfoDAO {
	
	@Autowired
    private SessionFactory sessionFactory;
 
    public ProductInfoDAO() {
 
    }
    
    public List<ProductInfo> getAllProducts() {    	
        String sql = "Select new " + ProductInfo.class.getName() + "(p.code, p.name, p.price) "//
                + " from " + Product.class.getName() + " p";
 
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        List<ProductInfo> result = query.list();        
        return result;
    }

	public ProductInfo findProductInfo(String code) {
        String sql = "Select new " + ProductInfo.class.getName() + "(p.code, p.name, p.price) "//
                + " from " + Product.class.getName() + " p where p.code = :code ";
 
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        query.setParameter("code", code);
        return (ProductInfo) query.uniqueResult();
	}

}
