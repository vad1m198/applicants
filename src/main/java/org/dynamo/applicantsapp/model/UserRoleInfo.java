package org.dynamo.applicantsapp.model;

import org.dynamo.applicantsapp.entity.UserRole;

public class UserRoleInfo {
	
	private int id;
    private String role;
    private boolean selected;
    
    public UserRoleInfo() {
    	
    }
    
    public UserRoleInfo(UserRole role, boolean selected) {
    	this.id = role.getId();
    	this.role = role.getRole();
    	this.selected = selected;
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	

}
