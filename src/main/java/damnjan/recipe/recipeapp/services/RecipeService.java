package damnjan.recipe.recipeapp.services;

import damnjan.recipe.recipeapp.commands.RecipeCommand;
import damnjan.recipe.recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand findCommandById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(Long idToDelete);
}
