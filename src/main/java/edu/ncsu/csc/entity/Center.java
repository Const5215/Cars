package edu.ncsu.csc.entity;

public class Center {

  private Long id;
  private String name;
  private String telephone;
  private String address;

  protected Center() {
  }

  public Center(Long id, String name, String telephone, String address) {
    this.id = id;
    this.name = name;
    this.telephone = telephone;
    this.address = address;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
