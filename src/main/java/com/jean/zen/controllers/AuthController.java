package com.jean.zen.controllers;

import com.jean.zen.dtos.requests.NewLoginRequest;
import com.jean.zen.dtos.requests.NewRegisterRequest;
import com.jean.zen.dtos.responses.Principal;
import com.jean.zen.services.JwtService;
import com.jean.zen.services.UserService;
import com.jean.zen.utils.custom_exceptions.BadRequestException;
import com.jean.zen.utils.custom_exceptions.ResourceConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final UserService userService;
  private final JwtService jwtService;

  public AuthController(UserService userService, JwtService jwtService) {
    this.userService = userService;
    this.jwtService = jwtService;
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody NewRegisterRequest req) {
    String email = req.getEmail();
    String password = req.getPassword();

    // Validate request
    if (!userService.isValidEmail(email) ||
        !userService.isValidPassword(password)) {
      throw new BadRequestException("Invalid email or password.");
    }
    if (!userService.isUniqueEmail(email)) {
      throw new ResourceConflictException("Email already exists.");
    }

    // Save user
    userService.save(email, password);

    // Return success response
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/login")
  public ResponseEntity<Principal> login(@RequestBody NewLoginRequest req) {
    String email = req.getEmail();
    String password = req.getPassword();

    // Get the principal when login is successful
    Principal principal = userService.login(email, password);

    // Get jwt token
    String token = jwtService.generateToken(principal);

    // Set the token
    principal.setToken(token);

    // Return success response with principal obj
    return ResponseEntity.ok().body(principal);
  }
}
