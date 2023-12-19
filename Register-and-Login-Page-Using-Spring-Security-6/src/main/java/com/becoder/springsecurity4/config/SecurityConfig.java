package com.becoder.springsecurity4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService getDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(getDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;

    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    // UserDetails user = User.withUsername("user").password(passwordEncoder().encode("1234"))
    // .roles("USER").build();

    // UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("1234"))
    // .roles("ADMIN").build();

    // return new InMemoryUserDetailsManager(user, admin);

    // }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // http.csrf((csrf) -> csrf.disable())
        // .authorizeHttpRequests((authz) -> authz.requestMatchers("/index").hasRole("USER")
        // .requestMatchers("/admin/**").hasRole("ADMIN").requestMatchers("/user/**")
        // .hasRole("USER").anyRequest().authenticated())
        // .formLogin(Customizer.withDefaults());


        // http.csrf((csrf) -> csrf.disable()).authorizeHttpRequests(
        // (authz) -> authz.requestMatchers("/").permitAll().anyRequest().authenticated())
        // .formLogin(Customizer.withDefaults());

        http.csrf((csrf) -> csrf.disable()).authorizeHttpRequests((authz) -> authz
                .requestMatchers("/", "/index", "/register", "/signin", "/saveEmp").permitAll()
                .requestMatchers("/user/**", "/profile").authenticated().anyRequest().permitAll())
                .formLogin(form -> form.loginPage("/signin").loginProcessingUrl("/login")
                        .defaultSuccessUrl("/profile").permitAll());

        return http.build();
    }
}
