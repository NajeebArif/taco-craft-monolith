package narif.poc.projects.tacocloudmonolith.web;

import lombok.RequiredArgsConstructor;
import narif.poc.projects.tacocloudmonolith.model.dto.RegistrationFormDto;
import narif.poc.projects.tacocloudmonolith.repository.TacoUserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final TacoUserRepo tacoUserRepo;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String registerForm(){
        return "/register";
    }

    @PostMapping
    public String registerUser(RegistrationFormDto registrationFormDto){
        tacoUserRepo.save(registrationFormDto.toTacoUser(passwordEncoder));
        return "redirect:/login";
    }


}
