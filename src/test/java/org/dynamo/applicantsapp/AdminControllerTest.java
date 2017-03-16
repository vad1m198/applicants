package org.dynamo.applicantsapp;

import java.util.Collections;
import java.util.List;

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


public class AdminControllerTest {
	 
	@InjectMocks
    private AdminController controller;

    @Mock
    private UserService mockUserService;
            
    @Mock
    private View mockView;
    
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(controller)
                .setSingleView(mockView)
                .build();
    }

    @Test
    public void whenGetDashboardReturnAllUsers() throws Exception {    	
    	List<User> expectedUsers = Collections.singletonList(new User());
        when(mockUserService.getAllUsers()).thenReturn(expectedUsers);
        
        mockMvc.perform(get("/admin/dashboard"))
	        .andExpect(status().isOk())        
	        .andExpect(request().sessionAttribute("allUsers", expectedUsers))
	        .andExpect(view().name("admin/dashboardPage"));
    }
    
    @Test
    public void whenGetDashboardWithQueryReturnFindByNameUsers() throws Exception {
    	String queryParam = "test";
    	List<User> expectedUsers = Collections.singletonList(new User());
        when(mockUserService.getByName(queryParam)).thenReturn(expectedUsers);
        
        mockMvc.perform(get("/admin/dashboard?query=" + queryParam))
	        .andExpect(status().isOk())        
	        .andExpect(request().sessionAttribute("allUsers", expectedUsers))
	        .andExpect(view().name("admin/dashboardPage"));
    }
	
}
