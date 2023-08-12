package narif.poc.projects.tacocloudmonolith.web;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import narif.poc.projects.tacocloudmonolith.config.OrderProps;
import narif.poc.projects.tacocloudmonolith.model.entity.TacoOrder;
import narif.poc.projects.tacocloudmonolith.model.entity.TacoUser;
import narif.poc.projects.tacocloudmonolith.repository.TacoOrderRepo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.time.Instant;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
@RequiredArgsConstructor
public class OrderController {

    private final TacoOrderRepo tacoOrderRepo;
    private final OrderProps orderProps;

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal TacoUser tacoUser, Model model){
        model.addAttribute("pageTitle", "Order Listings");
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
        List<TacoOrder> allByTacoUserOrderByPlacedAtDesc = tacoOrderRepo.
                findAllByTacoUserOrderByPlacedAtDesc(tacoUser, pageable);
        model.addAttribute("orders", allByTacoUserOrderByPlacedAtDesc);
        return "orderList";
    }

    @GetMapping("/current")
    public String orderForm(Model model){
        model.addAttribute("pageTitle", "Submit Order");
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus, @AuthenticationPrincipal TacoUser tacoUser){
        if(errors.hasErrors()){
            return "orderForm";
        }
        order.setTacoUser(tacoUser);
        order.setPlacedAt(Instant.now());
        tacoOrderRepo.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }

}
