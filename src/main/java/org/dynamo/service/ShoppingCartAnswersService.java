package org.dynamo.service;

import org.dynamo.entity.ShoppingCartAnswer;

public interface ShoppingCartAnswersService {
	
	ShoppingCartAnswer getAnswerByUserId(long userId);

	void saveAnswer(ShoppingCartAnswer answer);

}
