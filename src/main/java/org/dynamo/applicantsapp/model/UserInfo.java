package org.dynamo.applicantsapp.model;

public class UserInfo {
 
	private String id;
   private String userName;
   private String password;
    private String firstName;
    private String lastName;

   public UserInfo()  {
        
   }
 
   // Do not change this constructor, it used in hibernate Query.
   public UserInfo(String userName, String password) {
       this.userName = userName;
       this.password = password;
       
   }

    public UserInfo(String userName, String password, String firstName, String lastName, String id) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }
 
   public String getUserName() {
       return userName;
   }
 
   public void setUserName(String userName) {
       this.userName = userName;
   }
 
   public String getPassword() {
       return password;
   }
 
   public void setPassword(String password) {
       this.password = password;
   }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public String getId() {
		return this.id;
	}
	
	public String setId(String id) {
		return this.id = id;
	}
}