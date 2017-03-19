package org.dynamo.applicantsapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.dynamo.applicantsapp.entity.ShoppingCartAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailServiceImpl {
	
	 @Autowired
	 private JavaMailSender mailSender;
	 
    @Autowired
    private Environment env;
	 
	 
	 public void sendEmail(ShoppingCartAnswer answerInfo, String userName) throws AddressException {
	        MimeMessagePreparator preparator = getMessagePreparator(answerInfo, userName);
 
	        try {
	            mailSender.send(preparator);
	            System.out.println("Message Send...Hurrey");
	        } catch (MailException ex) {
	            System.err.println(ex.getMessage());
	        }
	    }
	 
	    private MimeMessagePreparator getMessagePreparator(final ShoppingCartAnswer answerInfo, final String userName) throws AddressException {
	    	
	        List<Address> recipients = new ArrayList<Address>();
	        for(String s: env.getProperty("mc.recipients").split(",")) {
	        	recipients.add(new InternetAddress(s));
	        }
	 
	        MimeMessagePreparator preparator = mimeMessage -> {
            //InternetAddress[] recipients = new InternetAddress[]{new InternetAddress("test.test@gmail.com")};

                mimeMessage.addRecipients(Message.RecipientType.TO, recipients.toArray(new Address[recipients.size()]));

                String messageStr = "<h4>Bugs:</h4></br><p>" + answerInfo.getBugs() + "</p>";
                messageStr += "<h4>Improvements:</h4></br><p>" + answerInfo.getImprovements() + "</p>";
                messageStr += "<h4>Ideas:</h4></br><p>" + answerInfo.getTestCases() + "</p>";
                mimeMessage.setContent(messageStr, "text/html");
                mimeMessage.setSubject(userName + "'s answers for shopping cart app");

            };
	        return preparator;
	    }

}
