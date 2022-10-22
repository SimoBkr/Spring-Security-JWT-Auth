package com.peaqock.aml.api;

import com.peaqock.aml.dto.payload.AccessToken;
import com.peaqock.aml.dto.payload.AuthRequest;
import com.peaqock.aml.service.auth.AuthService;
import com.peaqock.aml.shared.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @GetMapping("/userinfo")
    public UserInfo userinfo() {
        return service.loadUserinfo();
    }

    @PostMapping("/token")
    public AccessToken login(@RequestBody @Valid AuthRequest request) {
        return service.accessToken(request);
    }
}
