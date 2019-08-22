package org.fasttrackit.recipesholderapi.web;


import org.fasttrackit.recipesholderapi.domanin.Recipe;
import org.fasttrackit.recipesholderapi.exception.ResourceNotFoundException;
import org.fasttrackit.recipesholderapi.service.RecipeService;
import org.fasttrackit.recipesholderapi.transfer.Recipe.CreateRecipeRequest;
import org.fasttrackit.recipesholderapi.transfer.Recipe.GetRecipeRequest;
import org.fasttrackit.recipesholderapi.transfer.Recipe.UpdateRecipeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/recipes")
@CrossOrigin
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    //this is an endpoint
    @GetMapping
    public ResponseEntity<Page<Recipe>> getRecipes(GetRecipeRequest request, Pageable pageable){

        Page<Recipe> response = recipeService.getRecipes(request,pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Recipe>createProduct(@RequestBody @Valid CreateRecipeRequest request){
        Recipe response = recipeService.createRecipe(request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRecipe(@PathVariable Long id){
        recipeService.deteleRecipe(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Recipe> getRecipe(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Recipe recipe = recipeService.getRecipe(id);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") Long id, @RequestBody@Valid UpdateRecipeRequest request) throws ResourceNotFoundException {
        Recipe recipe = recipeService.updateRecipe(id, request);

        return new ResponseEntity(recipe, HttpStatus.NO_CONTENT);
    }




}
