package narif.poc.projects.tacocloudmonolith.repository;

import narif.poc.projects.tacocloudmonolith.model.entity.TacoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TacoUserRepo extends JpaRepository<TacoUser, Long> {

    Optional<TacoUser> findByUsername(String username);
}
