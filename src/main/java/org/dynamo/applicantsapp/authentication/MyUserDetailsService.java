package org.dynamo.applicantsapp.authentication;

import java.util.ArrayList;
import java.util.List;

import org.dynamo.applicantsapp.dao.UserInfoDAO;
import org.dynamo.applicantsapp.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
 
@Service
public class MyUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserInfoDAO userInfoDAO;
 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoDAO.findUserInfo(username);        
        
        if (userInfo == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        
//        System.out.println("UserInfo= " + userInfo);
//        System.out.println(userInfo.getUserName());
//        System.out.println(userInfo.getPassword());
 
        List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_APPLICANT");
        grantList.add(authority);
         
        UserDetails userDetails = (UserDetails) new User(userInfo.getUserName(), //
                userInfo.getPassword(),grantList);
        return userDetails;
    }
     
}