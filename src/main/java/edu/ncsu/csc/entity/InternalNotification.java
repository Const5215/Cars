package edu.ncsu.csc.entity;

import java.io.Serializable;
import java.util.Date;

public class InternalNotification implements Serializable {

    private Long id;

    private Date notificationDate;

    private Long orderId;

    private InternalOrder internalOrder;

    public InternalNotification() {

    }

    public InternalNotification( Date notificationDate,
                                 InternalOrder internalOrder) {
        this.notificationDate = notificationDate;
//        this.internalOrder = internalOrder;
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

    public Long getOrderId() { return orderId; }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
