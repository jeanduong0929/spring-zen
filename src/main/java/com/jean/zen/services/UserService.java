package com.jean.zen.services;

import com.jean.zen.dtos.responses.Principal;
import com.jean.zen.entities.Role;
import com.jean.zen.entities.User;
import com.jean.zen.repositories.UserRepository;
import com.jean.zen.utils.custom_exceptions.InvalidAuthException;
import java.util.Optional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepo;
  private final RoleService roleService;

  public UserService(UserRepository userRepo, RoleService roleService) {
    this.userRepo = userRepo;
    this.roleService = roleService;
  }

  public User save(String email, String password) {
    Role foundRole = roleService.findDefaultRole("USER");
    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
    User newUser = new User(email, hashedPassword, foundRole);
    return userRepo.save(newUser);
  }

  public Principal login(String email, String password) {
    Optional<User> userOpt = userRepo.findAll()
                                 .stream()
                                 .filter(user -> user.getEmail().equals(email))
                                 .findFirst();

    if (userOpt.isEmpty() ||
        !BCrypt.checkpw(password, userOpt.get().getPassword())) {
      throw new InvalidAuthException("Invalid email or password.");
    }
    return new Principal(userOpt.get());
  }

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
