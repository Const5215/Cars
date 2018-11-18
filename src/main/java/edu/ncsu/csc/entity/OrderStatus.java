package edu.ncsu.csc.entity;

public enum OrderStatus {
  Pending(0), Complete(1);

  private int status;

  private OrderStatus(int status) {
    this.status = status;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
