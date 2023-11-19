package com.example.securityservice.controller;

import com.example.securityservice.dto.AuthRequest;
import com.example.securityservice.exception.UserAuthNotFoundException;
import com.example.securityservice.model.UserCredential;
import com.example.securityservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final   AuthService authService;


    private final AuthenticationManager authenticationManager;
    @Autowired
    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredential userCredential){
        return authService.saveUser(userCredential);
    }
    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) throws UserAuthNotFoundException {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            return authService.generateToken(authRequest.getUsername());
        }
        else
            throw new UserAuthNotFoundException("user not registered");
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        authService.validateToken(token);
        return "Token is Valid";
    }
}
