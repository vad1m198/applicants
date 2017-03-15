package org.dynamo.applicantsapp.entity;

import javax.persistence.*;


import java.util.Set;

@Entity
@Table(name = "user_roles")
public class UserRole {

	private int id;
    private String role;
    private Set<User> users;
	
	public UserRole() {
	}
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.TABLE)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		UserRole other = (UserRole) obj;
		if (id != other.id)
			return false;		
		return true;
	}
	
	

}
