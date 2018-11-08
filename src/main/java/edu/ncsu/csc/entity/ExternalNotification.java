package edu.ncsu.csc.entity;

import java.io.Serializable;
import java.util.Date;


public class ExternalNotification implements Serializable {

    private Long id;

    private Date notificationDate;

    private Long orderId;

    public ExternalNotification() {
    }

    public ExternalNotification( Date notificationDate,
                                ExternalOrder externalOrder) {
        this.notificationDate = notificationDate;
//        this.externalOrder = externalOrder;
        this.orderId = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }
//
//    public ExternalOrder getExternalOrder() {
//        return externalOrder;
//    }
//
//    public void setExternalOrder(ExternalOrder externalOrder) {
//        this.externalOrder = externalOrder;
//    }
    public Long getOrderId() { return orderId; }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
