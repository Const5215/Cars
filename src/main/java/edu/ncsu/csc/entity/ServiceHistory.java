package edu.ncsu.csc.entity;

import java.util.Date;

public class ServiceHistory {
  private long id;
  private String carPlate;
  private ServiceType serviceType;
  private Date startTime, endTime;
  private long totalLaborHour;
  private ServiceStatus serviceStatus;
  private long mechanicId;

  public ServiceHistory() {
  }

  public ServiceHistory(long id, String carPlate, ServiceType serviceType, Date startTime, Date endTime, long totalLaborHour, ServiceStatus serviceStatus, long mechanicId) {
    this.id = id;
    this.carPlate = carPlate;
    this.serviceType = serviceType;
    this.startTime = startTime;
    this.endTime = endTime;
    this.totalLaborHour = totalLaborHour;
    this.serviceStatus = serviceStatus;
    this.mechanicId = mechanicId;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCarPlate() {
    return carPlate;
  }

  public void setCarPlate(String carPlate) {
    this.carPlate = carPlate;
  }

  public ServiceType getServiceType() {
    return serviceType;
  }

  public void setServiceType(ServiceType serviceType) {
    this.serviceType = serviceType;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public long getTotalLaborHour() {
    return totalLaborHour;
  }

  public void setTotalLaborHour(long totalLaborHour) {
    this.totalLaborHour = totalLaborHour;
  }

  public ServiceStatus getServiceStatus() {
    return serviceStatus;
  }

  public void setServiceStatus(ServiceStatus serviceStatus) {
    this.serviceStatus = serviceStatus;
  }

  public long getMechanicId() {
    return mechanicId;
  }

  public void setMechanicId(long mechanicId) {
    this.mechanicId = mechanicId;
  }
}
