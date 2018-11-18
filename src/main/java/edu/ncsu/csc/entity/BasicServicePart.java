package edu.ncsu.csc.entity;

public class BasicServicePart {

  private Long basicServiceId;
  private Long partId;
  private Long carModelId;
  private Integer quantity;

  public BasicServicePart() {
  }

  public BasicServicePart(Long basicServiceId, Long partId, Long carModelId, Integer quantity) {
    this.basicServiceId = basicServiceId;
    this.partId = partId;
    this.carModelId = carModelId;
    this.quantity = quantity;
  }

  public Long getBasicServiceId() {
    return basicServiceId;
  }

  public void setBasicServiceId(Long basicServiceId) {
    this.basicServiceId = basicServiceId;
  }

  public Long getPartId() {
    return partId;
  }

  public void setPartId(Long partId) {
    this.partId = partId;
  }

  public Long getCarModelId() {
    return carModelId;
  }

  public void setCarModelId(Long carModelId) {
    this.carModelId = carModelId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
