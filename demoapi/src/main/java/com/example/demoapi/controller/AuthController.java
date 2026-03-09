package com.example.demoapi.controller;

import com.example.demoapi.Service.JwtService;
import com.example.demoapi.dto.LoginRequest;
import com.example.demoapi.dto.JwtAuthResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public JwtAuthResponse login(@RequestBody LoginRequest request) {

        String token = jwtService.generateToken(request.getEmail());

        return new JwtAuthResponse(token);
    }
}