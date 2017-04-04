package org.dynamo.util;
import javax.servlet.http.HttpServletRequest;

import org.dynamo.model.CartInfo;

public class Utils {
 
    // Products in Cart, stored in Session.
    public static CartInfo getCartInSession(HttpServletRequest request) {
 
        // Get Cart from Session.
        CartInfo cartInfo = (CartInfo) request.getSession().getAttribute("cartInfo");
        
        // If null, create it.
        if (cartInfo == null) {
            cartInfo = new CartInfo();
            
            // And store to Session.
            request.getSession().setAttribute("cartInfo", cartInfo);
        }
 
        return cartInfo;
    }
 
    public static void removeCartInSession(HttpServletRequest request) {
        request.getSession().removeAttribute("cartInfo");
    }

//	public static ShoppingCartAnswer getAnswersInSession(HttpServletRequest request) {
//		ShoppingCartAnswer answerInfo = (ShoppingCartAnswer) request.getSession().getAttribute("shoppingCartAnswers");
//        if (answerInfo == null) {
//        	answerInfo = new ShoppingCartAnswer();
//            request.getSession().setAttribute("shoppingCartAnswers", answerInfo);
//        }
// 
//        return answerInfo;
//	}

//	@SuppressWarnings("unchecked")
//	public static List<User> getUsersInSession(HttpServletRequest request) {
//		String usersAttrName = "allUsers";
//		return (List<User>) request.getSession().getAttribute(usersAttrName);        
//	}
//	
//	public static void setUsersInSession(HttpServletRequest request, List<User> users) {
//		String usersAttrName = "allUsers";
//		request.getSession().removeAttribute(usersAttrName);
//		request.getSession().setAttribute(usersAttrName, users);
//	}
//	
//	public static UserFormInfo getUserFormInSession(HttpServletRequest request) {
//		String usersAttrName = "userFormInfo";
//		return (UserFormInfo) request.getSession().getAttribute(usersAttrName);		
//	}
//	
//    // Products in Cart, stored in Session.
//    public static UserFormInfo getUserFormInSession(HttpServletRequest request) {
// 
//        // Get Cart from Session.
//    	UserFormInfo info = (UserFormInfo) request.getSession().getAttribute("userForm");
//        
//        // If null, create it.
//        if (info == null) {
//        	info = new UserFormInfo();
//        	info.setUserInfo(new UserInfo());
//        	info.setRolesIds(new ArrayList<Integer>());
//            // And store to Session.
//            request.getSession().setAttribute("userForm", info);
//        }
// 
//        return info;
//    }
//	
//	public static void setUserFormInSession(HttpServletRequest request, UserFormInfo info) {
//		String usersAttrName = "userFormInfo";
//		request.getSession().removeAttribute(usersAttrName);
//		request.getSession().setAttribute(usersAttrName, info);		
//	}
//	
//	public static void setUserFormInSession(HttpServletRequest request, User user) {
//		String usersAttrName = "userForm";
//		request.getSession().removeAttribute(usersAttrName);
//		request.getSession().setAttribute(usersAttrName, user);       
//	}
//	
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
