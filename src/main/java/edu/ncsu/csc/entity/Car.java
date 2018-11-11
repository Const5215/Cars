package edu.ncsu.csc.entity;

import java.util.Date;

public class Car {
    private String plate;
    private Long customerId;
    private Long carModelId;
    private Date purchaseDate;
  private Long year;

    public Car() {
    }

  public Car(String plate, Long customerId, Long carModelId, Date purchaseDate, Long year) {
        this.plate = plate;
        this.customerId = customerId;
        this.carModelId = carModelId;
        this.purchaseDate = purchaseDate;
    this.year = year;
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

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

  public Long getYear() {
    return year;
    }

  public void setYear(Long year) {
    this.year = year;
    }
}
