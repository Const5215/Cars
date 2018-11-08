package edu.ncsu.csc.entity;

import java.io.Serializable;
import java.util.Date;

public class InternalOrder implements Serializable {

    private Long id;

    private Integer quantity;

    private Date orderDate;

    private Date deliveryDate;

    private Float total;

    private OrderStatus status;

    //private Part part;

//    private Center fromCenter;
//
//    private Center toCenter;

    private InternalNotification internalNotification;

    private Long partId;

    private Long toId;

    private Long fromId;


    public InternalOrder() {
    }

    public InternalOrder(
                         Center toCenter, InternalNotification internalNotification) {
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.total = total;
        this.status = status;
//        this.part = part;
//        this.fromCenter = fromCenter;
//        this.toCenter = toCenter;
        this.internalNotification = internalNotification;
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

//    public Part getPart() {
//        return part;
//    }
//
//    public void setPart(Part part) {
//        this.part = part;
//    }

    public Long getPartId() { return partId; }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

//    public Center getFromCenter() {
//        return fromCenter;
//    }
//
//    public void setFromCenter(Center fromCenter) {
//        this.fromCenter = fromCenter;
//    }
//
//    public Center getToCenter() {
//        return toCenter;
//    }
//
//    public void setToCenter(Center toCenter) {
//        this.toCenter = toCenter;
//    }

    public Long getToId() { return toId; }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public Long getFromId() { return fromId; }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public InternalNotification getInternalNotification() {
        return internalNotification;
    }

    public void setInternalNotification(InternalNotification internalNotification) {
        this.internalNotification = internalNotification;
    }
}
