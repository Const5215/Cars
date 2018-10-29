package edu.ncsu.csc.entity;

import java.util.Date;

public class Payroll {


    private Long id;
    private Date fromDate;
    private Date toDate;
    private Date paycheckDate;
    private Integer unit;
    private Float earning;


    public Payroll() {
    }

    public Payroll(Date fromDate, Date toDate,
                   Date paycheckDate, Integer unit,
                   Float earning) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.paycheckDate = paycheckDate;
        this.unit = unit;
        this.earning = earning;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getPaycheckDate() {
        return paycheckDate;
    }

    public void setPaycheckDate(Date paycheckDate) {
        this.paycheckDate = paycheckDate;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Float getEarning() {
        return earning;
    }

    public void setEarning(Float earning) {
        this.earning = earning;
    }

}
