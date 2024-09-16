
package com.project.SocialApp.config;

import com.project.SocialApp.security.JwtAuthenticationEntryPoint;
import com.project.SocialApp.security.JwtAuthenticationFilter;
import com.project.SocialApp.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private UserDetailsServiceImpl userDetailsService;

    private JwtAuthenticationEntryPoint handler;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationEntryPoint handler) {
        this.userDetailsService = userDetailsService;
        this.handler = handler;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
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
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
//        config.setAllowedOriginPatterns(Arrays.asList("http://*.example.com"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("OPTIONS", "HEAD", "GET", "PUT", "POST", "DELETE", "PATCH"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults()) // CORS yapılandırmasını varsayılan ayarlarla etkinleştirdik
                .csrf(csrf -> csrf.disable()) // CSRF'yi devre dışı bıraktik. postmanden istek atacagimiz icin bunu devredisi biraktik normalde birakilmaz
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(handler)) // Exception handling
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/v1/posts").permitAll() // Posts endpoint için izin ver
                        .requestMatchers(HttpMethod.GET, "/api/v1/comments").permitAll() // Comments endpoint için izin ver
                        .requestMatchers(HttpMethod.GET, "/api/v1/likes").permitAll()
                        .requestMatchers("/api/v1/auth/**").permitAll() // Auth endpointlerine izin ver
                        .requestMatchers(HttpMethod.POST, "/api/v1/posts").authenticated()  // Post istekleri doğrulama gerektirir
                        .requestMatchers(HttpMethod.POST, "/api/v1/comments").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/likes").authenticated()
                        .anyRequest().authenticated()); // Diğer istekler doğrulanmalı
        // JWT filtrelerini ekle
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}






