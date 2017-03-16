package org.dynamo.applicantsapp.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.dynamo.applicantsapp.authentication.MyUserDetailsService;
import org.dynamo.applicantsapp.config.WebSecurityConfig;
import org.dynamo.applicantsapp.controller.AdminController;
import org.dynamo.applicantsapp.controller.MainController;
import org.dynamo.applicantsapp.repos.ShoppingCartAnswerRepository;
import org.dynamo.applicantsapp.repos.UserRoleRepository;
import org.dynamo.applicantsapp.repos.UsercRepository;
import org.dynamo.applicantsapp.service.UserRoleService;
import org.dynamo.applicantsapp.service.UserService;
import org.dynamo.applicantsapp.validator.UserFormValidator;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SecurityTests.TestConfiguration.class, WebSecurityConfig.class})
@WebAppConfiguration
public class SecurityTests {
	
	@EnableWebMvc
    @Configuration
	public static class TestConfiguration {
		
	    @Bean(name = "viewResolver")
	    public InternalResourceViewResolver getViewResolver() {
	        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	        viewResolver.setPrefix("/WEB-INF/pages/");
	        viewResolver.setSuffix(".jsp");
	        return viewResolver;
	    }
	    
        @Bean(name = "myUserDetailsService")
        MyUserDetailsService getMyUserDetailsService() {
            return Mockito.mock(MyUserDetailsService.class);
        }
        
        @Bean(name = "usercRepository")
        UsercRepository getUsercRepository() {
            return Mockito.mock(UsercRepository.class);
        }
                
        @Bean(name = "userRoleRepository")
        UserRoleRepository getUserRoleRepository() {
            return Mockito.mock(UserRoleRepository.class);
        }
        
        @Bean(name = "shoppingCartAnswerRepository")
        ShoppingCartAnswerRepository getShoppingCartAnswerRepository() {
            return Mockito.mock(ShoppingCartAnswerRepository.class);
        }
        
        @Bean
        public MainController mainController() {
        	return new MainController();
        }
        
        @Bean
        public AdminController adminController() {
        	return new AdminController();
        }
        
        @Bean
        public UserFormValidator userFormValidator() {
        	return new UserFormValidator();
        }
        @Bean
        public UserService userService() {
        	return new UserService();
        }
        @Bean
        public UserRoleService userRoleService() {
        	return new UserRoleService();
        }
	}
	
    MockMvc mockMvc;
    
    @Autowired
	private WebApplicationContext context;
        
    @Before
    public void setup() {
    	    	
    	mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }
	
    @Test
    public void whenGetRootAsAnonymousThenReturnLoginPage() throws Exception {
            this.mockMvc
        		.perform(get("/").with(anonymous())).andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("loginPage"))
                .andReturn();
    }
    
    @Test
    @WithMockUser(roles="ADMIN_USER")
    public void testCRetrieve() throws Exception {
            this.mockMvc
            	.perform(get("/admin/dashboard"))
            	.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("admin/dashboardPage"));
    }
	
}
