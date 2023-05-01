package academy.devdojo.springboot2.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
@Log4j2
public class SecurityConfig {
    //private final UserService systemUserDetailsService;

    private PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/animes/admin/**").hasRole("ADMIN")
                        .requestMatchers("/animes/**").hasRole("USER")
                        .anyRequest()
                        .authenticated())
                .formLogin().and().httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails admin = User.withUsername("sergio-admin")
                .password(encoder.encode("kratos"))
                .roles("USER", "ADMIN")
                .build();

        UserDetails user = User.withUsername("sergio-user")
                .password(encoder.encode("kratos"))
                .roles("USER")
                .build();

        log.info(encoder.encode("kratos"));

        return new InMemoryUserDetailsManager(user, admin);
    }

}