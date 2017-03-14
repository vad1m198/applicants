package org.dynamo.applicantsapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dynamo.applicantsapp.entity.User;
import org.dynamo.applicantsapp.entity.UserRole;
import org.dynamo.applicantsapp.model.UserFormInfo;
import org.dynamo.applicantsapp.service.UserRoleService;
import org.dynamo.applicantsapp.service.UserService;
import org.dynamo.applicantsapp.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
	
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRoleService userRoleService;
 
	
   @RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
   public String getDashboard(HttpServletRequest request) {
	   
	   List<User> usersInSession = Utils.getUsersInSession(request);
	   if(usersInSession == null) {
		   List<User> users = userService.getAllUsers();
		   Utils.setUsersInSession(request, users);
	   }
	   return "admin/dashboardPage";
   }
   
   @RequestMapping(value = "/admin/userForm", method = RequestMethod.GET)
   public String getUserForm(HttpServletRequest request, Model model, @RequestParam(value = "id", defaultValue = "") String id) {	   
	   if(id.isEmpty()) {
		   UserFormInfo info = Utils.getUserFormInSession(request);
		   if(info == null) {
			   info = new UserFormInfo();
			   List<UserRole> roles = userRoleService.getAllRoles();			   
			   info.setRolesInfo(roles);
		   }	
		   model.addAttribute("userFormInfo",info);
		   return "admin/userFormPage";
	   }
	   
	   return "admin/dashboardPage";
   }
   
   @RequestMapping(value = "/admin/userForm", method = RequestMethod.POST)
   public String saveUser(HttpServletRequest request, //
           Model model, //
           @ModelAttribute("userFormInfo") UserFormInfo info) {

	   User user = new User(info);	   
	   List<UserRole> roles = new ArrayList<UserRole>();	   
	   for(int i: info.getRolesIds()) {
		   UserRole role = userRoleService.getRoleById(i);
		   if (role != null) {
			   roles.add(role);
		   }
	   }
	   
	   user.setRoles(roles);
	   
	   System.out.println("Email: " + user.getEmail());
	   System.out.println("FirstName: " + user.getFirst_name());
	   System.out.println("LastName: " + user.getLast_name());
	   System.out.println("Id: " + user.getId());
	   System.out.println("Password: " + user.getPassword());
	   
	   System.out.println("roles >>>> ");
	   
	   for(UserRole r: user.getRoles()) {
		   System.out.println("Id: " + r.getId());
		   System.out.println("Role: " + r.getRole());
	   }
	   
	   userService.save(user);
	   
	   
	   
	   return "admin/dashboardPage";
   }
}
