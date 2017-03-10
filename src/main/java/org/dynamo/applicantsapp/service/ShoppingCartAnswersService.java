package org.dynamo.applicantsapp.service;

import org.dynamo.applicantsapp.entity.ShoppingCartAnswer;
import org.dynamo.applicantsapp.repos.ShoppingCartAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Repository
public class ShoppingCartAnswersService {

	@Autowired
	private ShoppingCartAnswerRepository shoppingCartAnswerRepository;
    
    @Transactional
    public ShoppingCartAnswer saveAndFlush(ShoppingCartAnswer answer) {
        if ( answer != null ) {
        	answer = shoppingCartAnswerRepository.saveAndFlush(answer);
        }
        return answer;
    }

}
