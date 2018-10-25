package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateProfile extends AbstractPage {

    private User user;

    public UpdateProfile(User user) {
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
        do {
            System.out.println("# UpdateProfile");
            displayChoices();
            switch (getChoiceFromInput()) {
                case 1:
                    updatePassword();
                    break;
                case 2:
                    updateName();
                    break;
                case 3:
                    updateEmail();
                    break;
                case 4:
                    updatePhone();
                    break;
                case 5:
                    updateAddress();
                    break;
                case 6:
                    Page profileSubmenu = new ProfileSubmenu(user);
                    profileSubmenu.run();
                    break;
            }
        }while(true);
    }

    private void updateAddress() {
        System.out.print("Enter new Address: ");
        user.setAddress(scanner.nextLine());
        System.out.println("Address updated.");
    }

    private void updatePhone() {
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

        updateTable("PHONE", user.getPhone());
        System.out.println("Phone updated.");
    }

    private void updateEmail() {
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
            switch(type){
                case "PASSWORD":
                    preparedStatement = connection.prepareStatement("UPDATE CUSTOMER SET PASSWORD=? WHERE ID=?");
                    break;
                case "NAME":
                    preparedStatement = connection.prepareStatement("UPDATE CUSTOMER SET NAME=? WHERE ID=?");
                    break;
                case "EMAIL":
                    preparedStatement = connection.prepareStatement("UPDATE CUSTOMER SET EMAIL=? WHERE ID=?");
                    break;
                case "PHONE":
                    preparedStatement = connection.prepareStatement("UPDATE CUSTOMER SET PHONE=? WHERE ID=?");
                    break;
                case "ADDRESS":
                    preparedStatement = connection.prepareStatement("UPDATE CUSTOMER SET ADDRESS=? WHERE ID=?");

            }
            preparedStatement.setString(1, val);
            preparedStatement.setLong(2, user.getId());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
