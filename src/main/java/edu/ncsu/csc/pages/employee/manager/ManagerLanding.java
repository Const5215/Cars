package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.Role;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.pages.ProfileSubmenu;
import edu.ncsu.csc.pages.start.Home;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ManagerLanding extends AbstractPage {

  private User manager;

  public ManagerLanding(User manager) {
    this.manager = manager;
    choices.add("View and Update Profile");
    choices.add("View Customer Profile");
    choices.add("Add New Employee");
    choices.add("View Payroll Information");
    choices.add("View Inventory");
    choices.add("View and Place Orders");
    choices.add("View Notifications");
    choices.add("Register New Car");
    choices.add("View Service Details");
    choices.add("View Service History");
    choices.add("View Invoices");
    choices.add("Go Back");
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
    Long customerId;
    System.out.println("viewCustomerProfile");
    System.out.println("Enter customer id:");
    do {
      try {
        customerId = Long.parseLong(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid customer ID");
        continue;
      }
      try {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        preparedStatement = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE ID=?");
        preparedStatement.setLong(1, customerId);
        resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
          System.out.println("No such customer");
          continue;
        }
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
        break;
      }
    }while(true);
  }

  private void viewAndUpdateProfile() {
    Page profileSubmenu = new ProfileSubmenu(manager);
    profileSubmenu.run();
  }
}
