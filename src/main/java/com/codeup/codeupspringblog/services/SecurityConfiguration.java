package com.codeup.codeupspringblog.services;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.*;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  private UserDetailsLoader usersLoader;

  public SecurityConfiguration(UserDetailsLoader usersLoader) {
    this.usersLoader = usersLoader;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            /* Login configuration */
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/ads") // user's home page, it can be any URL
            .permitAll() // Anyone can go to the login page
            /* Logout configuration */
            .and()
            .logout()
            .logoutSuccessUrl("/") // append a query string value
            /* Pages that can be viewed without having to log in */
            .and()
            .authorizeHttpRequests()
            .requestMatchers("/", "/ads", "/ads/{id}") // anyone can see the home and the ads pages
            .permitAll()
            /* Pages that require authentication */
            .and()
            .authorizeHttpRequests()
            .requestMatchers(
                    "/ads/create", // only authenticated users can create ads
                    "/ads/{id}/edit" // only authenticated users can edit ads
            )
            .authenticated();
    return http.build();
  }
}
