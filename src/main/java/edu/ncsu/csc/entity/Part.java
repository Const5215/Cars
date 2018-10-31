package edu.ncsu.csc.entity;

public class Part {

  private long id;
  private String name;
  private long unitPrice;
  private long distributorId;

  public Part() {
  }

  public Part(long id, String name, long unitPrice, long distributorId) {
    this.id = id;
    this.name = name;
    this.unitPrice = unitPrice;
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

  public long getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(long unitPrice) {
    this.unitPrice = unitPrice;
  }

  public long getDistributorId() {
    return distributorId;
  }

  public void setDistributorId(long distributorId) {
    this.distributorId = distributorId;
  }

}
