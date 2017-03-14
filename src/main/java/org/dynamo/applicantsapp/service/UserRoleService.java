package org.dynamo.applicantsapp.service;

import java.util.List;

import org.dynamo.applicantsapp.entity.UserRole;
import org.dynamo.applicantsapp.repos.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Repository
public class UserRoleService {
		
	@Autowired
	UserRoleRepository userRoleRepository;
	
    @Transactional
	public List<UserRole> getAllRoles() {
		return userRoleRepository.findAll();		
	}
    
    @Transactional
	public UserRole getRoleById(int id) {
		return userRoleRepository.findOne(id);		
	}

}
