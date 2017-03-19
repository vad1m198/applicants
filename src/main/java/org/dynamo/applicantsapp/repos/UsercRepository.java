package org.dynamo.applicantsapp.repos;

import org.dynamo.applicantsapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UsercRepository extends JpaRepository<User, Integer> {

    List<User> findByEmail(String emailAddress);
    
    List<User> findById(Integer id);

    List<User> findByFirstNameIgnoreCaseContaining(String firstName);

    List<User> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String firstName, String lastName);
}
