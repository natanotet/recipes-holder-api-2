package org.fasttrackit.recipesholderapi.domanin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private Long id;
    //aceasta adnotare presupune ca field-urile din DB sa nu fie nule
    @NotNull
    private String recipeName;
    @NotNull
    private String recipeIngredients;
    @NotNull
    private String recipeHowTo;
    @NotNull
    private String recipeImagePath;



    @ManyToMany(mappedBy = "recipes")
    private Set<FavoriteRecipes> favoriteRecipesSet = new HashSet<>();

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

    public String getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public String getRecipeHowTo() {
        return recipeHowTo;
    }

    public void setRecipeHowTo(String recipeHowTo) {
        this.recipeHowTo = recipeHowTo;
    }

    public String getRecipeImagePath() {
        return recipeImagePath;
    }

    public void setRecipeImagePath(String recipeImagePath) {
        this.recipeImagePath = recipeImagePath;
    }

    public Set<FavoriteRecipes> getFavoriteRecipesSet() {
        return favoriteRecipesSet;
    }

    public void setFavoriteRecipesSet(Set<FavoriteRecipes> favoriteRecipesSet) {
        this.favoriteRecipesSet = favoriteRecipesSet;
    }
}
