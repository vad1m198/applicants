package org.dynamo.applicantsapp.model;

public class UserInfo {
 
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

    public UserInfo(String userName, String password, String firstName, String lastName) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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
}