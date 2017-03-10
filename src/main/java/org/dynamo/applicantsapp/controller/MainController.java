package org.dynamo.applicantsapp.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

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
   public String userInfo(HttpServletRequest request, Model model, Principal principal) {	   
       // After user login successfully.
	   if(principal == null) {
		   return "redirect:/login";
	   }
       String userName = principal.getName();
       System.out.println("User Name: "+ userName);
       return "welcomePage";
   }
   
   @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
   public String logoutSuccessfulPage(Model model) {
       return "logoutSuccessfulPage";
   }
}