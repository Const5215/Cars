package edu.ncsu.csc.entity;

import java.util.Date;

public class Car {

  private String plate;
  private Long customerId;
  private Long carModelId;
  private Integer year;
  private Date purchaseDate;

  public Car() {
  }

  public Car(String plate, Long customerId, Long carModelId, Integer year, Date purchaseDate) {
    this.plate = plate;
    this.customerId = customerId;
    this.carModelId = carModelId;
    this.year = year;
    this.purchaseDate = purchaseDate;
  }

  public String getPlate() {
    return plate;
  }

  public void setPlate(String plate) {
    this.plate = plate;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public Long getCarModelId() {
    return carModelId;
  }

  public void setCarModelId(Long carModelId) {
    this.carModelId = carModelId;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public Date getPurchaseDate() {
    return purchaseDate;
  }

  public void setPurchaseDate(Date purchaseDate) {
    this.purchaseDate = purchaseDate;
  }
}
