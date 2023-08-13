package narif.poc.projects.tacocloudmonolith.config;

import lombok.extern.slf4j.Slf4j;
import narif.poc.projects.tacocloudmonolith.model.entity.Ingredient;
import narif.poc.projects.tacocloudmonolith.model.entity.Ingredient.Type;
import narif.poc.projects.tacocloudmonolith.model.entity.Taco;
import narif.poc.projects.tacocloudmonolith.model.entity.TacoUser;
import narif.poc.projects.tacocloudmonolith.repository.IngredientRepo;
import narif.poc.projects.tacocloudmonolith.repository.TacoRepo;
import narif.poc.projects.tacocloudmonolith.repository.TacoUserRepo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@Configuration
@Slf4j
@Profile({"!prod", "!qa"})
public class DataConfig {

    @Bean
    public ApplicationRunner dataLoader(IngredientRepo ingredientRepo,
                                        TacoUserRepo tacoUserRepo,
                                        PasswordEncoder encoder,
                                        TacoRepo tacoRepo){
        return args -> {
            ingredientRepo.saveAll(getIngredients());
            log.info("Ingredients Loaded!");
            tacoUserRepo.saveAll(createUser(encoder));
            log.info("Users added.");

            saveTaco(tacoRepo);
        };
    }

    private static void saveTaco(TacoRepo tacoRepo) {
        Ingredient flourTortilla = new Ingredient(
                "FLTO", "Flour Tortilla", Type.WRAP);
        Ingredient cornTortilla = new Ingredient(
                "COTO", "Corn Tortilla", Type.WRAP);
        Ingredient groundBeef = new Ingredient(
                "GRBF", "Ground Beef", Type.PROTEIN);
        Ingredient carnitas = new Ingredient(
                "CARN", "Carnitas", Type.PROTEIN);
        Ingredient tomatoes = new Ingredient(
                "TMTO", "Diced Tomatoes", Type.VEGGIES);
        Ingredient lettuce = new Ingredient(
                "LETC", "Lettuce", Type.VEGGIES);
        Ingredient cheddar = new Ingredient(
                "CHED", "Cheddar", Type.CHEESE);
        Ingredient jack = new Ingredient(
                "JACK", "Monterrey Jack", Type.CHEESE);
        Ingredient salsa = new Ingredient(
                "SLSA", "Salsa", Type.SAUCE);
        Ingredient sourCream = new Ingredient(
                "SRCR", "Sour Cream", Type.SAUCE);

        Taco taco1 = new Taco();
        taco1.setName("Carnivore");
        taco1.setIngredients(Arrays.asList(
                flourTortilla, groundBeef, carnitas,
                sourCream, salsa, cheddar));
        tacoRepo.save(taco1);

        Taco taco2 = new Taco();
        taco2.setName("Bovine Bounty");
        taco2.setIngredients(Arrays.asList(
                cornTortilla, groundBeef, cheddar,
                jack, sourCream));
        tacoRepo.save(taco2);

        Taco taco3 = new Taco();
        taco3.setName("Veg-Out");
        taco3.setIngredients(Arrays.asList(
                flourTortilla, cornTortilla, tomatoes,
                lettuce, salsa));
        tacoRepo.save(taco3);
    }

    private static List<Ingredient> getIngredients() {
        return Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
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
