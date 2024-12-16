package com.sonnguyen.common.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@ComponentScan
public class OpenFeignTokenInterceptor implements RequestInterceptor {
    KeycloakClientCredentialTokenService keycloakClientCredentialTokenService;
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String accessToken=keycloakClientCredentialTokenService.getNewKeycloakCredentialGetVm().access_token();
        requestTemplate.header("Authorization",String.format("Bearer %s",accessToken));
    }
}
