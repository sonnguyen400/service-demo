package com.sonnguyen.common.security.impl;

import com.sonnguyen.common.iam.IamClient;
import com.sonnguyen.common.UserAuthority;
import com.sonnguyen.common.security.AuthorityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class RemoteAuthorityServiceImpl implements AuthorityService {
    private final IamClient iamClient;

    @Override
    public UserAuthority getUserAuthority(UUID userId) {
        return null;
    }

    @Override
    public UserAuthority getUserAuthority(String username) {
        return null;
    }

    @Override
    public UserAuthority getClientAuthority(UUID clientId) {
        return null;
    }
}
