package org.dynamo.entity;

import java.util.Arrays;
import java.util.List;

public class CustomEmail {
	
	private String from;
    private List<String> to;    
    private String subject;
    private String htmlmessage;

    public CustomEmail() {}

    public CustomEmail(User user) {
    	this.to = Arrays.asList(user.getEmail());
    	this.subject = "Thank you for your interest";
    	this.htmlmessage = "<html>"
    			+ "<p>Hello: " + user.getFirstName() + " " + user.getSecondName() + "</p>"
    			+ "<p>Please pass test <a href='http://localhost:8082'>task</a></p>"
    			+ "<p>Use <b>" + user.getEmail() + "</b> as username and <b>" + user.getPassword() +"</b> as password</p>"
    					+ "</html>";
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

	public String getHTMLMessage() {
		return htmlmessage;
	}
	
	public void setHTMLMessage(String htmlmessage) {
        this.htmlmessage = htmlmessage;
    }

}
