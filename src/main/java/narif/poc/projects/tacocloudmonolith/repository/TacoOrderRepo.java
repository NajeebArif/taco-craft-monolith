package narif.poc.projects.tacocloudmonolith.repository;

import narif.poc.projects.tacocloudmonolith.model.entity.TacoOrder;
import narif.poc.projects.tacocloudmonolith.model.entity.TacoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacoOrderRepo extends JpaRepository<TacoOrder, Long> {

    List<TacoOrder> findAllByTacoUserOrderByPlacedAtDesc(TacoUser tacoUser);
}
