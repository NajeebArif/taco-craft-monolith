package narif.poc.projects.tacocloudmonolith.web;

import lombok.RequiredArgsConstructor;
import narif.poc.projects.tacocloudmonolith.model.entity.Ingredient;
import narif.poc.projects.tacocloudmonolith.repository.IngredientRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
public class IngredientRestController {

    private final IngredientRepo ingredientRepo;

    @GetMapping
    public List<Ingredient> getAllIngredients(@RequestParam Optional<Integer> page,
                                              @RequestParam Optional<Integer> size) {
        Pageable pageable = PageRequest.of(page.orElse(0),
                size.orElse(12),
                Sort.by("name").ascending());
        return ingredientRepo.findAll(pageable).getContent();
    }
}
