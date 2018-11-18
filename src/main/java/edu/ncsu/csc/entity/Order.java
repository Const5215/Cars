package edu.ncsu.csc.entity;

import java.util.Date;

public class Order {

  private Long id;
  private Long partId;
  private Integer quantity;
  private Float total;
  private Long fromId;
  private Long toId;
  private Date orderDate;
  private Date expectedDeliveryDate;
  private Date actualDeliveryDate;
  private Integer status;
  private Boolean isDeliveryOrder;
  private Long centerId;
  private Long distributorId;

  public Order() {
  }

  public Order(Long id, Long partId, Integer quantity, Float total, Long fromId, Long toId, Date orderDate,
               Date expectedDeliveryDate, Date actualDeliveryDate, Integer status, Boolean isDeliveryOrder,
               Long centerId, Long distributorId) {
    this.id = id;
    this.partId = partId;
    this.quantity = quantity;
    this.total = total;
    this.fromId = fromId;
    this.toId = toId;
    this.orderDate = orderDate;
    this.expectedDeliveryDate = expectedDeliveryDate;
    this.actualDeliveryDate = actualDeliveryDate;
    this.status = status;
    this.isDeliveryOrder = isDeliveryOrder;
    this.centerId = centerId;
    this.distributorId = distributorId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getPartId() {
    return partId;
  }

  public void setPartId(Long partId) {
    this.partId = partId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Float getTotal() {
    return total;
  }

  public void setTotal(Float total) {
    this.total = total;
  }

  public Long getFromId() {
    return fromId;
  }

  public void setFromId(Long fromId) {
    this.fromId = fromId;
  }

  public Long getToId() {
    return toId;
  }

  public void setToId(Long toId) {
    this.toId = toId;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public Date getExpectedDeliveryDate() {
    return expectedDeliveryDate;
  }

  public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
    this.expectedDeliveryDate = expectedDeliveryDate;
  }

  public Date getActualDeliveryDate() {
    return actualDeliveryDate;
  }

  public void setActualDeliveryDate(Date actualDeliveryDate) {
    this.actualDeliveryDate = actualDeliveryDate;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Boolean getDeliveryOrder() {
    return isDeliveryOrder;
  }

  public void setDeliveryOrder(Boolean deliveryOrder) {
    isDeliveryOrder = deliveryOrder;
  }

  public Long getCenterId() {
    return centerId;
  }

  public void setCenterId(Long centerId) {
    this.centerId = centerId;
  }

  public Long getDistributorId() {
    return distributorId;
  }

  public void setDistributorId(Long distributorId) {
    this.distributorId = distributorId;
  }
}
