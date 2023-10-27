package com.jean.zen.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
  @Id private String id;

  @Column(nullable = false, unique = true) private String email;

  @Column(nullable = false) private String password;

  @ManyToOne
  @JsonBackReference
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;

  public User() {}

  public User(String email, String password, Role role) {
    this.id = UUID.randomUUID().toString();
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public String getId() { return id; }

  public void setId(String id) { this.id = id; }

  public String getEmail() { return email; }

  public void setEmail(String email) { this.email = email; }

  public String getPassword() { return password; }

  public void setPassword(String password) { this.password = password; }

  public Role getRole() { return role; }

  public void setRole(Role role) { this.role = role; }
}
