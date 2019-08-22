package org.fasttrackit.recipesholderapi.transfer.favorite_recipes;

public class AddRecipeToFavoriteRequest {

        private Long userId;
        private Long recipeId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public String toString() {
        return "AddRecipeToFavoriteRequest{" +
                "userId=" + userId +
                ", recipeId=" + recipeId +
                '}';
    }
}
