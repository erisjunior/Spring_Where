package com.erisvan.where.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.erisvan.where.security.JwtAuthFilter;
import com.erisvan.where.security.JwtService;
import com.erisvan.where.service.impl.AccountServiceImpl;

@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AccountServiceImpl service;

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, service);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((authorize) -> {
                    try {
                        authorize
                                .antMatchers("/api/avatars/**")
                                .hasAnyRole("CLIENT", "STORE", "ADMIN")
                                .antMatchers("/api/clients/**")
                                .hasAnyRole("CLIENT", "STORE", "ADMIN")
                                .antMatchers("/api/stores/**")
                                .hasAnyRole("CLIENT", "STORE", "ADMIN")
                                .antMatchers("/api/callings/**")
                                .hasAnyRole("CLIENT", "STORE", "ADMIN")
                                .antMatchers(HttpMethod.GET, "/api/categories/**")
                                .hasAnyRole("CLIENT", "STORE", "ADMIN")
                                .antMatchers(HttpMethod.POST, "/api/categories/**")
                                .hasRole("ADMIN")
                                .antMatchers(HttpMethod.DELETE, "/api/categories/**")
                                .hasRole("ADMIN")
                                .antMatchers(HttpMethod.PUT, "/api/categories/**")
                                .hasRole("ADMIN")
                                .antMatchers(HttpMethod.GET, "/api/accounts/**")
                                .hasAnyRole("CLIENT", "STORE", "ADMIN")
                                .antMatchers(HttpMethod.DELETE, "/api/accounts/**")
                                .hasAnyRole("CLIENT", "STORE", "ADMIN")
                                .antMatchers(HttpMethod.PUT, "/api/accounts/**")
                                .hasAnyRole("CLIENT", "STORE", "ADMIN")
                                .antMatchers(HttpMethod.POST, "/api/accounts/**")
                                .permitAll()
                                .anyRequest().authenticated()
                                .and()
                                .sessionManagement()
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                .and()
                                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        return http.build();
    }

}
