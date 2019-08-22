package org.fasttrackit.recipesholderapi.transfer.user;

public class GetUserRequest {

    String partialUserName;

    public String getPartialUserName() {
        return partialUserName;
    }

    public void setPartialUserName(String partialUserName) {
        this.partialUserName = partialUserName;
    }

    @Override
    public String toString() {
        return "GetUserRequest{" +
                "partialUserName='" + partialUserName + '\'' +
                '}';
    }
}
