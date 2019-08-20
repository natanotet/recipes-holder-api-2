package org.fasttrackit.recipesholderapi;

import org.fasttrackit.recipesholderapi.domanin.Recipe;
import org.fasttrackit.recipesholderapi.exception.ResourceNotFoundException;
import org.fasttrackit.recipesholderapi.service.RecipeService;
import org.fasttrackit.recipesholderapi.transfer.CreateRecipeRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIntegrationTest {

	@Autowired
	private RecipeService recipeService;

	@Test
	public void testCreateRecipe_wthenValidRequest_thenReturnCreatedRecipe() {

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


	}

	@Test(expected = TransactionSystemException.class)
	public void testCreateRecipe_whenMissingMandatoryProperties_thenTrowExceptiom(){
		CreateRecipeRequest request = new CreateRecipeRequest();

		recipeService.createRecipe(request);

	}

	@Test
	public void testGetRecipebyId_whenValidRequest_thenReturnAskedRecipe () throws ResourceNotFoundException {

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

		Recipe recipe = recipeService.getRecipe(createdRecipe.getId());

		assertThat(recipe, notNullValue());
		assertThat(recipe.getId(), is(createdRecipe.getId()));
		assertThat(recipe.getRecipeName(), is(createdRecipe.getRecipeName()));
		assertThat(recipe.getRecipeIngredients(), is(createdRecipe.getRecipeIngredients()));
		assertThat(recipe.getRecipeHowTo(), is(createdRecipe.getRecipeHowTo()));
		assertThat(recipe.getRecipeImagePath(), is(createdRecipe.getRecipeImagePath()));
	}
	@Test(expected = ResourceNotFoundException.class)
	public void testGetRecipe_when_notExistingId_thentrowResourceNotFoundException() throws ResourceNotFoundException {
		recipeService.getRecipe(9999L);

	}

}
