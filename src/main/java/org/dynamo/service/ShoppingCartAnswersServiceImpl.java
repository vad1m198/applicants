package org.dynamo.service;

import org.dynamo.dao.ShoppingCartAnswerDao;
import org.dynamo.entity.CustomEmail;
import org.dynamo.entity.ShoppingCartAnswer;
import org.dynamo.entity.User;
import org.dynamo.util.MailUtils;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartAnswersServiceImpl implements ShoppingCartAnswersService {
	
	@Autowired
	private ShoppingCartAnswerDao answerdao;
	
	@Autowired
	private UserService userService;
	
    @Autowired
    private Environment env;
    
	@Autowired
	private MailService mailservice;

	@Override
	public ShoppingCartAnswer getAnswerByUserId(long userId) {
		return answerdao.getAnswerByUserId(userId);
	}

	@Override
	public long saveAnswer(ShoppingCartAnswer answer) {
		long id = answerdao.saveAnswer(answer);
		User user = userService.findUserById(answer.getUserId());
		if(answer.isSubmitted()) {
			CustomEmail mail = MailUtils.getSubmitAnswersMail(answer, user.getFirstName() + " " + user.getSecondName(), env.getProperty("ANSWERS_RECIPIENTS"));
			mailservice.sendEmail(mail);    		
    	}
		return id;
	}	
}
