package org.dynamo.applicantsapp.authentication;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {

	private static final long serialVersionUID = -6529974982202228122L;
	private int userId;

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, int userId) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
		this.setUserId(userId);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}	

}
