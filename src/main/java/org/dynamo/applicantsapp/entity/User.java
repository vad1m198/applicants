package org.dynamo.applicantsapp.entity;

import javax.persistence.*;

import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {
 
    private int id;
    private String first_name;
    private String last_name;
    private String email;
	private String password;
	
	 private List<UserRole> roles;
	 
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name = "user_role_id_user_id", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	@Id
    @Column(name = "id")
	public int getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}
    @Column(name = "first_name")
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	@Column(name = "last_name")
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
    
 
 
}