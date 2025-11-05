package com.health.auth_service.controller;

import com.health.auth_service.model.AuthRequest;
import com.health.auth_service.model.AuthResponse;
import com.health.auth_service.model.User;
import com.health.auth_service.service.AuthService;
import com.health.auth_service.security.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
    	try {
    		User u = authService.register(user.getUsername(), user.getPassword(), user.getRole() == null ? "USER" : user.getRole());
    		log.info("User registered successfully");
    		return ResponseEntity.ok(u);
    	} catch(Exception e) {
    		log.error("User Already exist");
    		return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
    	}
    	
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
    	try {
    		Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
    		String token = jwtUtil.generateToken(request.getUsername());
    		log.info("User logged in successfully");
    		return ResponseEntity.ok(new AuthResponse(token));
    	} catch(Exception e) {
    		log.error("User Not Found");
    		return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
    	}
        
        
    }
}
