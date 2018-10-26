package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.pages.Profile;
import edu.ncsu.csc.pages.start.Home;

public class CustomerLanding extends AbstractPage {

  private User customer;

  public CustomerLanding(User customer) {
    this.customer = customer;
    choices.add("View and Update Profile");
    choices.add("Register Car");
    choices.add("View and Schedule Service");
    choices.add("View Invoices");
    choices.add("Logout");
  }

  @Override
  public void run() {
    System.out.println("# Customer");
    displayChoices();

    switch (getChoiceFromInput()) {
      case 1:
        viewAndUpdateProfile();
        break;
      case 2:
        registerCar();
        break;
      case 3:
        viewAndScheduleService();
        break;
      case 4:
        viewInvoices();
        break;
      case 5:
        logout();
        break;
    }
  }

  private void viewAndUpdateProfile() {
    Page profileSubmenu = new Profile(customer);
    profileSubmenu.run();
  }

  private void registerCar() {
    Page registerCar = new RegisterCar(customer);
    registerCar.run();
  }

  private void viewAndScheduleService() {
    Page serviceSubmenu = new ServiceSubmenu(customer);
    serviceSubmenu.run();
  }

  private void viewInvoices() {
    Page customerInvoice = new CustomerInvoice(customer);
    customerInvoice.run();
  }

  private void logout() {
    Page home = new Home();
    home.run();
  }

}
