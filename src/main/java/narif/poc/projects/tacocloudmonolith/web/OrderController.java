package narif.poc.projects.tacocloudmonolith.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import narif.poc.projects.tacocloudmonolith.model.entity.TacoOrder;
import narif.poc.projects.tacocloudmonolith.repository.TacoOrderRepo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
@RequiredArgsConstructor
public class OrderController {

    private final TacoOrderRepo tacoOrderRepo;

    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus){
        if(errors.hasErrors()){
            return "orderForm";
        }
        tacoOrderRepo.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
