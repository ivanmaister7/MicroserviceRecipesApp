package src.main.java.spring.project.recipes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient{

    private Long ingredientId;
    private String productName;
    private int quantity;
    private Unit unit;
}
