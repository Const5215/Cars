package edu.ncsu.csc.pages;

import edu.ncsu.csc.entity.Role;
import edu.ncsu.csc.entity.User;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
      System.out.println("#Update Profile");
      displayChoices();
      String role;
      if (user.getRole() == Role.Customer)
        role = "CUSTOMER";
      else
        role = "EMPLOYEE";
      choice = getChoiceFromInput();
      switch (choice) {
        case 1:
          updateName(role);
          break;
        case 2:
          updateAddress(role);
          break;
        case 3:
          updatePhone(role);
          break;
        case 4:
          updatePassword(role);
          break;
        case 5:
          Page profileSubmenu = new Profile(user);
          profileSubmenu.run();
      }
    }

    private void updateAddress(String tableName) {
        System.out.print("Enter new Address: ");
        user.setAddress(scanner.nextLine());
        updateTable(tableName, "ADDRESS", user.getAddress());
        System.out.println("Address updated.");
    }

    private void updatePhone(String tableName) {
        do {
            System.out.print("Enter new phone number (e.g. 123-456-7890): ");
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

        updateTable(tableName,"PHONE", user.getPhone());
        System.out.println("Phone updated.");
    }

    private void updateName(String tabelName) {
        System.out.print("Enter new name: ");
        user.setName(scanner.nextLine());
        updateTable(tabelName, "NAME", user.getName());
        System.out.println("Name updated.");
    }

    private void updatePassword(String tableName) {
        System.out.print("Enter new password: ");
        user.setPassword(scanner.nextLine());
        updateTable(tableName,"PASSWORD", user.getPassword());
        System.out.println("Password updated.");
    }

    private void updateTable(String tableName, String type, String val) {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "UPDATE $tableName SET $type=? WHERE ID=?";
            query = query.replace("$tableName", tableName);
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
