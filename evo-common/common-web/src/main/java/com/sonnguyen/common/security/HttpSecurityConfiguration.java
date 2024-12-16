package com.sonnguyen.common.security;

import com.sonnguyen.common.config.ActionLogFilter;
import com.sonnguyen.common.config.JwtProperties;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@EnableWebSecurity
@EnableFeignClients(basePackages = {"com.evo.common.client"})
@EnableMethodSecurity(securedEnabled = true)
public class HttpSecurityConfiguration {

    private final ActionLogFilter actionLogFilter;
    private final CustomAuthenticationFilter customAuthenticationFilter;
    private final ForbiddenTokenFilter forbiddenTokenFilter;
    private final JwtProperties jwtProperties;

    public HttpSecurityConfiguration(ActionLogFilter actionLogFilter,
                                     CustomAuthenticationFilter customAuthenticationFilter,
                                     ForbiddenTokenFilter forbiddenTokenFilter, JwtProperties jwtProperties) {
        this.actionLogFilter = actionLogFilter;
        this.customAuthenticationFilter = customAuthenticationFilter;
        this.forbiddenTokenFilter = forbiddenTokenFilter;
        this.jwtProperties = jwtProperties;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/health").permitAll()
                                .requestMatchers("/api/v1/auth/**").permitAll()
                                .requestMatchers("/api/v1/public/**").permitAll()
                                .requestMatchers("/api/authenticate/**").permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .authenticationManagerResolver(this.jwkResolver(this.jwtProperties)));
        http.addFilterAfter(this.forbiddenTokenFilter, BearerTokenAuthenticationFilter.class);
        http.addFilterAfter(this.customAuthenticationFilter, BearerTokenAuthenticationFilter.class);
        http.addFilterAfter(this.actionLogFilter, BearerTokenAuthenticationFilter.class);
        // @formatter:on
        return http.build();
    }



    public AuthenticationManagerResolver<HttpServletRequest> jwkResolver(JwtProperties jwtProperties) {
        return new JwkAuthenticationManagerResolver(jwtProperties);
    }

}
