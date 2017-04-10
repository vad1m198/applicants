package org.dynamo.service;

import java.util.List;

import org.dynamo.dao.UserRoleDao;
import org.dynamo.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	public List<UserRole> getAllRoles() {
		return userRoleDao.getAllRoles();
	}

}
