package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.pages.employee.EmployeeProfile;
import edu.ncsu.csc.pages.employee.EmployeeViewCustomerProfile;
import edu.ncsu.csc.pages.start.Home;

public class ManagerLanding extends AbstractPage {

  private User manager;

  public ManagerLanding(User manager) {
    this.manager = manager;
    choices.add("CustomerProfile");
    choices.add("View Customer CustomerProfile");
    choices.add("Add New Employees");
    choices.add("Payroll");
    choices.add("Inventory");
    choices.add("Orders");
    choices.add("Notifications");
    choices.add("New Car Model");
    choices.add("Car Service Details");
    choices.add("Service History");
    choices.add("Invoices");
    choices.add("Logout");
  }

  @Override
  public void run() {

    int choice;
      System.out.println("# Manager");
      switch(getChoiceFromInput()){
        case 1:
          viewAndUpdateProfile();
          break;
        case 2:
          viewCustomerProfile();
          break;
        case 3:
          addNewEmployee();
          break;
        case 4:
          viewPayrollInformation();
          break;
        case 5:
          viewInventory();
          break;
        case 6:
          viewAndPlaceOrders();
          break;
        case 7:
          viewNotifications();
          break;
        case 8:
          registerNewCar();
          break;
        case 9:
          viewServiceDetails();
          break;
        case 10:
          viewServiceInventory();
          break;
        case 11:
          viewInvoices();
      }
    Page home = new Home();
    home.run();
  }

  private void viewInvoices() {

  }

  private void viewServiceInventory() {

  }

  private void viewServiceDetails() {

  }

  private void registerNewCar() {

  }

  private void viewNotifications() {

  }

  private void viewAndPlaceOrders() {

  }

  private void viewInventory() {

  }

  private void viewPayrollInformation() {

  }

  private void addNewEmployee() {
    Page addNewEmployees = new AddNewEmployees(manager);
    addNewEmployees.run();
  }

  private void viewCustomerProfile() {
    Page viewCustomerProfile = new EmployeeViewCustomerProfile(manager);
    viewCustomerProfile.run();
  }

  private void viewAndUpdateProfile() {
    Page profile = new EmployeeProfile(manager);
    profile.run();
  }
}
