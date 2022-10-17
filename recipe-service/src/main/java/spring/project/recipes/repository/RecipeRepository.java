package src.main.java.spring.project.recipes.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import src.main.java.spring.project.recipes.domain.Recipe;

import java.util.Collection;
import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    @Query(value = " SELECT r " +
            "FROM Recipe r " +
            "WHERE r.recipeId IN :ids" )
    List<Recipe> findAllByIds(@Param("ids") Collection<Long> ids);

    @Query(value = " SELECT r " +
            "FROM Recipe r ")
    List<Recipe> findAllRecipe();
}
