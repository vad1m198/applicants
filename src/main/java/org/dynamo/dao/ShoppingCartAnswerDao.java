package org.dynamo.dao;

import org.dynamo.entity.ShoppingCartAnswer;

public interface ShoppingCartAnswerDao {

	ShoppingCartAnswer getAnswerByUserId(long userId);

	long saveAnswer(ShoppingCartAnswer answer);
	
}
