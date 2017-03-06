package org.dynamo.applicantsapp.authentication;

import java.util.ArrayList;
import java.util.List;

import org.dynamo.applicantsapp.dao.ShoppingCartAnswerDAO;
import org.dynamo.applicantsapp.dao.UserInfoDAO;
import org.dynamo.applicantsapp.model.ShoppingCartAnswerInfo;
import org.dynamo.applicantsapp.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
 
@Service
public class MyUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserInfoDAO userInfoDAO;
    
    @Autowired
    private ShoppingCartAnswerDAO shoppingCartAnswerDAO;

    public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoDAO.findUserInfo(username);        
        
        if (userInfo == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
        ShoppingCartAnswerInfo answerInfo = shoppingCartAnswerDAO.findShoppingCartAnswerInfo(Integer.parseInt(userInfo.getId()));
        
        if(answerInfo == null) {
        	GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_APPLICANT_SHOPPING_CART");
            grantList.add(authority);
        }

        CustomUser user = new CustomUser(userInfo.getFirstName() + " " + userInfo.getLastName(),
                userInfo.getPassword(),grantList, userInfo.getId());
        return user;
    }
     
}