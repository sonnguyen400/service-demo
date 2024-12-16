package com.sonnguyen.common.vm;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "keycloak")
public record KeycloakProperties(
        String client_id,
        String client_secret,
        String server_url,
        String realm,
        String username,
        String password
) {
}
