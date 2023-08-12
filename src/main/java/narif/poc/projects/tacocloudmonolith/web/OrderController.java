package narif.poc.projects.tacocloudmonolith.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import narif.poc.projects.tacocloudmonolith.model.entity.TacoOrder;
import narif.poc.projects.tacocloudmonolith.model.entity.TacoUser;
import narif.poc.projects.tacocloudmonolith.repository.TacoOrderRepo;
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

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
@RequiredArgsConstructor
public class OrderController {

    private final TacoOrderRepo tacoOrderRepo;

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal TacoUser tacoUser, Model model){
        model.addAttribute("pageTitle", "Order Listings");
        model.addAttribute("orders", tacoOrderRepo.findAllByTacoUserOrderByPlacedAtDesc(tacoUser));
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
