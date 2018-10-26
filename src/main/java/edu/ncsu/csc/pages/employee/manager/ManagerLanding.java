package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.Role;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.pages.Profile;
import edu.ncsu.csc.pages.start.Home;

import java.sql.DriverManager;
import java.sql.SQLException;

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
    System.out.println("addNewEmployee");
    System.out.println("Enter ");
  }

  private void viewCustomerProfile() {
    long customerId;
    System.out.println("viewCustomerProfile");
    System.out.println("Enter customer id:");
    do {
      try {
        customerId = Long.parseLong(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid customer ID");
        continue;
      }
      break;
    } while (true);
      try {
        do {
          connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
          preparedStatement = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE ID=?");
          preparedStatement.setLong(1, customerId);
          resultSet = preparedStatement.executeQuery();
          if (!resultSet.next()) {
            System.out.println("No such customer");
            continue;
          }
          break;
        } while (true);
        User viewCustomer = new User(resultSet.getLong("ID"),
            resultSet.getString("PASSWORD"),
            resultSet.getString("NAME"),
            resultSet.getString("EMAIL"),
            resultSet.getString("PHONE"),
            resultSet.getString("ADDRESS"),
            Role.Customer);
        System.out.println("Customer ID: " + viewCustomer.getId());
        System.out.println("Password: " + viewCustomer.getPassword());
        System.out.println("Name: " + viewCustomer.getName());
        System.out.println("Email: " + viewCustomer.getEmail());
        System.out.println("Phone: " + viewCustomer.getPhone());
        System.out.println("Address: " + viewCustomer.getAddress());
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        closeSqlConnection();
      }
  }

  private void viewAndUpdateProfile() {
    Page profile = new Profile(manager);
    profile.run();
  }
}
