package com.sonnguyen.iamservice2.controller;

import com.sonnguyen.iamservice2.exception.TokenException;
import com.sonnguyen.iamservice2.security.TokenProvider;
import com.sonnguyen.iamservice2.service.AccountService;
import com.sonnguyen.iamservice2.service.AuthenticationService;
import com.sonnguyen.iamservice2.service.AuthenticationServiceImpl;
import com.sonnguyen.iamservice2.viewmodel.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AuthenticationController {
    AuthenticationService authenticationService;
    AuthenticationServiceImpl authenticationServiceImpl;
    AccountService accountService;
    TokenProvider tokenProvider;
    PasswordEncoder passwordEncoder;
    @GetMapping("/certificate/.well-known/jwks.json")
    Map<String, Object> keys() throws Exception {
        return this.tokenProvider.jwkSet().toJSONObject();
    }
    @GetMapping(value = "/login/google")
    public ResponseEntity<?> oauth2Login(){
        return authenticationServiceImpl.oauth2Login();
    }
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginPostVm loginPostVm) {
        return authenticationService.login(loginPostVm);
    }

    @GetMapping(value = "/login")
    public ResponseEntity<?> oauth2LoginToken(@RequestBody(required = false) LoginPostVm loginPostVm) {
        return authenticationService.login(loginPostVm);
    }

    @PostMapping(value = "/login/accept")
    public ResponseTokenVm oauth2LoginToken(@RequestBody(required = false) AcceptedLoginRequestVm acceptedLoginRequestVm) {
        return authenticationServiceImpl.acceptLoginRequest(acceptedLoginRequestVm);
    }

    @PostMapping(value = "/register")
    public String register(@RequestBody UserRegistrationPostVm registrationPostVm) {
        accountService.register(registrationPostVm);
        return "registered successfully";
    }

    @GetMapping(value = "/verify")
    public ResponseEntity<?> requestVerifyAccount(@RequestParam String email) {
        return authenticationServiceImpl.requestVerifyAccount(email);
    }

    @GetMapping(value = "/verify/{code}")
    public ResponseEntity<?> requestVerifyCode(@PathVariable String code) {
        return authenticationServiceImpl.acceptVerifyAccount(code);
    }

    @PostMapping(value = "/logout")
    public void logout(@RequestBody RequestTokenVm requestTokenVm) {
        authenticationService.logout(requestTokenVm);
    }

    @PostMapping(value = "/token/refresh")
    public ResponseTokenVm refreshToken(@RequestParam String refresh_token) {
        return authenticationService.refreshToken(refresh_token);
    }

    @PostMapping("/password/change")
    public void changePassword(@RequestBody ChangePasswordPostVm changePasswordPostVm) {
        authenticationServiceImpl.changePassword(changePasswordPostVm);
    }

}
