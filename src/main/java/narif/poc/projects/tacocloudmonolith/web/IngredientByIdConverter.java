package narif.poc.projects.tacocloudmonolith.web;

import lombok.RequiredArgsConstructor;
import narif.poc.projects.tacocloudmonolith.model.entity.Ingredient;
import narif.poc.projects.tacocloudmonolith.repository.IngredientRepo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepo ingredientRepo;

    @Override
    public Ingredient convert(String id) {
        return ingredientRepo.findById(id).orElse(null);
    }
}
