package edu.ncsu.csc.entity;

import java.io.Serializable;
import java.util.Date;

public class ExternalOrder implements Serializable {

    private Long id;

    private Integer quantity;

    private Date orderDate;

    private Date expectDeliveryDate;

    private Date actualDeliveryDate;

    private Float total;

    private int status;

    private ExternalNotification externalNotification;

    private Long partId;

    private Long centerId;

    private Long distributorId;


    public ExternalOrder() {
    }

    public ExternalOrder(Long id, Integer quantity, Date orderDate, Date expectDeliveryDate, Date actualDeliveryDate, Float total, int status, ExternalNotification externalNotification, Long partId, Long centerId, Long distributorId) {
        this.id = id;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.expectDeliveryDate = expectDeliveryDate;
        this.actualDeliveryDate = actualDeliveryDate;
        this.total = total;
        this.status = status;
        this.externalNotification = externalNotification;
        this.partId = partId;
        this.centerId = centerId;
        this.distributorId = distributorId;
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

    public Date getExpectDeliveryDate() {
        return expectDeliveryDate;
    }

    public void setExpectDeliveryDate(Date expectDeliveryDate) {
        this.expectDeliveryDate = expectDeliveryDate;
    }

    public Date getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(Date actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getPartId() { return partId; }

    public void setPartId(Long partId) { this.partId = partId; }

    public Long getCenterId() { return centerId; }

    public void setCenterId(Long centerId) { this.centerId = centerId; }

    public Long getDistributorId() { return distributorId; }

    public void setDistributorId(Long distributorId) { this.distributorId = distributorId; }
}
