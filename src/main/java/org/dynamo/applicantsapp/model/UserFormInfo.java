package org.dynamo.applicantsapp.model;

import java.util.List;

import org.dynamo.applicantsapp.entity.UserRole;

/**
 * Created by Вадим on 14.03.2017.
 */
public class UserFormInfo {

    private UserInfo userInfo;
    private List<UserRole> rolesInfo;
    private List<Integer> rolesIds;
    
    private boolean valid;

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	public List<UserRole> getRolesInfo() {
		return rolesInfo;
	}

	public void setRolesInfo(List<UserRole> rolesInfo) {
		this.rolesInfo = rolesInfo;
	}

	public List<Integer> getRolesIds() {
		return rolesIds;
	}

	public void setRolesIds(List<Integer> rolesIds) {
		this.rolesIds = rolesIds;
	}
	
	

}
