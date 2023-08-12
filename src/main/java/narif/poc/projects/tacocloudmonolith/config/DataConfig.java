package narif.poc.projects.tacocloudmonolith.config;

import lombok.extern.slf4j.Slf4j;
import narif.poc.projects.tacocloudmonolith.model.entity.Ingredient;
import narif.poc.projects.tacocloudmonolith.model.entity.TacoUser;
import narif.poc.projects.tacocloudmonolith.repository.IngredientRepo;
import narif.poc.projects.tacocloudmonolith.repository.TacoUserRepo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@Configuration
@Slf4j
public class DataConfig {

    @Bean
    public ApplicationRunner dataLoader(IngredientRepo ingredientRepo,
                                        TacoUserRepo tacoUserRepo,
                                        PasswordEncoder encoder){
        return args -> {
            ingredientRepo.saveAll(getIngredients());
            log.info("Ingredients Loaded!");
            tacoUserRepo.saveAll(createUser(encoder));
            log.info("Users added.");
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

    private static List<TacoUser> createUser(PasswordEncoder passwordEncoder){
        String username = "admin";
        String role = "ROLE_ADMIN";
        String password = passwordEncoder.encode("test");
        String fullName = "Admin Admin";
        return List.of(createUser(username, password, fullName, role),
                createUser("narif", password, "Narif", "ROLE_USER"));
    }

    private static TacoUser createUser(String username, String password, String fullName, String role) {
        return new TacoUser(username, password, fullName, "test",
                "test", "test", "801505", "12345", role);
    }
}
