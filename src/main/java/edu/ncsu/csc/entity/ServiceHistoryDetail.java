package edu.ncsu.csc.entity;

public class ServiceHistoryDetail {
  private long serviceHistoryId;
  private long partId;
  private long quantity;

  public ServiceHistoryDetail() {
  }

  public ServiceHistoryDetail(long serviceHistoryId, long partId, long quantity) {
    this.serviceHistoryId = serviceHistoryId;
    this.partId = partId;
    this.quantity = quantity;
  }

  public long getServiceHistoryId() {
    return serviceHistoryId;
  }

  public void setServiceHistoryId(long serviceHistoryId) {
    this.serviceHistoryId = serviceHistoryId;
  }

  public long getPartId() {
    return partId;
  }

  public void setPartId(long partId) {
    this.partId = partId;
  }

  public long getQuantity() {
    return quantity;
  }

  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }
}
