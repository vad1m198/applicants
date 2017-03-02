package org.dynamo.applicantsapp.dao;

import org.dynamo.applicantsapp.entity.User;
import org.dynamo.applicantsapp.model.UserInfo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserInfoDAO {
	
	@Autowired
    private SessionFactory sessionFactory;
 
    public UserInfoDAO() {
 
    }
    
    public UserInfo findUserInfo(String userName) {
        String sql = "Select new " + UserInfo.class.getName() + "(u.email,u.password) "//
                + " from " + User.class.getName() + " u where u.email = :username ";
 
        Session session = sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        query.setParameter("username", userName);
         
        return (UserInfo) query.uniqueResult();
    }

}
