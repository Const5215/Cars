package edu.ncsu.csc.pages.start;

import edu.ncsu.csc.entity.Role;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.pages.customer.CustomerLanding;
import edu.ncsu.csc.pages.employee.manager.ManagerLanding;
import edu.ncsu.csc.pages.employee.receptionist.ReceptionistLanding;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Login extends AbstractPage {

  private User user;

  Login() {
    choices.add("Sign-In");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    Long id;
    String password;

    System.out.println("# Login");

    do {
      System.out.print("Enter user ID: ");

      try {
        id = Long.parseLong(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid user ID");
        continue;
      }

      System.out.print("Enter password: ");
      password = scanner.nextLine();

      displayChoices();

      switch (getChoiceFromInput()) {
        case 1:
          user = signIn(id, password);

          if (user == null) {
            System.out.println("Login incorrect");
            continue;
          }

          switch (user.getRole()) {
            case Customer:
              Page customer = new CustomerLanding(user);
              customer.run();
              break;
            case Manager:
              Page manager = new ManagerLanding(user);
              manager.run();
              break;
            case Receptionist:
              Page receptionist = new ReceptionistLanding(user);
              receptionist.run();
              break;
            case Mechanic:
              System.out.println("Permission denied");
              break;
          }

          user = null;
          break;
        case 2:
          Page home = new Home();
          home.run();
      }
    } while (user == null);
  }

  private User signIn(Long id, String password) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "select NAME, EMAIL, PHONE, ADDRESS, POSITION from EMPLOYEE, EMPLOYMENT where ID=EMPLOYEE_ID and ID=? and PASSWORD=?");
      preparedStatement.setLong(1, id);
      preparedStatement.setString(2, password);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        String name = resultSet.getString("NAME");
        String email = resultSet.getString("EMAIL");
        String phone = resultSet.getString("PHONE");
        String address = resultSet.getString("ADDRESS");
        Role role = Role.values()[resultSet.getInt("POSITION")];
        user = new User(id, password, name, email, phone, address, role);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    if (user != null) {
      return user;
    }

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "select NAME, EMAIL, PHONE, ADDRESS from CUSTOMER where ID=? and PASSWORD=?");
      preparedStatement.setLong(1, id);
      preparedStatement.setString(2, password);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        String name = resultSet.getString("NAME");
        String email = resultSet.getString("EMAIL");
        String phone = resultSet.getString("PHONE");
        String address = resultSet.getString("ADDRESS");
        user = new User(id, password, name, email, phone, address, Role.Customer);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return user;
  }
}
