package com.jean.zen.controllers;

import com.jean.zen.dtos.requests.NewRoleRequest;
import com.jean.zen.services.RoleService;
import com.jean.zen.utils.custom_exceptions.NotFoundException;
import com.jean.zen.utils.custom_exceptions.ResourceConflict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
  private final RoleService roleService;

  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @PostMapping
  public ResponseEntity<?> createRole(@RequestBody NewRoleRequest req) {
    String name = req.getName();

    // Validate role name
    if (name.isEmpty()) {
      throw new NotFoundException("Role name cannot be empty");
    }
    if (!roleService.isUniqueRole(name)) {
      throw new ResourceConflict("Role name already exists");
    }

    // Save the role
    roleService.save(name);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
