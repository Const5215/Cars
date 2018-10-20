package edu.ncsu.csc.pages.start;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AbstractPage {

  SignUp() {
    choices.add("Sign Up");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    User user = new User();

    System.out.println("# Sign Up");

    do {
      System.out.print("Enter email address: ");
      String email = scanner.nextLine().toLowerCase();
      Pattern pattern = Pattern
          .compile("^[\\w.%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
      Matcher matcher = pattern.matcher(email);

      if (!matcher.matches()) {
        System.out.println("Invalid email address");
        continue;
      }

      try {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        preparedStatement = connection.prepareStatement("select * from CUSTOMER where EMAIL=?");
        preparedStatement.setString(1, email);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
          System.out.println("This email address is already used");
        } else {
          user.setEmail(email);
          break;
        }
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        closeSqlConnection();
      }
    } while (true);

    System.out.print("Enter password: ");
    user.setPassword(scanner.nextLine());

    System.out.print("Enter name: ");
    user.setName(scanner.nextLine());

    System.out.print("Enter address: ");
    user.setAddress(scanner.nextLine());

    do {
      System.out.print("Enter phone number (e.g. 123-456-7890): ");
      String phone = scanner.nextLine();
      Pattern pattern = Pattern.compile("^\\d{3}-\\d{3}-\\d{4}$");
      Matcher matcher = pattern.matcher(phone);

      if (matcher.matches()) {
        user.setPhone(phone);
        break;
      } else {
        System.out.println("Invalid phone number");
      }
    } while (true);

    displayChoices();

    switch (getChoiceFromInput()) {
      case 1:
        signUp(user);
        Page login = new Login();
        login.run();
        break;
      case 2:
        Page home = new Home();
        home.run();
        break;
    }
  }

  private void signUp(User user) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection
          .prepareStatement("insert into CUSTOMER values (CUSTOMER_ID_SEQ.nextval, ?, ?, ?, ?, ?)");
      preparedStatement.setString(1, user.getPassword());
      preparedStatement.setString(2, user.getName());
      preparedStatement.setString(3, user.getEmail());
      preparedStatement.setString(4, user.getPhone());
      preparedStatement.setString(5, user.getAddress());
      preparedStatement.executeUpdate();

      preparedStatement = connection.prepareStatement("select ID from CUSTOMER where EMAIL=?");
      preparedStatement.setString(1, user.getEmail());
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        user.setId(resultSet.getLong("ID"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    System.out.printf("Successfully signed up, your ID: %d\n", user.getId());
  }
}
