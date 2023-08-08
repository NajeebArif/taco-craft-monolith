package narif.poc.projects.tacocloudmonolith.config;

import narif.poc.projects.tacocloudmonolith.repository.TacoUserRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                    .requestMatchers("/design","/orders**").hasRole("USER")
                    .requestMatchers("/","/**").permitAll()
                )
                .formLogin(formLogin->formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/design")
                ).logout(logoutConfigurer -> logoutConfigurer
                        .logoutSuccessUrl("/")
                );
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(TacoUserRepo tacoUserRepo) {
        return username -> tacoUserRepo.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User '"+username+"' not found"));
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}