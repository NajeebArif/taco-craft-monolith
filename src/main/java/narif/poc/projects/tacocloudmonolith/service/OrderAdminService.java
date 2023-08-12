package narif.poc.projects.tacocloudmonolith.service;

import lombok.RequiredArgsConstructor;
import narif.poc.projects.tacocloudmonolith.model.entity.TacoOrder;
import narif.poc.projects.tacocloudmonolith.repository.TacoOrderRepo;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderAdminService {

    private final TacoOrderRepo tacoOrderRepo;

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOrders(){
        tacoOrderRepo.deleteAll();
    }

    @PostAuthorize("hasRole('ADMIN') || " +
            "returnObject.deliveryName.equals(authentication.name)")
    public TacoOrder getOrder(Long id){
        return tacoOrderRepo.findById(id).orElseThrow();
    }
}
