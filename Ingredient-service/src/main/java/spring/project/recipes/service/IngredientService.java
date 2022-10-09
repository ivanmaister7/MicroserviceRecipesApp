package src.main.java.spring.project.recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.main.java.spring.project.recipes.domain.Ingredient;
import src.main.java.spring.project.recipes.repository.IngredientRepository;
import src.main.java.spring.project.recipes.responce.IngredientResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Long> findRecipesByQueryProducts(List<Long> productsId) {
        return ingredientRepository.findRecipesByQueryProducts(productsId);
    }
    public List<Ingredient> findAll(){
        return ingredientRepository.findAll();
    }
    public List<Ingredient> findIngredientsByRecipeId(Long recipeId){
        return ingredientRepository.findIngredientsByRecipeId(recipeId);
    }

    public Ingredient findById(Long ingredientId) {
        return ingredientRepository.findById(ingredientId).orElseThrow(() -> new RuntimeException("Invalid Ingredient Id"));
    }

    public List<IngredientResponse> convertToResponseEntity(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(ingredient -> new IngredientResponse(
                        ingredient.getIngredientId(),
                        ingredient.getProduct().getProductName(),
                        ingredient.getQuantity(),
                        ingredient.getUnit()
                ))
                .collect(Collectors.toList());
    }
}
