package edu.ncsu.csc.entity;

import java.util.Date;

public class Car {
    private String plate;
    private Long customerId;
    private Long carModelId;
    private Date purchaseDate;
    private Long lastMileage;
    private Long lastServiceType;
    private Date lastServiceDate;

    public Car() {
    }

    public Car(String plate, Long customerId, Long carModelId, Date purchaseDate, Long lastMileage, Long lastServiceType, Date lastServiceDate) {
        this.plate = plate;
        this.customerId = customerId;
        this.carModelId = carModelId;
        this.purchaseDate = purchaseDate;
        this.lastMileage = lastMileage;
        this.lastServiceType = lastServiceType;
        this.lastServiceDate = lastServiceDate;
    }

    public Car(String plate) {
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

    public Long getLastMileage() {
        return lastMileage;
    }

    public void setLastMileage(Long lastMileage) {
        this.lastMileage = lastMileage;
    }

    public Long getLastServiceType() {
        return lastServiceType;
    }

    public void setLastServiceType(Long lastServiceType) {
        this.lastServiceType = lastServiceType;
    }

    public Date getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(Date lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

}
