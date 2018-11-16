package edu.ncsu.csc.entity;

import java.util.Date;

public class ServiceHistory {

  private Long id;
  private Long customerId;
  private String carPlate;
  private Long centerId;
  private ServiceType serviceType;
  private Integer mileage;
  private Date startTime;
  private Date endTime;
  private Long mechanicId;
  private Long diagnosisId;
  private ServiceStatus serviceStatus;

  public ServiceHistory() {
  }

  public ServiceHistory(Long id, Long customerId, String carPlate, Long centerId,
      ServiceType serviceType, Integer mileage, Date startTime, Date endTime,
      Long mechanicId) {
    this.id = id;
    this.customerId = customerId;
    this.carPlate = carPlate;
    this.centerId = centerId;
    this.serviceType = serviceType;
    this.mileage = mileage;
    this.startTime = startTime;
    this.endTime = endTime;
    this.mechanicId = mechanicId;
    this.diagnosisId = null;
    this.serviceStatus = endTime.before(new Date()) ? ServiceStatus.Complete
        : (startTime.after(new Date()) ? ServiceStatus.Pending : ServiceStatus.Ongoing);
  }

  public ServiceHistory(Long id, Long customerId, String carPlate, Long centerId,
      ServiceType serviceType, Integer mileage, Date startTime, Date endTime,
      Long mechanicId, Long diagnosisId) {
    this.id = id;
    this.customerId = customerId;
    this.carPlate = carPlate;
    this.centerId = centerId;
    this.serviceType = serviceType;
    this.mileage = mileage;
    this.startTime = startTime;
    this.endTime = endTime;
    this.mechanicId = mechanicId;
    this.diagnosisId = diagnosisId;
    this.serviceStatus = endTime.before(new Date()) ? ServiceStatus.Complete
        : (startTime.after(new Date()) ? ServiceStatus.Pending : ServiceStatus.Ongoing);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getCarPlate() {
    return carPlate;
  }

  public void setCarPlate(String carPlate) {
    this.carPlate = carPlate;
  }

  public Long getCenterId() {
    return centerId;
  }

  public void setCenterId(Long centerId) {
    this.centerId = centerId;
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

  public Long getMechanicId() {
    return mechanicId;
  }

  public void setMechanicId(Long mechanicId) {
    this.mechanicId = mechanicId;
  }

  public Long getDiagnosisId() {
    return diagnosisId;
  }

  public void setDiagnosisId(Long diagnosisId) {
    this.diagnosisId = diagnosisId;
  }

  public ServiceStatus getServiceStatus() {
    return serviceStatus;
  }

  public void setServiceStatus(ServiceStatus serviceStatus) {
    this.serviceStatus = serviceStatus;
  }
}
