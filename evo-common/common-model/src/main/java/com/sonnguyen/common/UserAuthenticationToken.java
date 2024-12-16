package com.sonnguyen.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = false)
@Getter
public class UserAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private final boolean isRoot;
    private final boolean isClient;
    private final Object token;
    private final Set<String> grantedPermissions;

    public UserAuthenticationToken(Object principal,
                              Object token,
                              Collection<? extends GrantedAuthority> authorities,
                              boolean isRoot,
                              boolean isClient) {
        super(principal, null, authorities);
        this.isRoot = isRoot;
        this.isClient = isClient;
        this.token = token;
        this.grantedPermissions = CollectionUtils.isEmpty(authorities)
                ? Collections.emptySet()
                : authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }

}
