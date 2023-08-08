package narif.poc.projects.tacocloudmonolith.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import narif.poc.projects.tacocloudmonolith.model.Ingredient;
import narif.poc.projects.tacocloudmonolith.model.Ingredient.Type;
import narif.poc.projects.tacocloudmonolith.model.Taco;
import narif.poc.projects.tacocloudmonolith.model.TacoOrder;
import narif.poc.projects.tacocloudmonolith.repository.IngredientRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/design")
@Slf4j
@SessionAttributes("tacoOrder")
@RequiredArgsConstructor
public class DesignTacoController {

    private final IngredientRepo ingredientRepo;

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


    private List<Ingredient> getIngredients() {
        return ingredientRepo.findAll();
    }

    private static List<Ingredient> filterByType(Type type, List<Ingredient> ingredients) {
        return ingredients.stream().filter(i -> i.getType().equals(type)).toList();
    }

}
