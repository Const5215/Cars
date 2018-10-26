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
        choices.add("Update Password");
        choices.add("Update Name");
        choices.add("Update Email");
        choices.add("Update Phone");
        choices.add("Update Address");
        choices.add("Go back");
    }

    @Override
    public void run() {
        int choice;
        do {
            System.out.println("# UpdateProfile");
            displayChoices();
            String role;
            if (user.getRole() == Role.Customer)
                role = "CUSTOMER";
            else
                role = "EMPLOYEE";

            choice = getChoiceFromInput();
            switch (choice) {
                case 1:
                    updatePassword(role);
                    break;
                case 2:
                    updateName(role);
                    break;
                case 3:
                    updateEmail(role);
                    break;
                case 4:
                    updatePhone(role);
                    break;
                case 5:
                    updateAddress(role);
                    break;
                case 6:
                    Page profileSubmenu = new ProfileSubmenu(user);
                    profileSubmenu.run();
            }
        }while(choice != 6);
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

    private void updateEmail(String tableName) {
        do {
            System.out.print("Enter new email address: ");
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
        updateTable(tableName,"EMAIL", user.getEmail());
        System.out.println("Email updated.");
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
