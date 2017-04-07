package org.dynamo.service;

import javax.ws.rs.core.MediaType;

import org.dynamo.entity.CustomEmail;
import org.dynamo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Service("mailService")
public class MailServiceImpl implements MailService {
	
    @Autowired
    private Environment env;
 
    @Override
    public void sendEmail(CustomEmail email) {
    	Client client = Client.create();
    	
    	final String DOMAIN_NAME = "";
    	final String API_KEY = "";
    	
//		client.addFilter(new HTTPBasicAuthFilter("api", env.getProperty("MAILGUN_API_KEY")));
//		WebResource webResource = client.resource("https://api.mailgun.net/v3/" + env.getProperty("MAILGUN_DOMAIN_NAME")
//		    + "/messages");
    	
    	client.addFilter(new HTTPBasicAuthFilter("api", API_KEY));
		WebResource webResource = client.resource("https://api.mailgun.net/v3/" + DOMAIN_NAME
		    + "/messages");
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add("from", "<mailgun@" + DOMAIN_NAME + ">");
		formData.add("to", String.join(", ", email.getTo()));
		formData.add("subject", email.getSubject());
		formData.add("html", email.getHTMLMessage());
		String res = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class,
		    formData).toString();
		         
    }
 
}