package org.fasttrackit.recipesholderapi.domanin;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class FavoriteRecipes {

    @Id

    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name=("favorite_recipe"),
            joinColumns = @JoinColumn(name="favorite_id")
    ,inverseJoinColumns = @JoinColumn(name="recipe_id"))
    private Set<Recipe> recipes = new HashSet<>();

    public void addRecipe(Recipe recipe){
        //adauga reteta in favorite
        recipes.add(recipe);
        recipe.getFavoriteRecipesSet().add(this);
    }

    public void removeRecipe (Recipe recipe){
        recipes.remove(recipe);
        recipe.getFavoriteRecipesSet().remove(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
}
