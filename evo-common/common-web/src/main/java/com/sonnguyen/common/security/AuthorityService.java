package com.sonnguyen.common.security;



import com.sonnguyen.common.UserAuthority;

import java.util.UUID;

public interface AuthorityService {
    UserAuthority getUserAuthority(UUID userId);

    UserAuthority getUserAuthority(String username);

    UserAuthority getClientAuthority(UUID clientId);

}
