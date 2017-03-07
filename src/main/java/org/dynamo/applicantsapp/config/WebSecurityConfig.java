package org.dynamo.applicantsapp.config;

import org.dynamo.applicantsapp.authentication.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.annotation.Transactional;
 
@Configuration
@EnableWebSecurity
@Transactional
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    
    @Autowired
    private MyUserDetailsService myUserDetailsService;
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
  
        // For User in database.
        auth.userDetailsService(myUserDetailsService);
 
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
 
        http.csrf().disable();
 
        // The pages does not require login
        http.authorizeRequests().antMatchers("/", "/login").permitAll();
 
        // If no login, it will redirect to /login page.
        http.authorizeRequests().antMatchers("/welcome", "/productList", "/shoppingCartConfirmation", 
        		"/shoppingCart", "/buyProduct", "/shoppingCartCustomer", "/shoppingCartAnswers").access("hasRole('ROLE_APPLICANT_SHOPPING_CART')");
        
        
        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will throw.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
                
        // Config for Login Form
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/welcome")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
 
    }
}