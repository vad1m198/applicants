package org.dynamo.applicantsapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.dynamo.applicantsapp.entity.User;
import org.dynamo.applicantsapp.entity.UserRole;
import org.dynamo.applicantsapp.model.UserFormInfo;
import org.dynamo.applicantsapp.model.UserInfo;
import org.dynamo.applicantsapp.service.UserRoleService;
import org.dynamo.applicantsapp.service.UserService;
import org.dynamo.applicantsapp.util.Utils;
import org.dynamo.applicantsapp.validator.UserFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AdminController {
	
    @Autowired
    private UserFormValidator userFormValidator;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRoleService userRoleService;
    
    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        } else if (target.getClass() == UserFormInfo.class) {
            dataBinder.setValidator(userFormValidator);
        }
    }
 
	
   @RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
   public String getDashboard(HttpServletRequest request) {
	   List<User> users = userService.getAllUsers();
	   request.getSession().removeAttribute("allUsers");
	   request.getSession().setAttribute("allUsers", users);
	   return "admin/dashboardPage";
   }
   
   @RequestMapping(value = "/admin/userForm", method = RequestMethod.GET)
   public String getUserForm(HttpServletRequest request, Model model, @RequestParam(value = "id", defaultValue = "") String id) {
	   UserFormInfo info = null;
//	   Utils.getUserFormInSession(request);
//	   List<UserRole> roles = userRoleService.getAllRoles();
//	   info.setRolesInfo(roles);
	   if(!id.isEmpty()) {		   
		   Integer idInt = null;
		   try {
			   idInt = Integer.parseInt(id);
		   } catch(NumberFormatException nfe) {
			   nfe.printStackTrace();
		   }
		   
		   if(idInt != null) {
			   List<User> users = userService.getAllById(idInt);
			   if(users != null && !users.isEmpty()) {
				   User user = users.get(0);
				   info = new UserFormInfo();
				   info.setUserInfo(new UserInfo(user));
				   List<UserRole> roles = userRoleService.getAllRoles();
				   info.setRolesInfo(roles);

				   				   
				   List<Integer> roleIds = user.getRoles()
						   			.stream()
						   			.map(r -> r.getId())
						   			.collect(Collectors.toList());
				   
				   info.setRolesIds(roleIds);
			   }
		   }
	   } else {
		   info = new UserFormInfo();
		   info.setUserInfo(new UserInfo());
		   List<UserRole> roles = userRoleService.getAllRoles();
		   info.setRolesInfo(roles);
	   }
	   model.addAttribute("userFormInfo",info);
	   
	   return "admin/userFormPage";
   }
   
   @RequestMapping(value = "/admin/userForm", method = RequestMethod.POST)
   public String saveUser(HttpServletRequest request, //
           Model model, //
           @ModelAttribute("userFormInfo") @Validated UserFormInfo info,
           BindingResult result, //
           final RedirectAttributes redirectAttributes) {	   
       // If has Errors.
       if (result.hasErrors()) {
    	   info.setValid(false);

           // Forward to reenter customer info.
    	   if(info.getRolesInfo() == null || info.getRolesInfo().isEmpty()) {
    		   List<UserRole> roles = userRoleService.getAllRoles();
			   info.setRolesInfo(roles);
		   }
           return "admin/userFormPage";
       }
       info.setValid(true);
       System.out.println("info.getRolesInfo() >>>>>>>>>>>>>>>>>>>>>>>> " + info.getRolesInfo());
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
	   
	   return "redirect:dashboard";
   }
}
