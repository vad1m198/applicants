package org.dynamo.applicantsapp.entity;

import javax.persistence.*;

import org.dynamo.applicantsapp.model.UserFormInfo;
import org.hibernate.annotations.Proxy;

import java.util.List;


@Entity
@Proxy(lazy = false)
@Table(name = "users")
public class User {
 
    private int id;
    private String firstName;
    private String lastName;
    private String email;
	private String password;
	
	private List<UserRole> roles;
	 
	public User() {
		
	}
	 
	public User(UserFormInfo info) {
		this.id = info.getUserInfo().getId();
		this.firstName = info.getUserInfo().getFirstName().trim();
		this.lastName = info.getUserInfo().getLastName().trim();
		this.email = info.getUserInfo().getEmail().trim();
		this.password = info.getUserInfo().getPassword().trim();
	}

	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinTable(name = "user_role_id_user_id", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
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
    @Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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