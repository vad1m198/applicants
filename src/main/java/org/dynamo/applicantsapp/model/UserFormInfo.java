package org.dynamo.applicantsapp.model;

import org.dynamo.applicantsapp.entity.User;
import org.dynamo.applicantsapp.entity.UserRole;

import java.util.List;

/**
 * Created by Вадим on 14.03.2017.
 */
public class UserFormInfo {

    private User user;

    private List<UserRole> allRoles;


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
}
