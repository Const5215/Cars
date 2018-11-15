package edu.ncsu.csc.entity;

public class BasicService {
  private long id;
  private String name;
  private float laborHour;
  private long chargeRate;

  public BasicService() {
  }

  public BasicService(long id, String name, float laborHour, long chargeRate) {
    this.id = id;
    this.name = name;
    this.laborHour = laborHour;
    this.chargeRate = chargeRate;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getLaborHour() {
    return laborHour;
  }

  public void setLaborHour(float laborHour) {
    this.laborHour = laborHour;
  }

  public long getChargeRate() {
    return chargeRate;
  }

  public void setChargeRate(long chargeRate) {
    this.chargeRate = chargeRate;
  }
}
