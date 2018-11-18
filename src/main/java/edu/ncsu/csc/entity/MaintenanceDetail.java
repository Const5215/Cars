package edu.ncsu.csc.entity;

public class MaintenanceDetail {

  private Long carModelId;
  private ServiceType maintenanceType;
  private Long basicServiceId;

  public MaintenanceDetail() {
  }

  public MaintenanceDetail(Long carModelId, ServiceType maintenanceType, Long basicServiceId) {
    this.carModelId = carModelId;
    this.maintenanceType = maintenanceType;
    this.basicServiceId = basicServiceId;
  }

  public Long getCarModelId() {
    return carModelId;
  }

  public void setCarModelId(Long carModelId) {
    this.carModelId = carModelId;
  }

  public ServiceType getMaintenanceType() {
    return maintenanceType;
  }

  public void setMaintenanceType(ServiceType maintenanceType) {
    this.maintenanceType = maintenanceType;
  }

  public Long getBasicServiceId() {
    return basicServiceId;
  }

  public void setBasicServiceId(Long basicServiceId) {
    this.basicServiceId = basicServiceId;
  }
}
