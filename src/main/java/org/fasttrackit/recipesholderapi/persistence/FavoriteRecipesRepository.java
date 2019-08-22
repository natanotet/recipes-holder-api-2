package org.fasttrackit.recipesholderapi.persistence;

import org.fasttrackit.recipesholderapi.domanin.FavoriteRecipes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRecipesRepository extends JpaRepository <FavoriteRecipes, Long> {


}
