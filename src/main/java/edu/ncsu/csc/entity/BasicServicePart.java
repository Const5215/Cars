package edu.ncsu.csc.entity;

public class BasicServicePart {
  private long carModelId;
  private String name;
  private long partId;
  private long quantity;

  public BasicServicePart() {
  }

  public BasicServicePart(long carModelId, String name, long partId, long quantity) {
    this.carModelId = carModelId;
    this.name = name;
    this.partId = partId;
    this.quantity = quantity;
  }

  public long getCarModelId() {
    return carModelId;
  }

  public void setCarModelId(long carModelId) {
    this.carModelId = carModelId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getPartId() {
    return partId;
  }

  public void setPartId(long partId) {
    this.partId = partId;
  }

  public long getQuantity() {
    return quantity;
  }

  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }
}
