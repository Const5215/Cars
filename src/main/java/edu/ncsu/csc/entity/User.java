package edu.ncsu.csc.entity;

public class User {

  private Long id;
  private String password;
  private String name;
  private String email;
  private String phone;
  private String address;
  private Role role;

  public User() {
  }

  public User(Long id, String password, String name, String email, String phone,
      String address) {
    this.id = id;
    this.password = password;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.address = address;
  }

  public User(Long id, String password, String name, String email, String phone,
      String address, Role role) {
    this.id = id;
    this.password = password;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.role = role;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
