package org.fasttrackit.recipesholderapi.domanin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Recipes {

    @Id
    @GeneratedValue
    private Long id;
    private String recipeName;
    private String recipeIngredients;
    private String recipeHowTo;
    private String recipeImagePath;

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
}
