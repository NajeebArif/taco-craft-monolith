package narif.poc.projects.tacocloudmonolith.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import narif.poc.projects.tacocloudmonolith.model.Ingredient;
import narif.poc.projects.tacocloudmonolith.model.Ingredient.Type;
import narif.poc.projects.tacocloudmonolith.model.Taco;
import narif.poc.projects.tacocloudmonolith.model.TacoOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/design")
@Slf4j
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors,
                              @ModelAttribute TacoOrder order){
        if(errors.hasErrors()){
            return "design";
        }
        order.addTaco(taco);
        log.info("Processing Taco: {}", taco);
        return "redirect:/orders/current";
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = getIngredients();
        Type[] values = Type.values();
        for (Type type : values) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(type, ingredients));
        }
    }

    @ModelAttribute("tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute("taco")
    public Taco taco() {
        return new Taco();
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

    private static List<Ingredient> filterByType(Type type, List<Ingredient> ingredients) {
        return ingredients.stream().filter(i -> i.getType().equals(type)).toList();
    }

}
