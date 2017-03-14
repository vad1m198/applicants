package org.dynamo.applicantsapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 14.03.2017.
 */
public class UserFormInfo {

    private UserInfo userInfo;

    private List<UserRoleInfo> rolesInfo;
    
    public UserFormInfo() {
    	userInfo = new UserInfo();
    	rolesInfo = new ArrayList<UserRoleInfo>();
    }

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<UserRoleInfo> getRolesInfo() {
		return rolesInfo;
	}

	public void setRolesInfo(List<UserRoleInfo> rolesInfo) {
		this.rolesInfo = rolesInfo;
	}

}
