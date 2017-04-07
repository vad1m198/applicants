package org.dynamo.service;

import org.dynamo.entity.CustomEmail;

public interface MailService {	 
    public void sendEmail(final CustomEmail email);
}
