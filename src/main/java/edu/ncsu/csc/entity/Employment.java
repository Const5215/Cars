package edu.ncsu.csc.entity;

import java.util.Date;

public class Employment {

  private Long employeeId;
  private Long centerId;
  private Role position;
  private Float compensation;
  private Date startDate;

  public Employment() {
  }

  public Employment(Long employeeId, Long centerId, Role position, Float compensation,
      Date startDate) {
    this.employeeId = employeeId;
    this.centerId = centerId;
    this.position = position;
    this.compensation = compensation;
    this.startDate = startDate;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  public Long getCenterId() {
    return centerId;
  }

  public void setCenterId(Long centerId) {
    this.centerId = centerId;
  }

  public Role getPosition() {
    return position;
  }

  public void setPosition(Role position) {
    this.position = position;
  }

  public Float getCompensation() {
    return compensation;
  }

  public void setCompensation(Float compensation) {
    this.compensation = compensation;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
}
