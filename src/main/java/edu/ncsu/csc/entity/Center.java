package edu.ncsu.csc.entity;

import java.io.Serializable;
import java.util.List;


public class Center implements Serializable {

    private Long id;
    private String name;
    private String address;
    private String telephone;
    private List<Inventory> inventories;
    private List<Employment> employments;
    private List<ExternalOrder> externalOrders;
    private List<InternalOrder> internalOrdersFrom;
    private List<InternalOrder> internalOrdersTo;

    protected Center() {
    }

    public Center(
                  List<Inventory> inventories, List<Employment> employments,
                  List<ExternalOrder> externalOrders,
                  List<InternalOrder> internalOrdersFrom,
                  List<InternalOrder> internalOrdersTo) {
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.inventories = inventories;
        this.employments = employments;
        this.externalOrders = externalOrders;
        this.internalOrdersFrom = internalOrdersFrom;
        this.internalOrdersTo = internalOrdersTo;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }

    public List<Employment> getEmployments() {
        return employments;
    }

    public void setEmployments(List<Employment> employments) {
        this.employments = employments;
    }

    public List<ExternalOrder> getExternalOrders() {
        return externalOrders;
    }

    public void setExternalOrders(List<ExternalOrder> externalOrders) {
        this.externalOrders = externalOrders;
    }

    public List<InternalOrder> getInternalOrdersFrom() {
        return internalOrdersFrom;
    }

    public void setInternalOrdersFrom(
            List<InternalOrder> internalOrdersFrom) {
        this.internalOrdersFrom = internalOrdersFrom;
    }

    public List<InternalOrder> getInternalOrdersTo() {
        return internalOrdersTo;
    }

    public void setInternalOrdersTo(List<InternalOrder> internalOrdersTo) {
        this.internalOrdersTo = internalOrdersTo;
    }
}
