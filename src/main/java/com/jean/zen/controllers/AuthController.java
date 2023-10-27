package com.jean.zen.controllers;

import com.jean.zen.dtos.requests.NewRegisterRequest;
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

  public AuthController(UserService userService) {
    this.userService = userService;
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
}
