package com.jean.zen.services;

import com.jean.zen.entities.Role;
import com.jean.zen.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
  private final RoleRepository roleRepo;

  public RoleService(RoleRepository roleRepo) { this.roleRepo = roleRepo; }

  public Role save(String name) {
    Role newRole = new Role(name);
    return roleRepo.save(newRole);
  }

  public boolean isUniqueRole(String name) {
    return roleRepo.findAll().stream().noneMatch(
        role -> role.getName().equals(name));
  }
}
