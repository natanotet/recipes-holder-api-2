package org.fasttrackit.recipesholderapi.web;


import org.fasttrackit.recipesholderapi.domanin.Recipe;
import org.fasttrackit.recipesholderapi.service.RecipeService;
import org.fasttrackit.recipesholderapi.transfer.GetRecipeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
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


}
