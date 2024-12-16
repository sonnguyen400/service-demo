package com.sonnguyen.iamservice2.service;

import com.sonnguyen.iamservice2.exception.ResourceNotFoundException;
import com.sonnguyen.iamservice2.model.Account;
import com.sonnguyen.iamservice2.model.RolePermission;
import com.sonnguyen.iamservice2.model.UserDetails;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserDetailsService {
    AccountServiceImpl accountService;
    RolePermissionService rolePermissionService;

    public UserDetails loadUserByUsername(@NotNull String username) {
        return accountService
                .findByEmail(username)
                .map(account_ -> {
                    UserDetails userDetails = fromAccount(account_);
                    List<RolePermission> rolePermissions = rolePermissionService.findAllByAccountId(account_.getId());
                    Collection<? extends GrantedAuthority> authorities = rolePermissions.stream().map((rolePermission -> {
                        String authority = String.format("%s_%s", rolePermission.getResource_code().toUpperCase(), rolePermission.getScope().name());
                        return new SimpleGrantedAuthority(authority);
                    })).toList();
                    userDetails.setAuthorities(authorities);
                    return userDetails;
                })
                .orElseThrow(() -> new ResourceNotFoundException(username + " not found"));
    }

    public static UserDetails fromAccount(Account account) {
        return  UserDetails.builder()
                .id(account.getId())
                .username(account.getEmail())
                .password(account.getPassword())
                .verified(account.isVerified())
                .nonLocked(!account.isLocked())
                .isRoot(account.isRoot())
                .authorities(List.of()).build();
    }
}
