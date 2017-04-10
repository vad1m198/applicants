package org.dynamo.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.dynamo.dao.UserDao;
import org.dynamo.entity.CustomEmail;
import org.dynamo.entity.User;
import org.dynamo.entity.UserRole;
import org.dynamo.model.UserFormInfo;
import org.dynamo.util.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
		
	@Autowired
	private MailService mailservice;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRoleService userRoleService;

	@Override
	public UserDetails loadUserByUsername(String username) {
		return userDao.loadUserByUsername(username);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public List<User> getAllUsersByFnameAndSname(String firstName, String secondName) {
		return userDao.getAllUsersByFnameAndSname(firstName, secondName);
	}

	@Override
	public User findUserById(long id) {
		return userDao.findUserById(id);
	}

	@Override
	public long updateUser(long id, User user) {
		return userDao.updateUser(id, user);
	}
	
	@Override
	public long upsertUser(UserFormInfo info) {
		
		User user = info.getUser();
		List<UserRole> roles = userRoleService.getAllRoles().stream()
				.filter(r -> info.getSelectedRoles().contains(r.getId()))
				.collect(Collectors.toList());
		
		user.setRoles(roles);
		
		long userId = 0;
		
		if(info.getUser().getId() > 0) {
			userId = userDao.updateUser(info.getUser().getId(), user);
		} else {
			String password = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
			user.setPassword(passwordEncoder.encode(password));
			userId = userDao.createUser(user);
			user.setPassword(password);
			CustomEmail mail = MailUtils.getCreateUserMail(user);
			mailservice.sendEmail(mail);
		}
		return userId;
	}

	@Override
	public long createUser(User user) {
		return userDao.createUser(user);
	}

}
