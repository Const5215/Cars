package edu.ncsu.csc.entity;

import java.util.Date;

public class Employment {
  private long employeeId;
  private long centerId;
  private Role position;
  private float compensation;
  private Date startDate;

  public Employment() {
  }

  public Employment(long employeeId, long centerId, Role position, float compensation, Date startDate) {
    this.employeeId = employeeId;
    this.centerId = centerId;
    this.position = position;
    this.compensation = compensation;
    this.startDate = startDate;
  }

  public long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(long employeeId) {
    this.employeeId = employeeId;
  }

  public long getCenterId() {
    return centerId;
  }

  public void setCenterId(long centerId) {
    this.centerId = centerId;
  }

  public Role getPosition() {
    return position;
  }

  public void setPosition(Role position) {
    this.position = position;
  }

  public float getCompensation() {
    return compensation;
  }

  public void setCompensation(float compensation) {
    this.compensation = compensation;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
}
