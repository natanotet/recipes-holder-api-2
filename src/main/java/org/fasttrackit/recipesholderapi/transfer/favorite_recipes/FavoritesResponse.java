package org.fasttrackit.recipesholderapi.transfer.favorite_recipes;

import org.fasttrackit.recipesholderapi.transfer.Recipe.RecipeResponse;
import org.fasttrackit.recipesholderapi.transfer.user.UserResponse;

import java.util.HashSet;
import java.util.Set;

public class FavoritesResponse {

    private UserResponse user;
    private Long id;

    private Set<RecipeResponse> recipes = new HashSet<>();

    public Set<RecipeResponse> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<RecipeResponse> recipes) {
        this.recipes = recipes;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FavoritesResponse{" +
                "user=" + user +
                ", id=" + id +
                '}';
    }
}
