package damnjan.recipe.recipeapp.services;

import damnjan.recipe.recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
