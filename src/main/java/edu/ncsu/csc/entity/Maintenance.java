package edu.ncsu.csc.entity;

public class Maintenance {
  private long carModelId;
  private ServiceType serviceType;
  private long mile;

  public Maintenance() {
  }

  public Maintenance(long carModelId, ServiceType serviceType, long mile, long month) {
    this.carModelId = carModelId;
    this.serviceType = serviceType;
    this.mile = mile;
  }

  public long getCarModelId() {
    return carModelId;
  }

  public void setCarModelId(long carModelId) {
    this.carModelId = carModelId;
  }

  public ServiceType getServiceType() {
    return serviceType;
  }

  public void setServiceType(ServiceType serviceType) {
    this.serviceType = serviceType;
  }

  public long getMile() {
    return mile;
  }

  public void setMile(long mile) {
    this.mile = mile;
  }
}
