package org.fasttrackit.recipesholderapi.transfer.user;

import javax.validation.constraints.NotNull;

public class CreateUserRequest {

    @NotNull
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "CreateUserRequest{" +
                "userName='" + userName + '\'' +
                '}';
    }
}

