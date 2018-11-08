package edu.ncsu.csc.entity;

import java.io.Serializable;

public class Inventory implements Serializable {

    private Long centerId;

    private Long partId;

    private Integer currentQuantity;

    private Integer availableQuantity;

    private Integer minThreshold;

    private Integer minOrderQuantity;

    public Inventory() {
    }

    public Inventory(Long centerId, Long partId, Integer currentQuantity, Integer availableQuantity, Integer minThreshold, Integer minOrderQuantity) {
        this.centerId = centerId;
        this.partId = partId;
        this.currentQuantity = currentQuantity;
        this.availableQuantity = availableQuantity;
        this.minThreshold = minThreshold;
        this.minOrderQuantity = minOrderQuantity;
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public Long getCenterId() {
        return centerId;
    }

    public void setCenterId(Long centerId) {
        this.centerId = centerId;
    }

    public Integer getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(Integer currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Integer getMinThreshold() {
        return minThreshold;
    }

    public void setMinThreshold(Integer minThreshold) {
        this.minThreshold = minThreshold;
    }

    public Integer getMinOrderQuantity() {
        return minOrderQuantity;
    }

    public void setMinOrderQuantity(Integer minOrderQuantity) {
        this.minOrderQuantity = minOrderQuantity;
    }
}
