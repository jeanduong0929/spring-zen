package com.jean.zen.services;

import com.jean.zen.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepo;

  public UserService(UserRepository userRepo) { this.userRepo = userRepo; }

  public boolean isValidEmail(String email) {
    return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
  }

  public boolean isValidPassword(String password) {
    return password.matches(
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$");
  }

  public boolean isUniqueEmail(String email) {
    return userRepo.findAll().stream().noneMatch(
        user -> user.getEmail().equals(email));
  }
}
