package org.dynamo.entity;

import java.util.List;

public class CustomEmail {
	
	private String from;
    private List<String> to;    
    private String subject;
    private String htmlmessage;

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
