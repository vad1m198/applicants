package org.dynamo.applicantsapp.service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.dynamo.applicantsapp.model.ShoppingCartAnswerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailServiceImpl {
	
	 @Autowired
	 JavaMailSender mailSender;
	 
	 
	 public void sendEmail(ShoppingCartAnswerInfo answerInfo, String userName) {
	        MimeMessagePreparator preparator = getMessagePreparator(answerInfo, userName);
	 
	        try {
	            mailSender.send(preparator);
	            System.out.println("Message Send...Hurrey");
	        } catch (MailException ex) {
	            System.err.println(ex.getMessage());
	        }
	    }
	 
	    private MimeMessagePreparator getMessagePreparator(final ShoppingCartAnswerInfo answerInfo, final String userName) {
	 
	        MimeMessagePreparator preparator = new MimeMessagePreparator() {

				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
//					mimeMessage.setFrom(new InternetAddress("hr@org.dynamo.ny"));					
					InternetAddress[] recipients = new InternetAddress[]{new InternetAddress("vadim.merkotan@silverlinecrm.com")};
					
					mimeMessage.addRecipients(Message.RecipientType.TO, recipients);
					
					String messageStr = "<h4>Bugs:</h4></br><p>" + answerInfo.getBugs() + "</p>";
					messageStr += "<h4>Improvements:</h4></br><p>" + answerInfo.getImprovements() + "</p>";
					messageStr += "<h4>Test cases:</h4></br><p>" + answerInfo.getTest_cases() + "</p>";					
					mimeMessage.setContent(messageStr, "text/html");
	                mimeMessage.setSubject(userName + " answer's for shopping cart app");
					
				}
	        };
	        return preparator;
	    }

}
