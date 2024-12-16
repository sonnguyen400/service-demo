package com.evo.iam.service.impl;

import com.sonnguyen.common.model.UserAuthority;
import com.sonnguyen.common.webapp.security.AuthorityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Primary
@Slf4j
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {
    @Override
    public UserAuthority getUserAuthority(UUID userId) {
        //log.info(userId.toString());
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
