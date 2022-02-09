package com.rodrigues.rodrigues.model.dto;

import com.rodrigues.rodrigues.model.entities.User;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier
public class UserResponse {

    private String name;
    private String email;


    public UserResponse userToUserResponse(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        return this;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
