package org.dynamo.applicantsapp.tests;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dynamo.applicantsapp.controller.AdminController;
import org.dynamo.applicantsapp.entity.User;
import org.dynamo.applicantsapp.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


public class AdminControllerTests {
	 
	@InjectMocks
	AdminController controller;

    @Mock
    UserService mockUserService;
            
    @Mock
    View mockView;
    
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(controller)
                .setSingleView(mockView)
                .build();
    }

    @Test
    public void whenGetDashboardReturnAllUsers() throws Exception {    	
    	List<User> expectedUsers = Arrays.asList(new User());
        when(mockUserService.getAllUsers()).thenReturn(expectedUsers);
        
        mockMvc.perform(get("/admin/dashboard"))
	        .andExpect(status().isOk())        
	        .andExpect(request().sessionAttribute("allUsers", expectedUsers))
	        .andExpect(view().name("admin/dashboardPage"));
    }
    
    @Test
    public void whenGetDashboardWithQueryReturnFindByNameUsers() throws Exception {
    	String queryParam = "test";
    	List<User> expectedUsers = Arrays.asList(new User());
        when(mockUserService.getByName(queryParam)).thenReturn(expectedUsers);
        
        mockMvc.perform(get("/admin/dashboard?query=" + queryParam))
	        .andExpect(status().isOk())        
	        .andExpect(request().sessionAttribute("allUsers", expectedUsers))
	        .andExpect(view().name("admin/dashboardPage"));
    }
	
}
