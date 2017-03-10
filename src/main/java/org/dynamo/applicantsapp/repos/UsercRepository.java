package org.dynamo.applicantsapp.repos;

import org.dynamo.applicantsapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UsercRepository extends JpaRepository<User, Integer> {

    List<User> findByEmail(String emailAddress);

}
