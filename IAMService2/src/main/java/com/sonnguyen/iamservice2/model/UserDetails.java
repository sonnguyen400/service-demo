package com.sonnguyen.iamservice2.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
    private Long id;
    private String username;
    private String password;
    private boolean verified;
    private boolean nonLocked;
    private boolean isRoot;
    private Collection<? extends GrantedAuthority> authorities;
}
