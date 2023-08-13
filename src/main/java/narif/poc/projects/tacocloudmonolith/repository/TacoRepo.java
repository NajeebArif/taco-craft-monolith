package narif.poc.projects.tacocloudmonolith.repository;

import narif.poc.projects.tacocloudmonolith.model.entity.Taco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepo extends JpaRepository<Taco, Long> {


}
