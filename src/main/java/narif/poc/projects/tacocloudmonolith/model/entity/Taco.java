package narif.poc.projects.tacocloudmonolith.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.Instant;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@RestResource(rel="tacos", path="tacos")
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long.")
    private String name;
    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient.")
    @ManyToMany
    private List<Ingredient> ingredients;

    private Instant createdAt;

    @PrePersist
    public void initDates(){
        createdAt = Instant.now();
    }

}
