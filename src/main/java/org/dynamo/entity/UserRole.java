package org.dynamo.entity;

public class UserRole {

	private long id;
    private String role;

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
