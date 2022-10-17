package src.main.java.spring.project.recipes.responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import src.main.java.spring.project.recipes.domain.Ingredient;
import src.main.java.spring.project.recipes.domain.Recipe;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
@AllArgsConstructor
public class RecipeResponce {
    private Long recipeId;
    private String recipeName;
    private String info;
    private List<Ingredient> ingredients;

    public RecipeResponce(Recipe recipe, List<Ingredient> ingredients) {
        this.recipeId = recipe.getRecipeId();
        this.recipeName = recipe.getRecipeName();
        this.info = recipe.getInfo();
        this.ingredients = ingredients;
    }
}
