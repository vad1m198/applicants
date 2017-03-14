package org.dynamo.applicantsapp.controller;

import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.dynamo.applicantsapp.entity.User;
import org.dynamo.applicantsapp.entity.UserRole;
import org.dynamo.applicantsapp.model.UserFormInfo;
import org.dynamo.applicantsapp.model.UserRoleInfo;
import org.dynamo.applicantsapp.service.UserRoleService;
import org.dynamo.applicantsapp.service.UserService;
import org.dynamo.applicantsapp.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
    
    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
    	dataBinder.registerCustomEditor(UserRoleInfo.class, new UserInfoRolePropertyEditor());
//        Object target = dataBinder.getTarget();
//        if (target == null) {
//            return;
//        }
////        System.out.println("Target=" + target);
//
//        if (target.getClass() == UserFormInfo.class) {
//        	System.out.println(" target UserFormInfo >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        	UserFormInfo uf = (UserFormInfo) target;
//        	System.out.println("conver to UserFormInfo >>>>>");
//        	System.out.println(uf.getUserInfo());
//        	System.out.println(uf.getRolesInfo());
//        }
 
    }
	
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
			   List<UserRoleInfo> rolesInfo = roles.stream()
					   .map(role -> new UserRoleInfo(role, false))
					   .collect(Collectors.toList());
					   
			   
//			   List<String> rolesInfo = roles.stream()
//					   .map(role -> role.getRole())
//					   .collect(Collectors.toList());
					   
			   info.setRolesInfo(rolesInfo);
		   }
		   
		   System.out.println("getUserForm() userInfo >>>>>>>>>>>>>>>>>>>>> ");
		   System.out.println(info.getRolesInfo());
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
           @ModelAttribute("userFormInfo") UserFormInfo info) {

	   User user = new User(info);
	   System.out.println("Email: " + user.getEmail());
	   System.out.println("First name: " + user.getFirst_name());
	   System.out.println("Last Name:" + user.getLast_name());
	   System.out.println("Id: " + user.getId());
	   System.out.println("Password: " + user.getPassword());
	   System.out.println("user roles: " + user.getRoles());
	   System.out.println("userInfo roles: " + info.getRolesInfo());
	   
	   
//	   System.out.println("user roles >>>>>>>>>>>>>>>>>>>>>>>>>> ");
//	   for(UserRole r : user.getRoles()) {
//		   System.out.println(r.getId());
//		   System.out.println(r.getRole());
//	   }
	   
	   System.out.println("userInfo roles >>>>>>>>>>>>>>>>>>>>>>>>>> ");
	   for(UserRoleInfo r : info.getRolesInfo()) {
		   System.out.println(r.getRole());
		   
	   }

	   return "admin/dashboardPage";
   }
   
   private final class UserInfoRolePropertyEditor extends PropertyEditorSupport {
				
		public void setAsText(String incomingId) {
			UserRole role = userRoleService.getRoleById(Integer.parseInt(incomingId));
	        System.out.println(incomingId + " PROPERTY EDITOR ROLE " + role);
	        setValue(new UserRoleInfo(role, false));
	    }

	    public String getAsText() {
	        System.out.println("PROPERTY EDITOR ID " + ((UserRoleInfo)getValue()).getId());
	        return String.valueOf(((UserRoleInfo)getValue()).getId());
	    }

	}
   
   

}
