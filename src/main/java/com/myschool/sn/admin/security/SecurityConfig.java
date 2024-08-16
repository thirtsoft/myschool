package com.myschool.sn.admin.security;

import com.myschool.sn.admin.security.jwt.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.myschool.sn.admin.security.config.URL.WHITE_LIST_URL;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(ar -> ar.requestMatchers(WHITE_LIST_URL).permitAll())
                .authorizeHttpRequests(ar -> ar.requestMatchers(GET, "/myschool/v1/utilisateur/activation").permitAll())
                .authorizeHttpRequests(ar -> ar.requestMatchers(POST, "/myschool/v1/utilisateur/password-reset-request").permitAll())
                .authorizeHttpRequests(ar -> ar.anyRequest().authenticated())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterAfter(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                /*
                .logout(logout ->
                         logout.logoutUrl("/api/v1/auth/logout")
                                 .addLogoutHandler(logoutHandler)
                                 .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                 )*/
                .build();
    }
}
