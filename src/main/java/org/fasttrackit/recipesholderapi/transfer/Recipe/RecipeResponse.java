package org.fasttrackit.recipesholderapi.transfer.Recipe;

import java.util.Objects;

public class RecipeResponse {

    private Long id;
    private String recipeName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeResponse that = (RecipeResponse) o;
        return id.equals(that.id) &&
                recipeName.equals(that.recipeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recipeName);
    }

    @Override
    public String toString() {
        return "RecipeResponse{" +
                "id=" + id +
                ", recipeName='" + recipeName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
}
