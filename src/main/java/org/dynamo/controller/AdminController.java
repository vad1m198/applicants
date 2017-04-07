package org.dynamo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.dynamo.dao.ShoppingCartAnswerDao;
import org.dynamo.dao.UserDao;
import org.dynamo.dao.UserRoleDao;
import org.dynamo.entity.CustomEmail;
import org.dynamo.entity.User;
import org.dynamo.entity.UserRole;
import org.dynamo.model.UserFormInfo;
import org.dynamo.service.MailService;
import org.dynamo.util.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private ShoppingCartAnswerDao answerdao;
	
	@Autowired
	private MailService mailservice;
	
	@RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
	public String getDashboard(Model model,@ModelAttribute("userSearch") User user) {
		List<User> users = null;
		if(user != null && (user.getFirstName() != null || user.getSecondName() != null)) {			
			users = userDao.getAllUsersByFnameAndSname(user.getFirstName().trim(), user.getSecondName().trim());
		} else {
			user = new User();
			users = userDao.getAllUsers();
		}
		model.addAttribute("userSearch", user);
		model.addAttribute("users", users);
		return "admin/dashboard";
	}
	
	@RequestMapping(value = {"/details"}, method = RequestMethod.GET)
	public String getDetails(@RequestParam(value = "id", defaultValue = "") long id,
			Model model) {
		model.addAttribute("userDetailsInfo", userDao.findUserById(id));
		model.addAttribute("shoppingCartAnswersInfo", answerdao.getAnswerByUserId(id));		
		return "admin/details";
	}
	
	@RequestMapping(value = {"/edit"}, method = RequestMethod.GET)
	public String getEditView(@RequestParam(value = "id", defaultValue = "0") long id,
			Model model) {
		User user = null;
		
//		if(id > 0) {
			user = userDao.findUserById(id);
//		}
		
		UserFormInfo info = new UserFormInfo();		
		List<UserRole> roles = userRoleDao.getAllRoles();
		
		if(user == null) {
			info.setUser(new User());
			info.setSelectedRoles(new ArrayList<>());
		} else {
			info.setUser(user);
			List<Long> selectedRoles = new ArrayList<>();
			for(UserRole role: user.getRoles()) {
				selectedRoles.add(role.getId());
			}
			info.setSelectedRoles(selectedRoles);
		}
		info.setAllRoles(roles);
		
		model.addAttribute("userFormInfo", info);
		return "admin/edit";
	}
	
	@RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
	public String postToEditView(@Valid @ModelAttribute("userFormInfo") UserFormInfo info,
            BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			if(info.getAllRoles() == null || info.getAllRoles().isEmpty()) {
				info.setAllRoles(userRoleDao.getAllRoles());
			}
			return "admin/edit";
		}
		
		User user = info.getUser();
		List<UserRole> roles = userRoleDao.getAllRoles().stream()
				.filter(r -> info.getSelectedRoles().contains(r.getId()))
				.collect(Collectors.toList());
		
		user.setRoles(roles);
		
		long userId = 0;

		try {
			if(info.getUser().getId() > 0) {
				userId = userDao.updateUser(info.getUser().getId(), user);
			} else {
				user.setPassword(UUID.randomUUID().toString().replace("-", "").substring(0, 8));
				userId = userDao.createUser(user);
				CustomEmail mail = MailUtils.getCreateUserMail(user);
				mailservice.sendEmail(mail);
			}
		} catch(DuplicateKeyException e) {
			result.rejectValue("user.email", "user.email", "A user already exists for this email.");
			info.setAllRoles(userRoleDao.getAllRoles());
			return "admin/edit";
		}
		return "redirect:/admin/details?id=" + userId;
		
	}

}
