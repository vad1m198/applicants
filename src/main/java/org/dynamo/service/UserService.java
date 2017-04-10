package org.dynamo.service;

import java.util.List;

import org.dynamo.entity.User;
import org.dynamo.model.UserFormInfo;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
	
	UserDetails loadUserByUsername(String username);
	List<User> getAllUsers();
	List<User> getAllUsersByFnameAndSname(String firstName, String secondName);
	User findUserById(long id);
	long updateUser(long id, User user);
	long createUser(User user);
	long upsertUser(UserFormInfo info);

}
