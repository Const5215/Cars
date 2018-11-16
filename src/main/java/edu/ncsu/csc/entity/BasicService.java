package edu.ncsu.csc.entity;

public class BasicService {

  private Long id;
  private String name;
  private Float laborHour;
  private Integer chargeRate;

  public BasicService() {
  }

  public BasicService(Long id, String name, Float laborHour, Integer chargeRate) {
    this.id = id;
    this.name = name;
    this.laborHour = laborHour;
    this.chargeRate = chargeRate;
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

  public Float getLaborHour() {
    return laborHour;
  }

  public void setLaborHour(Float laborHour) {
    this.laborHour = laborHour;
  }

  public Integer getChargeRate() {
    return chargeRate;
  }

  public void setChargeRate(Integer chargeRate) {
    this.chargeRate = chargeRate;
  }
}
