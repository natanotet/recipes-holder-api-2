package org.fasttrackit.recipesholderapi.transfer;

import org.springframework.data.domain.Page;

public class GetRecipeRequest {

    private String partiaName;

    private String partialName2;

    public String getPartialName2() {
        return partialName2;
    }

    public void setPartialName2(String partialName2) {
        this.partialName2 = partialName2;
    }

    public String getPartiaName() {
        return partiaName;
    }

    public void setPartiaName(String partiaName) {
        this.partiaName = partiaName;
    }

    @Override
    public String toString() {
        return "GetRecipeRequest{" +
                "partiaName='" + partiaName + '\'' +
                ", partialName2='" + partialName2 + '\'' +
                '}';
    }
}
