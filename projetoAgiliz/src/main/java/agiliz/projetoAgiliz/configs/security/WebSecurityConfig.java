package agiliz.projetoAgiliz.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig {
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

        return http.build();
    }

    // @Bean
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.inMemoryAuthentication()
    //             .withUser("agilizDev")
    //             .password("bc024d9a-719c-411c-ad7c-2193931e5efc")
    //             .roles("ADMIN");
    // }
}
/*
// _..__.          .__.._
// .^"-.._ '-(\__/)-' _..-"^.
//        '-.' oo '.-'
//           `-..-'  Uglúk
 */