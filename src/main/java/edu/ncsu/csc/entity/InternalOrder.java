package edu.ncsu.csc.entity;

import java.io.Serializable;
import java.util.Date;

public class InternalOrder implements Serializable {

    private Long id;

    private Integer quantity;

    private Date orderDate;

    private Date expectedDeliveryDate;

    private Date actualDeliveryDate;

    private Float total;

    private int status;

    private Long partId;

    private Long toId;

    private Long fromId;


    public InternalOrder() {
    }

    public InternalOrder(Long id, Integer quantity, Date orderDate, Date expectedDeliveryDate, Date actualDeliveryDate, Float total, int status, Long partId, Long toId, Long fromId) {
        this.id = id;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.actualDeliveryDate = actualDeliveryDate;
        this.total = total;
        this.status = status;
        this.partId = partId;
        this.toId = toId;
        this.fromId = fromId;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Long getPartId() { return partId; }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public Long getToId() { return toId; }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public Long getFromId() { return fromId; }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

}

