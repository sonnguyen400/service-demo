package com.sonnguyen.common.security;

import com.sonnguyen.common.UserAuthentication;
import com.sonnguyen.common.UserAuthority;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

@Component
@Slf4j
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {
    AuthorityService authorityService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        JwtAuthenticationToken authentication =
                (JwtAuthenticationToken) securityContext.getAuthentication();
        Jwt token = authentication.getToken();

        Boolean isRoot = Boolean.FALSE;
        Boolean isClient = Boolean.FALSE;


        Set<SimpleGrantedAuthority> grantedPermissions = new HashSet<>();
        String username;
        if (StringUtils.hasText(token.getClaimAsString("preferred_username"))) {
            username = token.getClaimAsString("preferred_username");
        } else {
            username = token.getSubject();
        }
        if(!Objects.equals(username, "service-account-client")){
            log.info("User Client {}", username);
            enrichAuthority(username).ifPresent((authority) -> {
                Collection<? extends  GrantedAuthority> authorities=authority.getGrantedAuthority().stream().map(SimpleGrantedAuthority::new).toList();
                User principal = new User(username, "",authorities);
                authorities.forEach(System.out::println);
                AbstractAuthenticationToken auth = new UserAuthentication(principal, token, authorities, isRoot, isClient);
                SecurityContextHolder.getContext().setAuthentication(auth);
            });
        }else {
            log.info("Trusted Client");
            User principal = new User(username, "", List.of());
            AbstractAuthenticationToken auth = new UserAuthentication(principal, token, List.of(), isRoot, isClient);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        return !(authentication instanceof JwtAuthenticationToken);
    }

    private Optional<UserAuthority> enrichAuthority(String email) {
        return Optional.ofNullable(authorityService.getUserAuthority(email));
    }
}
