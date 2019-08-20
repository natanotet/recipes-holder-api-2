package org.fasttrackit.recipesholderapi.transfer;

import javax.validation.constraints.NotNull;

public class CreateRecipeRequest {

    @NotNull
    private String recipeName;
    @NotNull
    private String recipeIngredients;
    @NotNull
    private String recipeHowTo;
    @NotNull
    private String recipeImagePath;

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

    @Override
    public String toString() {
        return "CreateRecipeRequest{" +
                "recipeName='" + recipeName + '\'' +
                ", recipeIngredients='" + recipeIngredients + '\'' +
                ", recipeHowTo='" + recipeHowTo + '\'' +
                ", recipeImagePath='" + recipeImagePath + '\'' +
                '}';
    }
}
