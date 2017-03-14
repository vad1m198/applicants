package org.dynamo.applicantsapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 14.03.2017.
 */
public class UserFormInfo {

    private UserInfo userInfo;

    private List<UserRoleInfo> rolesInfo;    
//    private List<String> rolesInfo;
    
    public UserFormInfo() {
    	userInfo = new UserInfo();
//    	rolesInfo = new ArrayList<String>();
    	rolesInfo = new ArrayList<UserRoleInfo>();
    }

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

//	public List<String> getRolesInfo() {
//		return rolesInfo;
//	}
//
//	public void setRolesInfo(List<String> rolesInfo) {
//		this.rolesInfo = rolesInfo;
//	}
	
	public List<UserRoleInfo> getRolesInfo() {
		return rolesInfo;
	}

	public void setRolesInfo(List<UserRoleInfo> rolesInfo) {
		this.rolesInfo = rolesInfo;
	}

}
