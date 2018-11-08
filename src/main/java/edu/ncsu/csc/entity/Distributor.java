package edu.ncsu.csc.entity;

import java.io.Serializable;
import java.util.List;


public class Distributor implements Serializable {

    private Long id;

    private String name;

    private Integer deliveryWindow;

    private List<ExternalOrder> externalOrders;

    protected Distributor() {
    }

    public Distributor(
                       List<ExternalOrder> externalOrders) {
        this.name = name;
        this.deliveryWindow = deliveryWindow;
        this.externalOrders = externalOrders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDeliveryWindow() {
        return deliveryWindow;
    }

    public void setDeliveryWindow(Integer deliveryWindow) {
        this.deliveryWindow = deliveryWindow;
    }

    public List<ExternalOrder> getExternalOrders() {
        return externalOrders;
    }

    public void setExternalOrders(List<ExternalOrder> externalOrders) {
        this.externalOrders = externalOrders;
    }
}
