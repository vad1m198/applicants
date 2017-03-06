package org.dynamo.applicantsapp.dao;
import org.dynamo.applicantsapp.entity.ShoppingCartAnswer;
import org.dynamo.applicantsapp.model.ShoppingCartAnswerInfo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ShoppingCartAnswerDAO {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	public ShoppingCartAnswerInfo findShoppingCartAnswerInfo(int applicantId) {
        String sql = "Select new " + ShoppingCartAnswerInfo.class.getName() + "(s.id, s.bugs, s.test_cases, s.improvements, s.applicant_id) "//
                + " from " + ShoppingCartAnswer.class.getName() + " s where s.applicant_id = :applicant_id ";
 
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        query.setParameter("applicant_id", applicantId);
        return (ShoppingCartAnswerInfo) query.uniqueResult();
	}
	
	public void saveShoppingCartAnswers(ShoppingCartAnswerInfo answerInfo) { 
		Session session = sessionFactory.getCurrentSession();
		ShoppingCartAnswer answer = new ShoppingCartAnswer();
		answer.setApplicant_id(answerInfo.getApplicant_id());
		answer.setBugs(answerInfo.getBugs());		
		answer.setImprovements(answerInfo.getImprovements());
		answer.setTest_cases(answerInfo.getTest_cases());
        session.persist(answer);
		
	}

}
