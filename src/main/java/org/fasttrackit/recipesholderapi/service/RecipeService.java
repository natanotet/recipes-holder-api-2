package org.fasttrackit.recipesholderapi.service;

import org.fasttrackit.recipesholderapi.domanin.Recipe;
import org.fasttrackit.recipesholderapi.exception.ResourceNotFoundException;
import org.fasttrackit.recipesholderapi.persistence.RecipeRepository;
import org.fasttrackit.recipesholderapi.transfer.CreateRecipeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeService.class);


    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe createRecipe(CreateRecipeRequest request) {

        LOGGER.info ("Creating Recipe {}", request);

        Recipe recipe = new Recipe();
        recipe.setRecipeName(request.getRecipeName());
        recipe.setRecipeIngredients(request.getRecipeIngredients());
        recipe.setRecipeHowTo(request.getRecipeHowTo());
        recipe.setRecipeImagePath(request.getRecipeImagePath());

        return recipeRepository.save(recipe);
    }

    public Recipe getRecipe (long id) throws ResourceNotFoundException {

        LOGGER.info ("Retrieving recipe {}", id);
        return recipeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Recipe" + id + " does not exist"
                ));
    }

}