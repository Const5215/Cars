package edu.ncsu.csc.entity;

import java.io.Serializable;
import java.util.Date;

public class ExternalOrder implements Serializable {

    private Long id;

    private Integer quantity;

    private Date orderDate;

    private Date deliveryDate;

    private Float total;

    private OrderStatus status;

    private ExternalNotification externalNotification;

    private Long partId;

    private Long centerId;

    private Long distributorId;


    public ExternalOrder() {
    }

    public ExternalOrder(
                         Distributor distributor, ExternalNotification externalNotification) {
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.total = total;
        this.status = status;
        this.externalNotification = externalNotification;
        this.partId = partId;
        this.distributorId = distributorId;
        this.centerId = centerId;


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public ExternalNotification getExternalNotification() {
        return externalNotification;
    }

    public void setExternalNotification(ExternalNotification externalNotification) {
        this.externalNotification = externalNotification; }

    public Long getPartId() { return partId; }

    public void setPartId(Long partId) { this.partId = partId; }

    public Long getCenterId() { return centerId; }

    public void setCenterId(Long centerId) { this.centerId = centerId; }

    public Long getDistributorId() { return distributorId; }

    public void setDistributorId(Long distributorId) { this.distributorId = distributorId; }
}
