package dev.kerimfettahoglu.oauthresourceserver.controller;

import dev.kerimfettahoglu.oauthresourceserver.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final TokenService tokenService;

    @PostMapping("/login")
    public String login(Authentication authentication) {
        log.info("Token is generating for {}", authentication.getName());
        return tokenService.generateToken(authentication);
    }
}
