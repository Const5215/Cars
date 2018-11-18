package edu.ncsu.csc.entity;

public class Diagnosis {

  private Long id;
  private String problem;
  private String issue;
  private Float fee;

  public Diagnosis(Long id, String problem, String issue, Float fee) {
    this.id = id;
    this.problem = problem;
    this.issue = issue;
    this.fee = fee;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getProblem() {
    return problem;
  }

  public void setProblem(String problem) {
    this.problem = problem;
  }

  public String getIssue() {
    return issue;
  }

  public void setIssue(String issue) {
    this.issue = issue;
  }

  public Float getFee() {
    return fee;
  }

  public void setFee(Float fee) {
    this.fee = fee;
  }
}
