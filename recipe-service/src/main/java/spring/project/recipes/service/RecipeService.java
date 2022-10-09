package spring.project.recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import spring.project.recipes.domain.Ingredient;
import spring.project.recipes.domain.Recipe;
import spring.project.recipes.repository.RecipeRepository;
import spring.project.recipes.responce.RecipeResponce;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RecipeRepository recipeRepository;


//    public Recipe saveRecipe(Recipe recipe) {
//        Recipe newRecipe = new Recipe();
//        newRecipe.setRecipeName(recipe.getRecipeName());
//        newRecipe.setInfo(recipe.getInfo());
//        newRecipe.getIngredients().addAll((recipe.getIngredients()
//                .stream()
//                .map(ingredient -> {
//                    Product product = productService.findProductById(ingredient.getProduct().getProductId());
//                    Ingredient newIngredient = new Ingredient();
//                    newIngredient.setProduct(product);
//                    newIngredient.setRecipe(newRecipe);
//                    newIngredient.setQuantity(ingredient.getQuantity());
//                    newIngredient.setUnit(ingredient.getUnit());
//                    return newIngredient;
//                })
//                .collect(Collectors.toList())
//        ));
//        return recipeRepository.save(newRecipe);
//    }

    public List<Recipe> getRecipesByIds(List<Long> ids){
        return recipeRepository.findAllByIds(ids);
    }

    public List<Recipe> findAll() {
        return recipeRepository.findAllRecipe();
    }

    public List<RecipeResponce> findAllWithFullInfo() {
        return convertToRecipeResponse(findAll());
    }

    private List<RecipeResponce> convertToRecipeResponse(List<Recipe> recipes) {
        return recipes.stream()
                .map(recipe -> new RecipeResponce(
                        recipe,
                        loadIngredientsByRecipeId(recipe.getRecipeId())
                ))
                .collect(Collectors.toList());
    }

    private List<Ingredient> loadIngredientsByRecipeId(Long recipeId) {
        return List.of(
                Objects.requireNonNull(
                        restTemplate.getForObject("http://localhost:8001/ingredient/recipe/" + recipeId, Ingredient[].class)));
    }

    public Recipe getById(Long recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(() -> new RuntimeException("Invalid Recipe Id"));
    }

    public RecipeResponce getFullInfoById(Long recipeId) {
        return new RecipeResponce(getById(recipeId),
                List.of(
                        Objects.requireNonNull(
                                restTemplate.getForObject("http://localhost:8001/ingredient/recipe/" + recipeId, Ingredient[].class))));
    }

    public List<RecipeResponce> findValidRecipe(List<String> products) {
        HttpHeaders headers;
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<String>> request = new HttpEntity<>(products, headers);

        List<Long> validRecipesIds = List.of(
                Objects.requireNonNull(
                restTemplate.postForEntity(
                        "http://localhost:8001/ingredient/search", request, Long[].class).getBody()));
        return convertToRecipeResponse(getRecipesByIds(validRecipesIds));
    }
}
