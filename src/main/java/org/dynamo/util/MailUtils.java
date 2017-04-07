package org.dynamo.util;

import java.util.Arrays;

import org.dynamo.entity.CustomEmail;
import org.dynamo.entity.ShoppingCartAnswer;
import org.dynamo.entity.User;

public class MailUtils {
		
	public static CustomEmail getCreateUserMail(User user) {
		CustomEmail email = new CustomEmail();
		email.setTo(Arrays.asList(user.getEmail()));
		email.setSubject("Thank you for your interest");
		email.setHTMLMessage("<html>"
    			+ "<p>Hello: " + user.getFirstName() + " " + user.getSecondName() + "</p>"
    			+ "<p>Please pass test <a href='https://stark-dawn-86778.herokuapp.com/'>task</a></p>"
    			+ "<p>Use <b>" + user.getEmail() + "</b> as username and <b>" + user.getPassword() +"</b> as password</p>"
    					+ "</html>");
		return email;
	}

	public static CustomEmail getSubmitAnswersMail(ShoppingCartAnswer answer, String userName, String recipients) {
		CustomEmail email = new CustomEmail();
		email.setTo(Arrays.asList(recipients.split(",")));
		email.setSubject(userName + "'s answers");
		email.setHTMLMessage("<html>"
    			+ "<p><b>" + userName + "</b> has submitted answers</p>"
    			+ "<p><b>Bugs:</b><br/>" + answer.getBugs() + "</p>"
    			+ "<p><b>Ideas:</b><br/>" + answer.getIdeas() + "</p>"
    			+ "<p><b>Improvements:</b><br/>" + answer.getImprovements() + "</p>"
    					+ "</html>");
		
		return email;
	}

}
