package org.dynamo.applicantsapp.service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;

import org.dynamo.applicantsapp.entity.ShoppingCartAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailServiceImpl {
	
	 @Autowired
	 JavaMailSender mailSender;
	 
	 
	 public void sendEmail(ShoppingCartAnswer answerInfo, String userName) {
	        MimeMessagePreparator preparator = getMessagePreparator(answerInfo, userName);
	 
	        try {
	            mailSender.send(preparator);
	            System.out.println("Message Send...Hurrey");
	        } catch (MailException ex) {
	            System.err.println(ex.getMessage());
	        }
	    }
	 
	    private MimeMessagePreparator getMessagePreparator(final ShoppingCartAnswer answerInfo, final String userName) {
	 
	        MimeMessagePreparator preparator = mimeMessage -> {
//					mimeMessage.setFrom(new InternetAddress("hr@org.dynamo.ny"));
                InternetAddress[] recipients = new InternetAddress[]{new InternetAddress(/*"vadym.merkotan@gmail.com"*/"vadim.merkotan@silverlinecrm.com")};

                mimeMessage.addRecipients(Message.RecipientType.TO, recipients);

                String messageStr = "<h4>Bugs:</h4></br><p>" + answerInfo.getBugs() + "</p>";
                messageStr += "<h4>Improvements:</h4></br><p>" + answerInfo.getImprovements() + "</p>";
                messageStr += "<h4>Test cases:</h4></br><p>" + answerInfo.getTestCases() + "</p>";
                mimeMessage.setContent(messageStr, "text/html");
                mimeMessage.setSubject(userName + "'s answers for shopping cart app");

            };
	        return preparator;
	    }

}
