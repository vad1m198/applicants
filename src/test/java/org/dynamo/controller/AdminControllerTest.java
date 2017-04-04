package org.dynamo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Arrays;
import java.util.List;

import org.dynamo.dao.ProductDao;
import org.dynamo.dao.UserDao;
import org.dynamo.entity.Product;
import org.dynamo.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

public class AdminControllerTest {
	
    @InjectMocks
    private AdminController controller;
            
    @Mock
    private View mockView;
    
    @Mock
	private UserDao userDao;

    private MockMvc mockMvc;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(controller)
                .setSingleView(mockView)
                .build();
    }
    
    @Test
    public void whenGetDashboardThenGetAllUsersFromService() throws Exception {
    	List<User> users = Arrays.asList(new User(), new User());
    	
    	when(userDao.getAllUsers()).thenReturn(users);
    	
        mockMvc.perform(get("/admin/dashboard"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("users", users))
                .andExpect(view().name("admin/dashboard"));
    }

}
