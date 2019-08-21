package org.fasttrackit.recipesholderapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.recipesholderapi.domanin.Recipe;
import org.fasttrackit.recipesholderapi.exception.ResourceNotFoundException;
import org.fasttrackit.recipesholderapi.persistence.RecipeRepository;
import org.fasttrackit.recipesholderapi.transfer.CreateRecipeRequest;
import org.fasttrackit.recipesholderapi.transfer.GetRecipeRequest;
import org.fasttrackit.recipesholderapi.transfer.UpdateRecipeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeService.class);


    private final RecipeRepository recipeRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, ObjectMapper objectMapper) {
        this.recipeRepository = recipeRepository;
        this.objectMapper = objectMapper;
    }

    public Recipe createRecipe(CreateRecipeRequest request) {

        LOGGER.info("Creating Recipe {}", request);

        Recipe recipe = objectMapper.convertValue(request, Recipe.class);
//        Recipe recipe = new Recipe();
//        recipe.setRecipeName(request.getRecipeName());
//        recipe.setRecipeIngredients(request.getRecipeIngredients());
//        recipe.setRecipeHowTo(request.getRecipeHowTo());
//        recipe.setRecipeImagePath(request.getRecipeImagePath());

        return recipeRepository.save(recipe);
    }

    public Recipe getRecipe(long id) throws ResourceNotFoundException {

        LOGGER.info("Retrieving recipe {}", id);
        return recipeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Recipe" + id + " does not exist"
                ));
    }

    public Recipe updateRecipe(long id, UpdateRecipeRequest request) throws ResourceNotFoundException {

        LOGGER.info("Updateding recipe {} with {}", id, request);

        Recipe recipe = getRecipe(id);

        BeanUtils.copyProperties(request, recipe);

        return recipeRepository.save(recipe);
    }

    public void deteleRecipe(long id) {
        LOGGER.info("deleting recipe {} ", id);

        recipeRepository.deleteById(id);

        LOGGER.info("deleted recipe {} ", id);

    }

    public Page<Recipe> getRecipes(GetRecipeRequest request, Pageable pageable) {

        LOGGER.info("Retriving Recipes {} ", request);

        if (request.getPartiaName() != null && request.getPartialName2() != null) {

            return recipeRepository.findRecipesByRecipeNameContainingAndRecipeIngredients(request.getPartialName2(), pageable);
        } else if (request.getPartialName2() != null) {

            return recipeRepository.findByRecipeNameContaining(request.getPartiaName(), pageable);

        }


        return recipeRepository.findAll(pageable);
    }
}