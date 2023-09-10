package narif.poc.projects.tacocloudmonolith.config;

import narif.poc.projects.tacocloudmonolith.repository.TacoUserRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
                        .requestMatchers("/design", "/orders/**").hasRole("USER")
//                        .requestMatchers(HttpMethod.POST, "/data-api/ingredients").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/data-api/ingredients").hasAnyAuthority("SCOPE_writeIngredients", "ROLE_ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/data-api/ingredients/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/data-api/ingredients/**").hasAnyAuthority("SCOPE_deleteIngredients", "ROLE_ADMIN")
                        .requestMatchers("/","/webjars/**", "/images/**","*.css","*.js*", "/login", "/register").permitAll()
                                .anyRequest().authenticated()
//                        .requestMatchers("/api/**", "/data-api/**").authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/design")
                        .permitAll()
                ).logout(logoutConfigurer -> logoutConfigurer
                        .logoutSuccessUrl("/")
                        .permitAll()
                ).oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(TacoUserRepo tacoUserRepo) {
        return username -> tacoUserRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
