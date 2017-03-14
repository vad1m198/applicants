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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + (selected ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRoleInfo other = (UserRoleInfo) obj;
		if (id != other.id)
			return false;		
		return true;
	}
	
	
	
	

}
