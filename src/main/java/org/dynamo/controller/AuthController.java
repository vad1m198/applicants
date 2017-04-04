package org.dynamo.controller;

import java.security.Principal;

import org.dynamo.model.LoginFormInfo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {

	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String getRootPage(Model model) {
		return "redirect:/login";
	}
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String getLoginPage(Model model, Principal principal) {
	    if(principal != null) {
	        return "redirect:/welcome";
        }
		model.addAttribute("loginForm", new LoginFormInfo());		
		return "login";
	}

	@RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
	public String getWelcomePage(Model model,Authentication authentication) {
	    if(authentication.isAuthenticated()
                && authentication.getAuthorities()
                .stream()
                .filter(auth -> auth.getAuthority().equals("ADMIN")).count() > 0 ) {
	        return"redirect:/admin/dashboard";
        }
		return "welcome";
	}

}
