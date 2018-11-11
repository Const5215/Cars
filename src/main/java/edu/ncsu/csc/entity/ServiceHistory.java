package edu.ncsu.csc.entity;

import java.util.Date;

public class ServiceHistory {
  private long id;
  private long customerId;
  private long centerId;
  private long mileage;
  private String carPlate;
  private ServiceType serviceType;
  private long diagnosisId;
  private Date startTime, endTime;
  private long totalLaborHour;
  private ServiceStatus serviceStatus;
  private long mechanicId;

  public ServiceHistory() {
  }

  public ServiceHistory(long id, long customerId, long centerId, long mileage, String carPlate, ServiceType serviceType, long diagnosisId, Date startTime, Date endTime, long totalLaborHour, ServiceStatus serviceStatus, long mechanicId) {
    this.id = id;
    this.customerId = customerId;
    this.centerId = centerId;
    this.mileage = mileage;
    this.carPlate = carPlate;
    this.serviceType = serviceType;
    this.diagnosisId = diagnosisId;
    this.startTime = startTime;
    this.endTime = endTime;
    this.totalLaborHour = totalLaborHour;
    this.serviceStatus = serviceStatus;
    this.mechanicId = mechanicId;
  }

  public long getMileage() {
    return mileage;
  }

  public void setMileage(long mileage) {
    this.mileage = mileage;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }

  public long getCenterId() {
    return centerId;
  }

  public void setCenterId(long centerId) {
    this.centerId = centerId;
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

  public long getDiagnosisId() {
    return diagnosisId;
  }

  public void setDiagnosisId(long diagnosisId) {
    this.diagnosisId = diagnosisId;
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
