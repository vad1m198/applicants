package org.dynamo.applicantsapp.validator;
 
import org.dynamo.applicantsapp.entity.User;
import org.dynamo.applicantsapp.model.UserFormInfo;
import org.dynamo.applicantsapp.service.UserService;

import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
 
@Component
public class UserFormValidator implements Validator {
 
    private EmailValidator emailValidator = EmailValidator.getInstance();
    
    @Autowired
    private UserService userService;
 
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == UserFormInfo.class;
    }
 
    @Override
    public void validate(Object target, Errors errors) {
    	UserFormInfo userFormInfo = (UserFormInfo) target;
 
        // Check the fields of CustomerInfo class.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userInfo.firstName", "NotEmpty.userForm.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userInfo.lastName", "NotEmpty.userForm.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userInfo.email", "NotEmpty.userForm.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userInfo.password", "NotEmpty.userForm.required");
                
        if (userFormInfo.getUserInfo().getFirstName().length() < 3 || userFormInfo.getUserInfo().getFirstName().length() > 36) {
        	errors.rejectValue("userInfo.firstName", "Length.userForm.Name");
        }
        
        if (userFormInfo.getUserInfo().getLastName().length() < 3 || userFormInfo.getUserInfo().getLastName().length() > 36) {
        	errors.rejectValue("userInfo.lastName", "Length.userForm.Name");
        }
        
        if (!emailValidator.isValid(userFormInfo.getUserInfo().getEmail())) {
            errors.rejectValue("userInfo.email", "Pattern.userForm.email");
        } else if(userFormInfo.getUserInfo().getEmail().length() > 50){
        	errors.rejectValue("userInfo.email", "Length.userForm.Email");
        }
        
        List<User> users = userService.getAllByEmail(userFormInfo.getUserInfo().getEmail());
        
        if(!users.isEmpty()){
        	for(User u: users) {
        		System.out.println(u.getId() + " : " + userFormInfo.getUserInfo().getId());
        		if(u.getId() != userFormInfo.getUserInfo().getId()) {
        			errors.rejectValue("userInfo.email", "Pattern.userForm.emailExist");
        			break;
        		}
        	}
        } 
        	
    	if(userFormInfo.getUserInfo().getPassword().length() > 16 || userFormInfo.getUserInfo().getPassword().length() < 5) {
        	errors.rejectValue("userInfo.password", "Length.userForm.Password");
        }
        
        if(userFormInfo.getRolesIds() == null || userFormInfo.getRolesIds().isEmpty()) {
        	errors.rejectValue("rolesIds", "Pattern.userForm.roleIds");
        }
        
    }
 
}