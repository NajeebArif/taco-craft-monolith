package narif.poc.projects.tacocloudmonolith.repository;

import narif.poc.projects.tacocloudmonolith.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepo extends JpaRepository<Ingredient, String> {
}
