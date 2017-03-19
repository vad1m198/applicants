package org.dynamo.applicantsapp;

import java.util.Collections;
import java.util.List;

import org.dynamo.applicantsapp.controller.AdminController;
import org.dynamo.applicantsapp.entity.User;
import org.dynamo.applicantsapp.entity.UserRole;
import org.dynamo.applicantsapp.model.UserFormInfo;
import org.dynamo.applicantsapp.service.UserRoleService;
import org.dynamo.applicantsapp.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.servlet.View;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.springframework.validation.BindingResult;



public class AdminControllerTest {
	 
	@InjectMocks
    private AdminController controller;

    @Mock
    private UserService mockUserService;
    
    @Mock
    private BindingResult mockBindingResult;

    @Mock
    private UserRoleService mockUserRoleService;
            
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

    @Test
    public void whenGetUserFormThenEmptyUserFormInfoShouldBeReturned() throws Exception {
        List<UserRole> expectedUserRoles = Collections.singletonList(new UserRole());
        when(mockUserRoleService.getAllRoles()).thenReturn(expectedUserRoles);

        MvcResult result = mockMvc.perform(get("/admin/userForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/userFormPage"))
                .andReturn();

        UserFormInfo info = (UserFormInfo) result.getModelAndView().getModel().get("userFormInfo");
        assertEquals(info.getRolesInfo(), expectedUserRoles);
        assertEquals(info.getUserInfo().getId(), 0);
        assertNull(info.getUserInfo().getEmail());
        assertNull(info.getUserInfo().getFirstName());
        assertNull(info.getUserInfo().getLastName());
        assertNull(info.getUserInfo().getPassword());
        assertNull(info.getRolesIds());
    }

    @Test
    public void whenGetUserFormWithValidUserIdThenUserFormInfoForUserShouldBeReturned() throws Exception {
        UserRole role = new UserRole();
        role.setId(1);
        role.setRole("ADMIN");
        List<UserRole> expectedUserRoles = Collections.singletonList(role);

        User user = new User();
        user.setId(1);
        user.setEmail("test@test.com");
        user.setFirstName("fname");
        user.setLastName("lname");
        user.setPassword("pass");
        user.setRoles(expectedUserRoles);


        when(mockUserRoleService.getAllRoles()).thenReturn(expectedUserRoles);
        when(mockUserService.getById(1)).thenReturn(user);

        MvcResult result = mockMvc.perform(get("/admin/userForm?id=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/userFormPage"))
                .andReturn();

        UserFormInfo info = (UserFormInfo) result.getModelAndView().getModel().get("userFormInfo");
        assertEquals(info.getRolesInfo(), expectedUserRoles);
        assertEquals(info.getUserInfo().getId(), 1);
        assertEquals(info.getUserInfo().getEmail(), "test@test.com");
        assertEquals(info.getUserInfo().getFirstName(), "fname");
        assertEquals(info.getUserInfo().getLastName(), "lname");
        assertEquals(info.getUserInfo().getPassword(), "pass");
        assertEquals(info.getRolesIds().stream().map(Object::toString).reduce("", String::concat)
                , "1");

    }

    @Test
    public void whenGetUserFormWithUnexistingUserIdThen404ShouldBeReturned() throws Exception {
        when(mockUserService.getById(1)).thenReturn(null);

        mockMvc.perform(get("/admin/userForm?id=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/404"));
    }

    @Test
    public void whenGetUserFormWithNotNumberUserIdThen404ShouldBeReturned() throws Exception {
        mockMvc.perform(get("/admin/userForm?id=test"))
                .andExpect(status().isOk())
                .andExpect(view().name("/404"));
    }
    
    @Test
    public void whenPostUserFormHasNoErrorsThenSaveUserAndRedirectToView() throws Exception {
    	
		UserRole role = new UserRole();
		role.setId(1);
		role.setRole("ADMIN");
		List<UserRole> expectedUserRoles = Collections.singletonList(role);
    	
    	when(mockBindingResult.hasErrors()).thenReturn(Boolean.FALSE);
    	when(mockUserRoleService.getAllRoles()).thenReturn(expectedUserRoles);
    	when(mockUserRoleService.getRoleById(1)).thenReturn(role);
    					  
		RequestBuilder request = post("/admin/userForm")
			.param("userInfo.id", "0")
			.param("userInfo.firstName", "fname")
			.param("userInfo.lastName","lname")
			.param("userInfo.email","test@test.com")
			.param("userInfo.password", "qwerty")
			.param("rolesIds", "1")
			.param("userFormInfo.rolesInfo", expectedUserRoles.toString());
		
		mockMvc
		.perform(request)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(view().name("redirect:view?id=0"));
				
		ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
		verify(mockUserService).save(argument.capture());
		assertEquals(0, argument.getValue().getId());
		assertEquals("fname", argument.getValue().getFirstName());
		assertEquals("lname", argument.getValue().getLastName());
		assertEquals("test@test.com", argument.getValue().getEmail());
		assertEquals("qwerty", argument.getValue().getPassword());
		assertEquals(expectedUserRoles, argument.getValue().getRoles());
	  }
}
