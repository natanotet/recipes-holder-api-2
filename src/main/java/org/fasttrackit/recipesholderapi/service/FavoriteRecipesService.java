package org.fasttrackit.recipesholderapi.service;

import org.fasttrackit.recipesholderapi.domanin.FavoriteRecipes;
import org.fasttrackit.recipesholderapi.domanin.Recipe;
import org.fasttrackit.recipesholderapi.domanin.User;
import org.fasttrackit.recipesholderapi.exception.ResourceNotFoundException;
import org.fasttrackit.recipesholderapi.persistence.FavoriteRecipesRepository;
import org.fasttrackit.recipesholderapi.transfer.Recipe.RecipeResponse;
import org.fasttrackit.recipesholderapi.transfer.favorite_recipes.AddRecipeToFavoriteRequest;
import org.fasttrackit.recipesholderapi.transfer.favorite_recipes.FavoritesResponse;
import org.fasttrackit.recipesholderapi.transfer.user.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FavoriteRecipesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeService.class);
    private final FavoriteRecipesRepository favoriteRecipesRepository;
    private final UserService userService;
    private final RecipeService recipeService;

    @Autowired
    public FavoriteRecipesService(FavoriteRecipesRepository favoriteRecipesRepository, UserService userService, RecipeService recipeService) {
        this.favoriteRecipesRepository = favoriteRecipesRepository;
        this.userService = userService;
        this.recipeService = recipeService;
    }

    @Transactional
    public void addRecipeToFavoriteRecipe(AddRecipeToFavoriteRequest request) throws ResourceNotFoundException {

        LOGGER.info("Saving to favorites {}", request);

        User user = userService.getUser(request.getUserId());

        FavoriteRecipes favorite = favoriteRecipesRepository.findById(request.getUserId()).orElse(
                (new FavoriteRecipes()));

        if (favorite.getUser() == null) {
            favorite.setUser(user);
        }
        favorite.setUser(user);

        Recipe recipe = recipeService.getRecipe(request.getRecipeId());
        favorite.addRecipe(recipe);


        favoriteRecipesRepository.save(favorite);
    }
    @Transactional
    public FavoritesResponse getFavoriteRecipes(Long userId) throws ResourceNotFoundException {

        FavoriteRecipes favoriteRecipes = favoriteRecipesRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Favorite recipe " + userId + " does niot exist"));

        UserResponse userResponse = new UserResponse();
        userResponse.setId(favoriteRecipes.getUser().getId());
        userResponse.setUserName(favoriteRecipes.getUser().getUserName());

        FavoritesResponse favoritesResponse = new FavoritesResponse();

        favoritesResponse.setId(favoriteRecipes.getId());
        favoritesResponse.setUser(userResponse);

        favoriteRecipes.getRecipes().forEach(recipe -> {
            RecipeResponse recipeResponse = new RecipeResponse();
            recipeResponse.setId(recipe.getId());
            recipeResponse.setRecipeName(recipe.getRecipeName());

            favoritesResponse.getRecipes().add(recipeResponse);

        });


        return favoritesResponse;
    }


}
