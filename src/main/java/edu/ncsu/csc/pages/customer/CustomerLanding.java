package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.pages.start.Home;

public class CustomerLanding extends AbstractPage {

  private User customer;

  public CustomerLanding(User customer) {
    this.customer = customer;
    choices.add("Profile");
    choices.add("Register Car");
    choices.add("Service");
    choices.add("Invoices");
    choices.add("Logout");
  }

  @Override
  public void run() {
    System.out.println("# Customer");
    displayChoices();

    switch (getChoiceFromInput()) {
      case 1:
        profile();
        break;
      case 2:
        registerCar();
        break;
      case 3:
        service();
        break;
      case 4:
        invoices();
        break;
      case 5:
        logout();
    }
  }

  private void profile() {
    Page customerProfile = new Profile(customer);
    customerProfile.run();
  }

  private void registerCar() {
    Page registerCar = new RegisterCar(customer);
    registerCar.run();
  }

  private void service() {
    Page service = new Service(customer);
    service.run();
  }

  private void invoices() {
    Page invoice = new Invoice(customer);
    invoice.run();
  }

  private void logout() {
    Page home = new Home();
    home.run();
  }
}
