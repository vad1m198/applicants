package org.dynamo.applicantsapp.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dynamo.applicantsapp.entity.ShoppingCartAnswer;
import org.dynamo.applicantsapp.entity.User;
import org.dynamo.applicantsapp.entity.UserRole;
import org.dynamo.applicantsapp.model.CartInfo;
import org.dynamo.applicantsapp.model.UserFormInfo;

public class Utils {
 
    // Products in Cart, stored in Session.
    public static CartInfo getCartInSession(HttpServletRequest request) {
 
        // Get Cart from Session.
        CartInfo cartInfo = (CartInfo) request.getSession().getAttribute("myCart");
        
        // If null, create it.
        if (cartInfo == null) {
            cartInfo = new CartInfo();
            
            // And store to Session.
            request.getSession().setAttribute("myCart", cartInfo);
        }
 
        return cartInfo;
    }
 
    public static void removeCartInSession(HttpServletRequest request) {
        request.getSession().removeAttribute("myCart");
    }

	public static ShoppingCartAnswer getAnswersInSession(HttpServletRequest request) {
		ShoppingCartAnswer answerInfo = (ShoppingCartAnswer) request.getSession().getAttribute("shoppingCartAnswers");
        if (answerInfo == null) {
        	answerInfo = new ShoppingCartAnswer();
            request.getSession().setAttribute("shoppingCartAnswers", answerInfo);
        }
 
        return answerInfo;
	}

	@SuppressWarnings("unchecked")
	public static List<User> getUsersInSession(HttpServletRequest request) {
		String usersAttrName = "allUsers";
		return (List<User>) request.getSession().getAttribute(usersAttrName);        
	}
	
	public static void setUsersInSession(HttpServletRequest request, List<User> users) {
		String usersAttrName = "allUsers";
		request.getSession().removeAttribute(usersAttrName);
		request.getSession().setAttribute(usersAttrName, users);       
	}
	
	public static UserFormInfo getUserFormInSession(HttpServletRequest request) {
		String usersAttrName = "userFormInfo";
		return (UserFormInfo) request.getSession().getAttribute(usersAttrName);		
	}
	
//	public static void setUserFormInSession(HttpServletRequest request, User user) {
//		String usersAttrName = "userForm";
//		request.getSession().removeAttribute(usersAttrName);
//		request.getSession().setAttribute(usersAttrName, user);       
//	}
	
//	@SuppressWarnings("unchecked")
//	public static List<UserRole> getUserRolesInSession(HttpServletRequest request) {
//		String usersAttrName = "allUserRoles";
//		return (List<UserRole>) request.getSession().getAttribute(usersAttrName);        
//	}
//	
//	public static void setUserRolesInSession(HttpServletRequest request, List<UserRole> userRoles) {
//		String usersAttrName = "allUserRoles";
//		request.getSession().setAttribute(usersAttrName, userRoles);       
//	}
 
//    public static void storeLastOrderedCartInSession(HttpServletRequest request, CartInfo cartInfo) {
//        request.getSession().setAttribute("lastOrderedCart", cartInfo);
//    }
//    
//    public static CartInfo getLastOrderedCartInSession(HttpServletRequest request) {
//        return (CartInfo) request.getSession().getAttribute("lastOrderedCart");
//    }
 
}
