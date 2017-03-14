package org.dynamo.applicantsapp.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.dynamo.applicantsapp.entity.User;
import org.dynamo.applicantsapp.entity.UserRole;
import org.dynamo.applicantsapp.model.CustomerInfo;
import org.dynamo.applicantsapp.model.UserFormInfo;
import org.dynamo.applicantsapp.service.UserRoleService;
import org.dynamo.applicantsapp.service.UserService;
import org.dynamo.applicantsapp.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		   List<UserRole> roles = userRoleService.getAllRoles();		   
		   info.setAllRoles(roles);
		   info.setUser(new User());
		   System.out.println("user.getRoles() >>>>>>>>>>>>>>>>>>>>> ");
		   System.out.println(info.getAllRoles());
		   model.addAttribute("userFormInfo",info);
		   return "admin/userFormPage";
	   }
	   
//	   List<User> usersInSession = Utils.getUsersInSession(request);
//	   if(usersInSession == null) {
//		   List<User> users = userService.getAllUsers();
//		   Utils.setUsersInSession(request, users);
//	   }
	   return "admin/dashboardPage";
   }
   
   @RequestMapping(value = "/admin/userForm", method = RequestMethod.POST)
   public String saveUser(HttpServletRequest request, //
           Model model, //
           @ModelAttribute("userForimInfo") UserFormInfo info, BindingResult result) {
	   	   
	   System.out.println(info.getUser().getEmail());
	   System.out.println(info.getUser().getId());
	   
	   System.out.println("roles >>>>>>>>>>>>>>>>>>>>>>>>>> ");
	   System.out.println(info.getAllRoles());
	   
//	   for(UserRole r: user.getRoles()) {
//		   System.out.println(r.getRole());
//	   }

	   return "admin/dashboardPage";
   }
   
   
   
   

}
