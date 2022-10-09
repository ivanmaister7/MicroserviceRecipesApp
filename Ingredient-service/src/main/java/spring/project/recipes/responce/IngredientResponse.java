package src.main.java.spring.project.recipes.responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import src.main.java.spring.project.recipes.domain.Unit;

@Data
@AllArgsConstructor
public class IngredientResponse {
   private Long ingredientId;
   private String productName;
   private int quantity;
   private Unit unit;
}
