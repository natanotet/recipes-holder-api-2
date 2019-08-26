package org.fasttrackit.recipesholderapi.web;


import org.fasttrackit.recipesholderapi.domanin.FavoriteRecipes;
import org.fasttrackit.recipesholderapi.exception.ResourceNotFoundException;
import org.fasttrackit.recipesholderapi.service.FavoriteRecipesService;
import org.fasttrackit.recipesholderapi.transfer.favorite_recipes.AddRecipeToFavoriteRequest;
import org.fasttrackit.recipesholderapi.transfer.favorite_recipes.FavoritesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/favorite")
@CrossOrigin
public class FavoriteRecipesController {

    private FavoriteRecipesService favoriteRecipesService;

    @Autowired
    public FavoriteRecipesController(FavoriteRecipesService favoriteRecipesService) {
        this.favoriteRecipesService = favoriteRecipesService;
    }

    @PutMapping
    public ResponseEntity addRecipeToFavorite(@RequestBody @Valid AddRecipeToFavoriteRequest request) throws ResourceNotFoundException {
        favoriteRecipesService.addRecipeToFavoriteRecipe(request);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{recipeId}")
    public ResponseEntity <FavoritesResponse> getFavoriteRecipes(@PathVariable("recipeId") Long recipeId) throws ResourceNotFoundException {
        FavoritesResponse favoriteRecipes = favoriteRecipesService.getFavoriteRecipes(recipeId);
        return new ResponseEntity<>(favoriteRecipes, HttpStatus.OK);
    }
}
