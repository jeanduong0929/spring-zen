package com.jean.zen.dtos.responses;

import com.jean.zen.entities.User;

public class Principal {
  private String id;
  private String email;
  private String role;
  private String token;
  public Principal() {}

  public Principal(User user) {
    this.id = user.getId();
    this.email = user.getEmail();
    this.role = user.getRole().getName();
  }

  public Principal(String id, String email, String role) {
    this.id = id;
    this.email = email;
    this.role = role;
  }

  public Principal(String id, String email, String role, String token) {
    this.id = id;
    this.email = email;
    this.role = role;
    this.token = token;
  }
  public String getId() { return id; }

  public void setId(String id) { this.id = id; }

  public String getEmail() { return email; }

  public void setEmail(String email) { this.email = email; }

  public String getRole() { return role; }

  public void setRole(String role) { this.role = role; }

  public String getToken() { return token; }

  public void setToken(String token) { this.token = token; }
}
