package org.dynamo.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AuthenticationUser extends User {
	
	private static final long serialVersionUID = 1L;

	public AuthenticationUser(String username, String password, Collection<? extends GrantedAuthority> authorities, long userId) {
		super(username, password, authorities);	
		this.userId = userId;
	}

	private long userId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}	

}
