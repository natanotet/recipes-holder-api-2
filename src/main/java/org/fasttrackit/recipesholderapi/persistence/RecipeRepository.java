package org.fasttrackit.recipesholderapi.persistence;

import org.fasttrackit.recipesholderapi.domanin.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
