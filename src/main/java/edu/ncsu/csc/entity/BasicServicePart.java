package edu.ncsu.csc.entity;

public class BasicServicePart {
  private long basicServiceId;
  private long partId;
  private long carModelId;
  private long quantity;

  public BasicServicePart() {
  }

  public BasicServicePart(long basicServiceId, long partId, long carModelId, long quantity) {
    this.basicServiceId = basicServiceId;
    this.partId = partId;
    this.carModelId = carModelId;
    this.quantity = quantity;
  }

  public long getBasicServiceId() {
    return basicServiceId;
  }

  public void setBasicServiceId(long basicServiceId) {
    this.basicServiceId = basicServiceId;
  }

  public long getPartId() {
    return partId;
  }

  public void setPartId(long partId) {
    this.partId = partId;
  }

  public long getCarModelId() {
    return carModelId;
  }

  public void setCarModelId(long carModelId) {
    this.carModelId = carModelId;
  }

  public long getQuantity() {
    return quantity;
  }

  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }
}
