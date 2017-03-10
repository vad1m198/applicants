package org.dynamo.applicantsapp.repos;


import org.dynamo.applicantsapp.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer>  {

}
