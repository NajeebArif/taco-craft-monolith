package narif.poc.projects.tacocloudmonolith.config;

import lombok.extern.slf4j.Slf4j;
import narif.poc.projects.tacocloudmonolith.model.Ingredient;
import narif.poc.projects.tacocloudmonolith.repository.IngredientRepo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@Slf4j
public class DataConfig {

    @Bean
    public ApplicationRunner dataLoader(IngredientRepo ingredientRepo){
        return args -> {
            ingredientRepo.saveAll(getIngredients());
            log.info("Ingredients Loaded!");
        };
    }

    private static List<Ingredient> getIngredients() {
        return Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        );
    }
}
