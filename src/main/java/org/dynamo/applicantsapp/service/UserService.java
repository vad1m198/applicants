package org.dynamo.applicantsapp.service;

import java.util.List;

import org.dynamo.applicantsapp.entity.User;
import org.dynamo.applicantsapp.repos.UsercRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Repository
public class UserService {
	
    @Autowired
    private UsercRepository userRepository;
    
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public List<User> getByName(String name) {
//        return userRepository.findByNameContaining(name);
        //return userRepository.findByFirstNameIgnoreCaseContaining(name);
        return userRepository.findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(name, name);
    }
    
    @Transactional
    public List<User> getAllByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    @Transactional
    public User getById(Integer id) {
        return userRepository.findOne(id);
    }
    
    @Transactional
    public List<User> getAllById(Integer id) {
        return userRepository.findById(id);
    }
    
    @Transactional
	public void save(User user) {
    	userRepository.saveAndFlush(user);
	}

}
