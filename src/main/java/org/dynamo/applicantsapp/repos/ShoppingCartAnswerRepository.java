package org.dynamo.applicantsapp.repos;

import org.dynamo.applicantsapp.entity.ShoppingCartAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Вадим on 08.03.2017.
 */
public interface ShoppingCartAnswerRepository extends JpaRepository<ShoppingCartAnswer, Integer> {

    List<ShoppingCartAnswer> findByApplicantId(int applicantId);

}
