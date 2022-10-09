package src.main.java.spring.project.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import src.main.java.spring.project.recipes.domain.Ingredient;

import java.util.Collection;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Long> {

    @Query(value = " SELECT DISTINCT r.recipeId " +
        "FROM Ingredient r " +
        "WHERE r.product.productId IN :products " +
        "AND r.recipeId NOT IN ( " +
                "SELECT rr.recipeId " +
                "FROM Ingredient rr " +
                "WHERE rr.product.productId NOT IN :products ) " )
    List<Long> findRecipesByQueryProducts(@Param("products") Collection<Long> products);

    List<Ingredient> findIngredientsByRecipeId(Long recipeId);
}
