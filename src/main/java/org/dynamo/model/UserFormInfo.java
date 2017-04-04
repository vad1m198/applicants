package org.dynamo.model;

import java.util.List;

import javax.validation.Valid;

import org.dynamo.entity.User;
import org.dynamo.entity.UserRole;
import org.hibernate.validator.constraints.NotEmpty;

public class UserFormInfo {
	@Valid
	private User user;
	private List<UserRole> allRoles;
	@NotEmpty
	private List<Long> selectedRoles;
	

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<UserRole> getAllRoles() {
		return allRoles;
	}
	public void setAllRoles(List<UserRole> allRoles) {
		this.allRoles = allRoles;
	}
	public List<Long> getSelectedRoles() {
		return selectedRoles;
	}
	public void setSelectedRoles(List<Long> selectedRoles) {
		this.selectedRoles = selectedRoles;
	}
}
