package edu.ncsu.csc.entity;

import java.io.Serializable;

public class InventoryId implements Serializable {


    private Center center;
    private Part part;
    protected InventoryId() {
    }

    public InventoryId(Center center, Part part) {
        this.center = center;
        this.part = part;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
}
