package spring.project.recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.project.recipes.domain.Recipe;
import spring.project.recipes.service.RecipeService;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/info")
    public ResponseEntity<?> getAllRecipesInfo() {
        return new ResponseEntity<>(recipeService.findAll(), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<?> getAllRecipesFullInfo() {
        return new ResponseEntity<>(recipeService.findAllWithFullInfo(), HttpStatus.CREATED);
    }
    @GetMapping("/info/{id}")
    public ResponseEntity<?> getRecipesInfoById(@PathVariable Long id) {
        return new ResponseEntity<>(recipeService.getById(id), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipesFullInfoById(@PathVariable Long id) {
        return new ResponseEntity<>(recipeService.getFullInfoById(id), HttpStatus.CREATED);
    }
//    @PostMapping("/add")
//    public ResponseEntity<?> saveRecipe(@RequestBody Recipe recipe) {
//        return new ResponseEntity<>(recipeService.saveRecipe(recipe), HttpStatus.CREATED);
//    }
    @PostMapping("/search")
    public ResponseEntity<?> findValidRecipe(@RequestBody List<String> products) {
        return new ResponseEntity<>(recipeService.findValidRecipe(products), HttpStatus.CREATED);
    }
}
