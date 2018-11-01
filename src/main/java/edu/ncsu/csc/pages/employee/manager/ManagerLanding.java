package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.pages.employee.Profile;
import edu.ncsu.csc.pages.employee.ViewCustomerProfile;
import edu.ncsu.csc.pages.start.Home;

public class ManagerLanding extends AbstractPage {

  private User manager;

  public ManagerLanding(User manager) {
    this.manager = manager;
    choices.add("Profile");
    choices.add("View Customer Profile");
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
    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        profile();
        break;
      case 2:
        viewCustomerProfile();
        break;
      case 3:
        addNewEmployees();
        break;
      case 4:
        payroll();
        break;
      case 5:
        inventory();
        break;
      case 6:
        orders();
        break;
      case 7:
        notifications();
        break;
      case 8:
        newCarModel();
        break;
      case 9:
        carServiceDetails();
        break;
      case 10:
        serviceHistory();
        break;
      case 11:
        invoices();
      case 12:
        logout();
    }
  }

  private void logout() {
    Page home = new Home();
    home.run();
  }

  private void invoices() {
    Page invoices = new Invoices(manager);
    invoices.run();
  }

  private void serviceHistory() {
    Page serviceHistory = new ServiceHistory(manager);
    serviceHistory.run();
  }

  private void carServiceDetails() {
    Page carServiceDetails = new CarServiceDetails(manager);
    carServiceDetails.run();
  }

  private void newCarModel() {
    Page newCarModel = new NewCarModel(manager);
    newCarModel.run();
  }

  private void notifications() {
    Page notifications = new Notifications(manager);
    notifications.run();
  }

  private void orders() {
    Page orders = new Orders(manager);
    orders.run();
  }

  private void inventory() {
    Page inventory = new Inventory(manager);
    inventory.run();
  }

  private void payroll() {
    Page payroll = new Payroll(manager);
    payroll.run();
  }

  private void addNewEmployees() {
    Page addNewEmployees = new AddNewEmployees(manager);
    addNewEmployees.run();
  }

  private void viewCustomerProfile() {
    Page viewCustomerProfile = new ViewCustomerProfile(manager);
    viewCustomerProfile.run();
  }

  private void profile() {
    Page profile = new Profile(manager);
    profile.run();
  }
}
