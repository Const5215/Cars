package edu.ncsu.csc.entity;

public class CarModel {
  private Long id;
  private String make;
  private String model;

  public CarModel() {
  }

  public CarModel(Long id, String make, String model) {
    this.id = id;
    this.make = make;
    this.model = model;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }
}
