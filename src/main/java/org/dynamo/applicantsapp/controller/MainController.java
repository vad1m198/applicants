package org.dynamo.applicantsapp.controller;

import java.security.Principal;
 
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
@Transactional
public class MainController {
  
   @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
   public String loginPage(Model model ) {        
       return "loginPage";
   }
 
//   @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
//   public String logoutSuccessfulPage(Model model) {
//       model.addAttribute("title", "Logout");
//       return "logoutSuccessfulPage";
//   }
 
   @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
   public String userInfo(Model model, Principal principal) {
 
       // After user login successfully.
       String userName = principal.getName();
 
       System.out.println("User Name: "+ userName);
 
       return "userInfoPage";
   }
   
   @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
   public String logoutSuccessfulPage(Model model) {
       return "logoutSuccessfulPage";
   }
}