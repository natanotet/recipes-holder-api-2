package org.fasttrackit.recipesholderapi;


import org.fasttrackit.recipesholderapi.domanin.FavoriteRecipes;
import org.fasttrackit.recipesholderapi.domanin.Recipe;
import org.fasttrackit.recipesholderapi.domanin.User;
import org.fasttrackit.recipesholderapi.exception.ResourceNotFoundException;
import org.fasttrackit.recipesholderapi.service.FavoriteRecipesService;
import org.fasttrackit.recipesholderapi.service.RecipeService;
import org.fasttrackit.recipesholderapi.service.UserService;
import org.fasttrackit.recipesholderapi.transfer.Recipe.CreateRecipeRequest;
import org.fasttrackit.recipesholderapi.transfer.Recipe.RecipeResponse;
import org.fasttrackit.recipesholderapi.transfer.favorite_recipes.AddRecipeToFavoriteRequest;
import org.fasttrackit.recipesholderapi.transfer.favorite_recipes.FavoritesResponse;
import org.fasttrackit.recipesholderapi.transfer.user.CreateUserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FavoriteRecipesIntegrationTest {

    @Autowired
    private FavoriteRecipesService favoriteRecipesService;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private UserService userService;
    @Test
    public void testAddToFavorite_whenValidRequest_thenCreateFavoriteRecipe() throws ResourceNotFoundException {

        CreateRecipeRequest request = new CreateRecipeRequest();

        request.setRecipeName("Paine Prajita");
        request.setRecipeIngredients("paine, ulei, usturoi ");
        request.setRecipeHowTo("se baga painea feliata in prajitor, se apasa clapeta in jos si se asteapta pana painea sare din prajitor direct in farfurie");
        request.setRecipeImagePath("https://images.app.goo.gl/dvfWuD3UE7ZhmKJR8");

        Recipe createdRecipe = recipeService.createRecipe(request);


        assertThat(createdRecipe, notNullValue());
        assertThat(createdRecipe.getId(), greaterThan(0L));
        assertThat(createdRecipe.getRecipeName(), is(request.getRecipeName()));
        assertThat(createdRecipe.getRecipeIngredients(), is(request.getRecipeIngredients()));
        assertThat(createdRecipe.getRecipeHowTo(), is(request.getRecipeHowTo()));
        assertThat(createdRecipe.getRecipeImagePath(), is(request.getRecipeImagePath()));

        CreateUserRequest request2 = new CreateUserRequest();
        request2.setUserName("Vasile Bucatarul");

        User createUser = userService.createUser(request2);

        assertThat(createUser, notNullValue());
        assertThat(createUser.getUserName(), is(request2.getUserName()));

       AddRecipeToFavoriteRequest request3 = new AddRecipeToFavoriteRequest();
       request3.setUserId(createUser.getId());
       request3.setRecipeId(createdRecipe.getId());


        favoriteRecipesService.addRecipeToFavoriteRecipe(request3);

        FavoritesResponse favoritesResponse = favoriteRecipesService.getFavoriteRecipes(createUser.getId());

        assertThat(favoritesResponse, notNullValue());
        assertThat(favoritesResponse.getUser(), notNullValue());
        assertThat(favoritesResponse.getUser().getId(), is(createUser.getId()));
        assertThat(favoritesResponse.getUser().getUserName(), is(createUser.getUserName()));

        assertThat(favoritesResponse.getRecipes(), notNullValue());
        assertThat(favoritesResponse.getRecipes(), hasSize(1));

        RecipeResponse firstRecipe = favoritesResponse.getRecipes().iterator().next();

        assertThat(firstRecipe, notNullValue());
        assertThat(firstRecipe.getRecipeName(), is(createdRecipe.getRecipeName()));

    }
}
