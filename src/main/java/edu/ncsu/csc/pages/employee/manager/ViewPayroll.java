package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.Payroll;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.IPayrollRepository;
import edu.ncsu.csc.repository.PayrollRepositoryImpl;

import java.text.SimpleDateFormat;
import java.util.List;

public class ViewPayroll extends AbstractPage {
  private User manager;

  ViewPayroll(User manager) {
    this.manager = manager;
    choices.add("Go back");
  }

  @Override
  public void run() {
    System.out.println("#payroll");
    printPayroll();
    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void goBack() {
    Page managerLanding = new ManagerLanding(manager);
    managerLanding.run();
  }

  private void printPayroll(){
    IPayrollRepository payrollRepository = new PayrollRepositoryImpl();
    List<Payroll> payrollList = payrollRepository.getPayroll(this.manager);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:SS");
    for (Payroll payroll : payrollList) {
      System.out.println("Paycheck date: " + simpleDateFormat.format(payroll.getPaycheckDate()));
      System.out.println("Pay period");
      System.out.println("Employee Id" + payroll.getId());
      System.out.println("Employee name" + manager.getName());
      System.out.println("Compensation");
      System.out.println("Compensation frequency (monthly/hourly)");
      System.out.println("Units" + payroll.getUnit());
      System.out.println("Earning (current))" + payroll.getEarning());
    }
  }



}
