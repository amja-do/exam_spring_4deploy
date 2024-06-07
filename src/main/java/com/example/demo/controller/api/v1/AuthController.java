package com.example.demo.controller.api.v1;

import com.example.demo.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("authApiController")
@RequestMapping("/api/v1")
public class AuthController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> token(@RequestParam String username, @RequestParam String password) {
        Authentication authentication = authenticate(username, password);
        String token = tokenService.generateToken(authentication);
        return ResponseEntity.ok().body(token);
    }

    private Authentication authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authReq
            = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authReq);
    }
}
