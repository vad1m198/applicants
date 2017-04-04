package org.dynamo.dao;

import java.util.List;

import org.dynamo.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDao extends UserDetailsService{
	
	public List<User> getAllUsers();	
	public long updateUser(long id, User user);
	User findUserById(long id);
	public long createUser(User user);
	public List<User> getAllUsersByFnameAndSname(String firstName, String secondName);

}
