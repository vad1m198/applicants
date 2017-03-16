package org.dynamo.applicantsapp.authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.dynamo.applicantsapp.entity.ShoppingCartAnswer;
import org.dynamo.applicantsapp.entity.User;
import org.dynamo.applicantsapp.entity.UserRole;
import org.dynamo.applicantsapp.repos.ShoppingCartAnswerRepository;
import org.dynamo.applicantsapp.repos.UsercRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsercRepository usercRepository;

    @Autowired
    private ShoppingCartAnswerRepository shoppingCartAnswerRepository;

    public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = usercRepository.findByEmail(username);

        if (users.size() == 0) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }

        User user = users.get(0);

        List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
        for(UserRole role: user.getRoles()) {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getRole());
            grantList.add(authority);
        }

        List<ShoppingCartAnswer> answers = shoppingCartAnswerRepository.findByApplicantId(user.getId());

        if(answers.size() > 0) {
            grantList = grantList.stream()
                    .filter(authority ->  !authority.getAuthority().equals("ROLE_SHOPPING_CART_USER"))
                    .collect(Collectors.toList());
        }

        CustomUser customUser = new CustomUser(user.getFirstName() + " " + user.getLastName(),
                user.getPassword(),grantList, user.getId());
        return customUser;
    }
     
}