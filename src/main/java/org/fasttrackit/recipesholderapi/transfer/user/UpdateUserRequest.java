package org.fasttrackit.recipesholderapi.transfer.user;

public class UpdateUserRequest {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UpdateUserRequest{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
