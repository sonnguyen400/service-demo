package com.sonnguyen.common.config;

import com.sonnguyen.common.iam.KeycloakClient;
import com.sonnguyen.common.vm.KeycloakCredentialGetVm;
import com.sonnguyen.common.vm.KeycloakProperties;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KeycloakClientCredentialTokenService {
    private final KeycloakProperties keycloakProperties;
    private final KeycloakClient keycloakClient;

    public KeycloakClientCredentialTokenService(KeycloakProperties keycloakProperties, KeycloakClient keycloakClient) {
        this.keycloakProperties = keycloakProperties;
        this.keycloakClient = keycloakClient;
    }

    public KeycloakCredentialGetVm getNewKeycloakCredentialGetVm() {
        return keycloakClient.getClientCredentialsToken(Map.of("client_id",keycloakProperties.client_id(),
                "client_secret",keycloakProperties.client_secret(),
                "grant_type","client_credentials"));
    }
}
//Springbean factory