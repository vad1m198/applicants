package org.dynamo.service;

import org.dynamo.entity.ShoppingCartAnswer;

public interface ShoppingCartAnswersService {
	
	ShoppingCartAnswer getAnswerByUserId(long userId);

	long saveAnswer(ShoppingCartAnswer answer);

}
