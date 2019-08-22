package org.fasttrackit.recipesholderapi.transfer.user;

public class UserResponse {

    private Long id;
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
