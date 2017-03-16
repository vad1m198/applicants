package org.dynamo.applicantsapp.repos;

import org.dynamo.applicantsapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UsercRepository extends JpaRepository<User, Integer> {

    List<User> findByEmail(String emailAddress);
    
    List<User> findById(Integer id);

//    @Query("Select u from User u where u.first_name like :name OR u.last_name like :name")
//    List<User> findByNameContaining(@Param("name")String name);
//
//
//    List<Email> findByEmailIdInAndPincodeIn(List<String> emails, List<String> pinCodes);
//
//
//    List<Registration> findByPlaceIgnoreCaseContaining(String place);

    List<User> findByFirstNameIgnoreCaseContaining(String firstName);

    List<User> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String firstName, String lastName);
}
