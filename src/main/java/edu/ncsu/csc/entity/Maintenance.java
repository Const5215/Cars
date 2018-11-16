package edu.ncsu.csc.entity;

public class Maintenance {

  private Long carModelId;
  private ServiceType serviceType;
  private Integer mileage;

  public Maintenance() {
  }

  public Maintenance(Long carModelId, ServiceType serviceType, Integer mileage) {
    this.carModelId = carModelId;
    this.serviceType = serviceType;
    this.mileage = mileage;
  }

  public Long getCarModelId() {
    return carModelId;
  }

  public void setCarModelId(Long carModelId) {
    this.carModelId = carModelId;
  }

  public ServiceType getServiceType() {
    return serviceType;
  }

  public void setServiceType(ServiceType serviceType) {
    this.serviceType = serviceType;
  }

  public Integer getMileage() {
    return mileage;
  }

  public void setMileage(Integer mileage) {
    this.mileage = mileage;
  }
}
