package agiliz.projetoAgiliz.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().authenticated()) // Seta que todas as requisições precisam de autenticação
                .httpBasic(withDefaults());
        return http.build();
    }
}

// _..__.          .__.._
// .^"-.._ '-(\__/)-' _..-"^.
//        '-.' oo '.-'
//           `-..-'  Uglúk
