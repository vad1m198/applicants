package org.dynamo.applicantsapp.entity;

import javax.persistence.*;

import org.dynamo.applicantsapp.model.UserRoleInfo;

import java.util.Set;

@Entity
@Table(name = "user_roles")
public class UserRole {

	private int id;
    private String role;
    private Set<User> users;
    
	public UserRole(UserRoleInfo info) {
		this.id = info.getId();
		this.role = info.getRole();
	}
	
	public UserRole() {
	}
	
	@Id
    @Column(name = "id")
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "role")
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@ManyToMany(mappedBy = "roles")
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
