package com.sonnguyen.common.vm;

public record KeycloakCredentialGetVm(
        String access_token,
        Integer expires_in
) {
}
