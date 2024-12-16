package com.sonnguyen.iamservice2.security;

import com.sonnguyen.common.UserAuthority;
import com.sonnguyen.common.security.AuthorityService;
import com.sonnguyen.iamservice2.exception.ResourceNotFoundException;
import com.sonnguyen.iamservice2.model.RolePermission;
import com.sonnguyen.iamservice2.service.AccountServiceImpl;
import com.sonnguyen.iamservice2.service.RolePermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Primary
@Service
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {
    AccountServiceImpl accountService;
    RolePermissionService rolePermissionService;
    @Override
    public UserAuthority getUserAuthority(UUID userId) {

        return null;
    }

    @Override
    public UserAuthority getUserAuthority(String username) {
        return accountService
                .findByEmail(username)
                .map(account_ -> {
                    List<RolePermission> rolePermissions = rolePermissionService.findAllByAccountId(account_.getId());
                    List<String> authorities = rolePermissions.stream().map((rolePermission -> String.format("%s_%s", rolePermission.getResource_code().toUpperCase(), rolePermission.getScope().name()))).toList();
                   return UserAuthority.builder()
                           .email(account_.getEmail())
                           .id(account_.getId())
                           .grantedAuthority(authorities)
                           .isLocked(account_.isLocked())
                           .isVerified(account_.isVerified())
                           .isRoot(account_.isRoot())
                           .build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(username + " not found"));
    }

    @Override
    public UserAuthority getClientAuthority(UUID clientId) {
        return null;
    }
}
