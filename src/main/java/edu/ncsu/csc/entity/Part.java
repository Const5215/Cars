package edu.ncsu.csc.entity;

public class Part {

  private Long id;
  private String name;
  private String make;
  private Float unitPrice;
  private Integer warranty;
  private Integer deliveryWindow;
  private Long distributorId;

  public Part() {
  }

  public Part(Long id, String name, String make, Float unitPrice, Integer warranty,
      Integer deliveryWindow, Long distributorId) {
    this.id = id;
    this.name = name;
    this.make = make;
    this.unitPrice = unitPrice;
    this.warranty = warranty;
    this.deliveryWindow = deliveryWindow;
    this.distributorId = distributorId;
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

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public Float getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(Float unitPrice) {
    this.unitPrice = unitPrice;
  }

  public Integer getWarranty() {
    return warranty;
  }

  public void setWarranty(Integer warranty) {
    this.warranty = warranty;
  }

  public Integer getDeliveryWindow() {
    return deliveryWindow;
  }

  public void setDeliveryWindow(Integer deliveryWindow) {
    this.deliveryWindow = deliveryWindow;
  }

  public Long getDistributorId() {
    return distributorId;
  }

  public void setDistributorId(Long distributorId) {
    this.distributorId = distributorId;
  }
}
