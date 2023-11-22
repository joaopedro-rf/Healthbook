package com.example.Healthbook.config;

import com.example.Healthbook.service.HealthbookUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final HealthbookUserDetailsService healthbookUserDetailsService;
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                authorize-> authorize
                        .requestMatchers("/swagger-ui").permitAll()
                        .requestMatchers("/pacients").permitAll()
                        .requestMatchers("/pacients/delete").hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
        http.formLogin(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService(AuthenticationManagerBuilder auth) {

        UserDetails user =
                User.withUsername("user")
                        .password(encoder().encode("userPass"))
                        .roles("USER")
                        .build();

        UserDetails admin =
                User.withUsername("admin")
                .password(encoder().encode("adminPass"))
                .roles("ADMIN")
                .build();

        try {
            auth.userDetailsService(healthbookUserDetailsService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new InMemoryUserDetailsManager(user,admin);
    }


}
