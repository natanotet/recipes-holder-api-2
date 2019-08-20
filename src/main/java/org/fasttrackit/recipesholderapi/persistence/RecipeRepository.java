package org.fasttrackit.recipesholderapi.persistence;

import org.fasttrackit.recipesholderapi.domanin.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Page<Recipe> findByRecipeNameContaining(String partialName, Pageable pageable);
    Page<Recipe> findRecipesByRecipeNameContainingAndRecipeIngredients(String partialName2, Pageable pageable);

}
