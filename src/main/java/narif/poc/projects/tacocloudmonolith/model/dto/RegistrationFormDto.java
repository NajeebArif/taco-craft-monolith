package narif.poc.projects.tacocloudmonolith.model.dto;

import lombok.Data;
import narif.poc.projects.tacocloudmonolith.model.entity.TacoUser;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationFormDto {

    private String username;
    private String password;
    private String fullName;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public TacoUser toTacoUser(PasswordEncoder passwordEncoder) {
        return new TacoUser(username, passwordEncoder.encode(password),
                fullName, street, city, state, zip, phone);
    }
}
