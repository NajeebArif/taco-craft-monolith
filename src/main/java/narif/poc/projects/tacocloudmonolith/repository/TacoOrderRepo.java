package narif.poc.projects.tacocloudmonolith.repository;

import narif.poc.projects.tacocloudmonolith.model.TacoOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoOrderRepo extends JpaRepository<TacoOrder, Long> {
}
