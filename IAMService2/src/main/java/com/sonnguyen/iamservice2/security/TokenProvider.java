package com.sonnguyen.iamservice2.security;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.sonnguyen.iamservice2.utils.RSAKeyUtil;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPublicKey;
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class TokenProvider{
    RSAKeyUtil rsaKeyUtil;
    public JWKSet jwkSet() throws Exception {
        RSAKey.Builder builder = new RSAKey.Builder((RSAPublicKey) rsaKeyUtil.getPublicKey()).keyUse(KeyUse.SIGNATURE);
        return new JWKSet(builder.build());
    }
}