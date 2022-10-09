package src.main.java.spring.project.recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.main.java.spring.project.recipes.service.IngredientService;
import src.main.java.spring.project.recipes.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllIngredients() {
        return new ResponseEntity<>(ingredientService.findAll(), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getIngredientById(@PathVariable Long id) {
        return new ResponseEntity<>(ingredientService.findById(id), HttpStatus.CREATED);
    }
    @GetMapping("/recipe/{id}")
    public ResponseEntity<?> getAllIngredientsByRecipeId(@PathVariable Long id) {
        return new ResponseEntity<>(ingredientService.convertToResponseEntity(ingredientService.findIngredientsByRecipeId(id)), HttpStatus.CREATED);
    }
    @PostMapping("/search")
    public ResponseEntity<?> findValidRecipe(@RequestBody List<String> products) {
        List<Long> productsId = productService.findProductsByNames(products);
        return new ResponseEntity<>(ingredientService.findRecipesByQueryProducts(productsId), HttpStatus.CREATED);
    }
}
