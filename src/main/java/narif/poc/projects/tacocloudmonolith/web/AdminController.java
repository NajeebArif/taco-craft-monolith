package narif.poc.projects.tacocloudmonolith.web;

import lombok.RequiredArgsConstructor;
import narif.poc.projects.tacocloudmonolith.service.OrderAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final OrderAdminService orderAdminService;

    @GetMapping
    public String adminPage(){
        return "adminPage";
    }

    @PostMapping("/deleteOrders")
    public String deleteAllOrders(){
        orderAdminService.deleteAllOrders();
        return "redirect:/admin";
    }
}
