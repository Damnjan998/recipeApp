package damnjan.recipe.recipeapp.serviceImpl;

import damnjan.recipe.recipeapp.commands.RecipeCommand;
import damnjan.recipe.recipeapp.converters.RecipeCommandToRecipe;
import damnjan.recipe.recipeapp.converters.RecipeToRecipeCommand;
import damnjan.recipe.recipeapp.domain.Recipe;
import damnjan.recipe.recipeapp.repositories.RecipeRepository;
import damnjan.recipe.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("Im in the service");

        return recipeRepository.findAllByOrderById();
    }

    @Override
    public Recipe findById(Long l) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(l);

        if (!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe Not Found!");
        }

        return recipeOptional.get();
    }

    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        assert detachedRecipe != null;
        recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + detachedRecipe.getId());
        return recipeToRecipeCommand.convert(detachedRecipe);
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long l) {
        return recipeToRecipeCommand.convert(findById(l));
    }

    public void deleteById(Long idToDelete) {
        recipeRepository.deleteById(idToDelete);
    }

}
