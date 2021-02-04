package damnjan.recipe.recipeapp.repositories;

import damnjan.recipe.recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
