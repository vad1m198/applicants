package org.dynamo.applicantsapp.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class MainController {
  
   @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
   public String loginPage(Model model, Principal principal) {
       return "loginPage";
   }
   
   @RequestMapping("/403")
   public String accessDenied() {
       return "/403";
   }
 
   @RequestMapping(value = "/welcome", method = RequestMethod.GET)
   public String userInfo(Authentication authentication) {	   
       // After user login successfully.
	   if(authentication == null) {
		   return "redirect:/login";
	   }
	   
       String userName = authentication.getName();
       System.out.println("User Name: "+ userName);
       
       Boolean isAdmin = authentication.getAuthorities()
		   		.stream()
		   		.filter(a -> a.getAuthority().contains("ADMIN_USER"))
		   		.findFirst().isPresent();
		   
       if(isAdmin) {
    	   return "redirect:/admin/dashboard";
       }
       
       return "welcomePage";
   }
   
   @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
   public String logoutSuccessfulPage(Model model) {
       return "logoutSuccessfulPage";
   }
}