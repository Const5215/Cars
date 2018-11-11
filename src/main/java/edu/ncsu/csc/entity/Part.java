package edu.ncsu.csc.entity;

public class Part {

  private long id;
  private String name;
  private String make;
  private float unitPrice;
  private long warranty;
  private long delivery_window;
  private long distributorId;

  public Part() {
  }

  public Part(long id, String name, String make, float unitPrice, long warranty, long delivery_window, long distributorId) {
    this.id = id;
    this.name = name;
    this.make = make;
    this.unitPrice = unitPrice;
    this.warranty = warranty;
    this.delivery_window = delivery_window;
    this.distributorId = distributorId;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public float getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(float unitPrice) {
    this.unitPrice = unitPrice;
  }

  public long getWarranty() {
    return warranty;
  }

  public void setWarranty(long warranty) {
    this.warranty = warranty;
  }

  public long getDelivery_window() {
    return delivery_window;
  }

  public void setDelivery_window(long delivery_window) {
    this.delivery_window = delivery_window;
  }

  public long getDistributorId() {
    return distributorId;
  }

  public void setDistributorId(long distributorId) {
    this.distributorId = distributorId;
  }
}
