package edu.ncsu.csc.pages;

import edu.ncsu.csc.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbstractPage implements Page {

  protected static final String URL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
  protected static final String USERNAME = "";
  protected static final String PASSWORD = "";
  protected Connection connection;
  protected PreparedStatement preparedStatement;
  protected ResultSet resultSet;
  protected Scanner scanner;
  protected List<String> choices;

  protected AbstractPage() {
    scanner = new Scanner(System.in);
    choices = new ArrayList<>();
  }

  public void run() {
  }

  protected void displayChoices() {
    for (int i = 0; i < choices.size(); i++) {
      System.out.printf("%d.\t%s\n", i + 1, choices.get(i));
    }
  }

  protected Integer getChoiceFromInput() {
    Integer choice;

    do {
      System.out.print("Enter choice: ");
      String input = scanner.nextLine();

      try {
        choice = Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println("Invalid choice");
        continue;
      }

      if (choice < 1 || choice > choices.size()) {
        System.out.println("Invalid choice");
        continue;
      }

      return choice;
    } while (true);
  }

  protected void closeSqlConnection() {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    if (preparedStatement != null) {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    if (resultSet != null) {
      try {
        resultSet.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  protected String getInfo(String info, MatchType matchType) {
    String result;
    do {
      System.out.print(info);
      result = scanner.nextLine();
      boolean matched = true;
      String targetRegex;
      Pattern pattern;
      Matcher matcher;
      switch (matchType) {
        case Email:
          targetRegex = "^[\\w.%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
          pattern = Pattern.compile(targetRegex);
          matcher = pattern.matcher(result);
          matched = matcher.matches();
          break;
        case Phone:
          targetRegex = "^\\d{3}-\\d{3}-\\d{4}$";
          pattern = Pattern.compile(targetRegex);
          matcher = pattern.matcher(result);
          matched = matcher.matches();
          break;
        case Date:
          targetRegex = "^[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$";
          pattern = Pattern.compile(targetRegex);
          matcher = pattern.matcher(result);
          matched = matcher.matches();
          break;
        case Number:
          try {
            Long num = Long.parseLong(scanner.nextLine());
          } catch (NumberFormatException e) {
            matched = false;
          }
      }
      if (matched) {
        break;
      } else {
        switch (matchType) {
          case Email:
            System.out.println("Invalid email");
            break;
          case Phone:
            System.out.println("Invalid phone");
            break;
          case Date:
            System.out.println("Invalid date");
            break;
          case Number:
            System.out.println("Invalid number");
        }
      }
    } while (true);
    return result;
  }

  protected boolean checkUsedEmail(String email) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("select * from CUSTOMER where EMAIL=?");
      preparedStatement.setString(1, email);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        System.out.println("This email address is already used.");
        return true;
      } else {
        return false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return true;
  }

  protected Car getCar() {
    Car car = null;
    try {
      car = new Car(resultSet.getString("PLATE"),
          resultSet.getLong("CUSTOMER_ID"),
          resultSet.getLong("CAR_MODEL_ID"),
          resultSet.getDate("PURCHASE_DATE"),
          resultSet.getLong("LAST_MILEAGE"),
          resultSet.getLong("LAST_SERVICE_TYPE"),
          resultSet.getDate("LAST_SERVICE_DATE"));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return car;
  }

  protected User getUser() {
    User user = null;
    try {
      user = new User(resultSet.getLong("ID"),
          resultSet.getString("PASSWORD"),
          resultSet.getString("NAME"),
          resultSet.getString("EMAIL"),
          resultSet.getString("PHONE"),
          resultSet.getString("ADDRESS"),
          Role.Customer);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return user;
  }

  protected long getCenterId(long employeeId) {
    long centerId = -1;
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("SELECT CENTER_ID FROM EMPLOYMENT WHERE EMPLOYEE_ID=?");
      preparedStatement.setLong(1, employeeId);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        centerId = resultSet.getLong("CENTER_ID");
      } else {
        System.out.println("Center Id not found");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return centerId;
  }

  protected Part getPart() {
    Part part = null;
    try {
      part = new Part(resultSet.getLong("ID"),
          resultSet.getString("NAME"),
          resultSet.getLong("UNIT_PRICE"),
          resultSet.getLong("DISTRIBUTOR_ID"));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return part;
  }

  protected void displayCarList(List<Car> carList) {
    for (int i = 0; i < carList.size(); i++) {
      System.out.printf("Car #%d details:\n", i);
      System.out.println("Plate: " + carList.get(i).getPlate());
      System.out.println("Purchase date:" + carList.get(i).getPurchaseDate());
      System.out.println("Last Mileage:" + carList.get(i).getLastMileage());
      System.out.println("Last service type:" + carList.get(i).getLastServiceType());
      System.out.println("Last service date:" + carList.get(i).getLastServiceDate());
    }
  }

}
