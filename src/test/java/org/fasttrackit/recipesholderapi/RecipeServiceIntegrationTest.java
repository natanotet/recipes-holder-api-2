package org.fasttrackit.recipesholderapi;

import org.fasttrackit.recipesholderapi.domanin.Recipe;
import org.fasttrackit.recipesholderapi.exception.ResourceNotFoundException;
import org.fasttrackit.recipesholderapi.service.RecipeService;
import org.fasttrackit.recipesholderapi.transfer.Recipe.CreateRecipeRequest;
import org.fasttrackit.recipesholderapi.transfer.Recipe.UpdateRecipeRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.hamcrest.CoreMatchers.*;
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

	@Test
	public void testUpdateRECIPE_whenvalidrequest_thenReturnUpdateRecipe() throws ResourceNotFoundException {
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

		UpdateRecipeRequest request1 = new UpdateRecipeRequest();
		request1.setRecipeName("Salam Prajit");
		request1.setRecipeIngredients(createdRecipe.getRecipeIngredients() + "300g salam Victoria, ulei");
		request1.setRecipeHowTo(createdRecipe.getRecipeHowTo() + "Se taie salamult felii si se pune in tigaia cu ulei incins");
		request1.setRecipeImagePath(createdRecipe.getRecipeImagePath() + "https://images.app.goo.gl/hpXsxFxXHbmaQyWE9");

		Recipe updatedRecipe = recipeService.updateRecipe(createdRecipe.getId(), request1);

		assertThat(updatedRecipe, notNullValue());
		assertThat(updatedRecipe.getId(), is(createdRecipe.getId()));
		assertThat(updatedRecipe.getRecipeName(), is(request1.getRecipeName()));
		assertThat(updatedRecipe.getRecipeName(), not(is(createdRecipe.getRecipeName())));
		assertThat(updatedRecipe.getRecipeIngredients(), is(request1.getRecipeIngredients()));
		assertThat(updatedRecipe.getRecipeIngredients(), not(createdRecipe.getRecipeName()));
		assertThat(updatedRecipe.getRecipeHowTo(), is(request1.getRecipeHowTo()));
		assertThat(updatedRecipe.getRecipeHowTo(), not(is(createdRecipe.getRecipeHowTo())));
		assertThat(updatedRecipe.getRecipeImagePath(), is(request1.getRecipeImagePath()));
		assertThat(updatedRecipe.getRecipeImagePath(), not(is(createdRecipe.getRecipeImagePath())));

	}

}
