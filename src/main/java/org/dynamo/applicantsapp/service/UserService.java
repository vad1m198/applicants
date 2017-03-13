package org.dynamo.applicantsapp.service;

import java.util.List;

import org.dynamo.applicantsapp.entity.User;
import org.dynamo.applicantsapp.repos.UsercRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Repository
public class UserService {
	
    @Autowired
    private UsercRepository userRepository;
    
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
