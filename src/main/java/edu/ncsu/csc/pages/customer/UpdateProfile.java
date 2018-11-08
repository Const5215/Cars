package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.MatchType;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

import java.sql.DriverManager;
import java.sql.SQLException;

public class UpdateProfile extends AbstractPage {

  private User user;

  UpdateProfile(User user) {
    this.user = user;
    choices.add("Name");
    choices.add("Address");
    choices.add("Phone Number");
    choices.add("Password");
    choices.add("Go back");
  }

  @Override
  public void run() {
    int choice;
    do {
      System.out.println("#Update Profile");
      displayChoices();
      choice = getChoiceFromInput();
      switch (choice) {
        case 1:
          updateName();
          break;
        case 2:
          updateAddress();
          break;
        case 3:
          updatePhone();
          break;
        case 4:
          updatePassword();
          break;
        case 5:
          goBack();
      }
    } while (choice != 5);
  }

  private void goBack() {
    Page profile = new Profile(user);
    profile.run();
  }

  private void updateAddress() {
    System.out.print("Enter new address: ");
    user.setAddress(scanner.nextLine());
    updateTable("ADDRESS", user.getAddress());
    System.out.println("Address updated.");
  }

  private void updatePhone() {
    user.setPhone(getInfo("Enter new phone number (e.g. 123-456-7890): ", MatchType.Phone));
    updateTable("PHONE", user.getPhone());
    System.out.println("Phone updated.");
  }

  private void updateName() {
    System.out.print("Enter new name: ");
    user.setName(scanner.nextLine());
    updateTable("NAME", user.getName());
    System.out.println("Name updated.");
  }

  private void updatePassword() {
    System.out.print("Enter new password: ");
    user.setPassword(scanner.nextLine());
    updateTable("PASSWORD", user.getPassword());
    System.out.println("Password updated.");
  }

  private void updateTable(String type, String val) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      String query = "UPDATE CUSTOMER SET $type=? WHERE ID=?";
      query = query.replace("$type", type);
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, val);
      preparedStatement.setLong(2, user.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
  }
}
