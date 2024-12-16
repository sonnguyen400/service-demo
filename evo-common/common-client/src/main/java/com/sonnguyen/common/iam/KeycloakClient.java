package com.sonnguyen.common.iam;

import com.sonnguyen.common.vm.KeycloakCredentialGetVm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(name = "keycloakclient", url = "${keycloak.server-url}/realms/iam_service")
public interface KeycloakClient {
    @PostMapping(value = "/protocol/openid-connect/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    KeycloakCredentialGetVm getClientCredentialsToken(Map<String, ?> client_credentials);
}
